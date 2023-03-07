package com.guineastore.productservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guineastore.productservice.entities.Product;
import com.guineastore.productservice.models.ProductModel;
import com.guineastore.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductModel createProduct(ProductModel productModel) {

        Product product = Product.builder()
                .description(productModel.getDescription())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .build();

        Product savedProduct = productRepository.save(product);


        return ProductModel.builder()
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .id(savedProduct.getId())
                .price(savedProduct.getPrice())
                .build();
    }

    @Override
    public List<ProductModel> getAllProducts() {

        List<Product> listProducts = productRepository.findAll();
        return listProducts.
                stream().
                    map(this::mapProductModel)
                        .collect(Collectors.toList());
    }

    private ProductModel mapProductModel(Product p) {
        return ProductModel.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .build();
    }
}
