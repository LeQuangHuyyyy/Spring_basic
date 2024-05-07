package com.example.Security_Tech.userDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductDTO {
    private String productName;
    private String productBrand;
    private String productCategory;
    private double productPrice;
    private String description;
    private MultipartFile imageUrl;
}
