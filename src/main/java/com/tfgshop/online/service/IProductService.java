package com.tfgshop.online.service;

import com.tfgshop.online.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> listAllProducts();
    public Optional<Product> findById(int id);
}
