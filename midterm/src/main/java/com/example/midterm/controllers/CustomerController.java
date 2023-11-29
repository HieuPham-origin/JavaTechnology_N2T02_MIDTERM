package com.example.midterm.controllers;

import com.example.midterm.models.Brand;
import com.example.midterm.models.Category;
import com.example.midterm.models.Product;
import com.example.midterm.models.ProductImage;
import com.example.midterm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private ProductImageService productImageService;
    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("/about")
    public String about(){return "about";}
    @GetMapping("/contact")
    public String contact(){return "contact";}
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
}
