package com.guineastore.productservice.services;

import com.guineastore.productservice.entities.Product;
import com.guineastore.productservice.models.ProductModel;

import java.util.List;

public interface IProductService {


    ProductModel createProduct(ProductModel productModel);

    List<ProductModel> getAllProducts();
}
