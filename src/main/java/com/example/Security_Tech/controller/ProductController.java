package com.example.Security_Tech.controller;


import com.example.Security_Tech.model.Product;
import com.example.Security_Tech.repository.ProductRepository;
import com.example.Security_Tech.userDTO.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@RequestMapping("/Product")
@Controller
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @GetMapping("home")
    public String home(Model model) {
        List<Product> products;
        products = repo.findAll();
        model.addAttribute("products", products);
        return "ProductHome";
    }

    @PostMapping(value = {"home", "/"})
    public String index(Model model) {
        List<Product> products;
        products = repo.findAll();
        model.addAttribute("products", products);
        return "ProductHome";
    }

    @GetMapping("/create")
    public String create(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("ProductDTO", productDTO);
        return "CreateProduct";
    }

    @GetMapping("store")
    public String store(Model model) {
        return "store";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ProductDTO productDTO, Model model) {
        Product product = new Product();

        Date date = new Date();
        MultipartFile image = productDTO.getImageUrl();
        File imageFile = null;
        if (image != null) {
            try {
                String fileName = image.getOriginalFilename();
                String uploadDir = "/image/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = image.getInputStream()) {
                    imageFile = new File(uploadDir + fileName);
                    Files.copy(inputStream, imageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                product.setCreateDate(date);
                product.setImageUrl(fileName);

                product.setName(productDTO.getProductName());
                product.setBrand(productDTO.getProductBrand());
                product.setCategory(productDTO.getProductCategory());
                product.setPrice(productDTO.getProductPrice());
                product.setDescription(productDTO.getDescription());
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            repo.save(product);
        }
        return "redirect:/Product/home";
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam int id) {
        ProductDTO productDTO = new ProductDTO();
        Product product = repo.findById(id).get();
        model.addAttribute("product", product);

        productDTO.setProductName(product.getName());
        productDTO.setProductBrand(product.getBrand());
        productDTO.setProductCategory(product.getCategory());
        productDTO.setProductPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        model.addAttribute("ProductDTO", productDTO);
        return "EditProduct";
    }

    @PostMapping("edit")
    public String edit(@ModelAttribute ProductDTO productDTO, @RequestParam int id) {
        Product product;
        product = repo.findById(id).get();

        if (!productDTO.getImageUrl().isEmpty()) {
            String uploadDir = "/image/";
            Path oldPath = Paths.get(uploadDir + product.getImageUrl());
            try {
                Files.delete(oldPath);
            } catch (Exception ignored) {
            }
            MultipartFile image = productDTO.getImageUrl();
            String imageFileName = image.getOriginalFilename();
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + imageFileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImageUrl(imageFileName);
        }
        product.setName(productDTO.getProductName());
        product.setBrand(productDTO.getProductBrand());
        product.setCategory(productDTO.getProductCategory());
        product.setPrice(productDTO.getProductPrice());
        product.setDescription(productDTO.getDescription());
        repo.save(product);
        return "redirect:/Product/home";
    }

    @GetMapping("/delete")
    public String delete(Product product, @RequestParam int id) {
        repo.deleteById(id);
        return "redirect:/Product/home";
    }
}
