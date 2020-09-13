package com.tfgshop.online.repository;

import com.tfgshop.online.entities.Product;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IWarehouseStockRepository extends JpaRepository<WarehouseStock, Integer> {

    @Query("SELECT p FROM WarehouseStock p WHERE p.warehouse = :warehouse and p.product = :product")
    public Optional<WarehouseStock> findByWaAndPro(@Param("warehouse") Warehouse warehouse,
                                                   @Param("product") Product product);

    /*
        Listado de productos que tengan menos de 5 unidades
     */

    @Query("SELECT p FROM WarehouseStock p WHERE p.stock < 5")
    public List<WarehouseStock> stockBreak();
}
