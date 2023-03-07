package com.guineastore.productservice.controller;


import com.guineastore.productservice.entities.Product;
import com.guineastore.productservice.models.ProductModel;
import com.guineastore.productservice.services.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final IProductService productService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel createProduct(@RequestBody ProductModel productModel)
    {

         return productService.createProduct(productModel);

    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public  List<ProductModel>getAllProducts()
    {
      List<ProductModel>listProductsModel=productService.getAllProducts();
      return listProductsModel;
    }

}
