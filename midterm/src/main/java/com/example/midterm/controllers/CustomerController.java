package com.example.midterm.controllers;

import com.example.midterm.dtos.ProductDTO;
import com.example.midterm.models.*;
import com.example.midterm.repositories.OrderRepository;
import com.example.midterm.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
@Autowired
private OrderRepository orderRepository;
    @Autowired
    private ProductImageService productImageService;
    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "index";
    }
    @GetMapping("/about")
    public String about(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "about";
    }
    @GetMapping("/contact")
    public String contact(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "contact";
    }
    @GetMapping("/products")
    public String products(@RequestParam(value = "brand", required = false) Integer brandId,
                           @RequestParam(value = "category", required = false) Integer categoryId,
                           @RequestParam(value = "color", required = false) String color,
                           Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        List<Product> products = productService.getAllProducts();
        if (brandId != null) {
            products = productService.getProductsByBrand(brandId);
        }
        if (categoryId != null) {
            products = productService.getProductsByCategory(categoryId);
        }
        if (color != null){
            products = productService.getProductsByColor(color);
        }
        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = categoryService.getAllCategories();
        List<ProductImage> firstImages = new ArrayList<>();
        List<String> colors = productService.getColors();

        for (Product product : products) {
            List<ProductImage> images = productService.getImageByProductId(product.getProductId());
            if (!images.isEmpty()) {
                ProductImage firstImage = images.get(0);
                firstImages.add(firstImage);
            }
        }
        model.addAttribute("username", username);
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("firstImages", firstImages);
        model.addAttribute("colors", colors);
        return "shop";
    }
    @GetMapping("/product")
    public String getProductDetail(@RequestParam(value = "productId") Integer productId, Model model) {
        Product product = productService.getProductById(productId);
        List<ProductImage> images = productService.getImageByProductId(product.getProductId());
        model.addAttribute("images", images);
        model.addAttribute("product", product);
        return "product";
    }
    @PostMapping("/addToCart/{id}")
    public ModelAndView addToCart(@PathVariable(name = "id") Integer productId,
                                  @RequestParam(value = "quantity", defaultValue = "1") int quantity,
                                  HttpSession session) {
        Product product = productService.getProductById(productId);
        String username = (String) session.getAttribute("username");
        orderService.createCart(username);
        Optional<Order> order = orderService.existOrder(username);
        if (order.isPresent()) {
            orderService.addToCart(product, order.get(), quantity);
        }
        List<ProductDTO> productDTOList = orderService.listItems(order.get().getOrderId());

        return new ModelAndView("redirect:/products");
    }
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        Optional<Order> order = orderService.existOrder(username);
        if(order.isEmpty()){
            return "redirect:/shop";
        }
        List<ProductDTO> productDTOList = orderService.listItems(order.get().getOrderId());
        int total = orderService.totalPrice(order.get());

        model.addAttribute("items", productDTOList);
        model.addAttribute("total", total);
        return "cart";
    }
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        Customer customer = this.customerService.getByUsername(username);
        model.addAttribute("customer", customer);
        return "checkout";
    }
    @PostMapping("/checkout")
    public String checkout(HttpSession session){
        String username = (String) session.getAttribute("username");
        Optional<Order> order = orderService.existOrder(username);
        if(order.isPresent()){
            Order order1 = order.get();
            order1.setStatus("In Progess");
            order1.setPrice(orderService.totalPrice(order.get()));
            orderService.updateOrder(order.get().getOrderId(), order1);
        }else{
            return null;
        }
        return "thankyou";
    }
}
