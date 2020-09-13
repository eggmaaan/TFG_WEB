package com.tfgshop.online.repository;

import com.tfgshop.online.entities.Product;
import com.tfgshop.online.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {

}
