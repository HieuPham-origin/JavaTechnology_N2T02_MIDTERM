package com.example.midterm.controllers;

import com.example.midterm.models.*;
import com.example.midterm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    private ProductImageService productImageService;
    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "about";
    }
    @GetMapping("/contact")
    public String contact(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "contact";
    }
    @GetMapping("/products")
    public String products(@RequestParam(value = "brand", required = false) Integer brandId,
                           @RequestParam(value = "category", required = false) Integer categoryId,
                           @RequestParam(value = "color", required = false) String color,
                           Model model) {
        String username = customerService.getCurrentUsername();
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
        model.addAttribute("prefix", "product");
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
//    @PostMapping("/addToCart")
//    public String addToCart(@RequestParam(name = "productId") int productId) {
//        String username = customerService.getCurrentUsername();
//        Customer customer = (Customer) customerService.loadUserByUsername(username);
//        if (customer != null) {
//            Product product = productService.getProductById(productId);
//            if (product != null) {
//                Optional<Order> order = orderService.existOrder(customer.getUsername());
//                if (order.isPresent())
//                    orderService.addToCart(product, order.get(), 1);
//
//                return "redirect:/cart";
//            }
//        }
//        return "redirect:/products";
//    }
}
