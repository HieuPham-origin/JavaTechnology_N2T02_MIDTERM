package com.example.midterm.controllers;

import com.example.midterm.models.Brand;
import com.example.midterm.models.Category;
import com.example.midterm.models.Product;
import com.example.midterm.models.ProductImage;
import com.example.midterm.services.BrandService;
import com.example.midterm.services.CategoryService;
import com.example.midterm.services.ProductImageService;
import com.example.midterm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    private ProductImageService productImageService;
    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/about")
    public String about(){return "about";}
    @GetMapping("/contact")
    public String contact(){return "contact";}
    @GetMapping("/products")
    public String products(@RequestParam(value = "brand", required = false) Integer brandId,
                           @RequestParam(value = "category", required = false) Integer categoryId,
                           Model model) {
        List<Product> products = productService.getAllProducts();
        if (brandId != null) {
            products = productService.getProductsByBrand(brandId);
        }
        if (categoryId != null) {
            products = productService.getProductsByCategory(categoryId);
        }
        List<Brand> brands = brandService.getAllBrands();
        List<Category> categories = categoryService.getAllCategories();
        List<ProductImage> firstImages = new ArrayList<>();

        for (Product product : products) {
            List<ProductImage> images = productService.getImageByProductId(product.getProductId());
            if (!images.isEmpty()) {
                ProductImage firstImage = images.get(0);
                firstImages.add(firstImage);
            }
        }

        model.addAttribute("prefix", "product");
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("firstImages", firstImages);

        return "shop";
    }
}
