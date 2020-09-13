package com.tfgshop.online.service;

import com.tfgshop.online.entities.Product;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;

import java.util.List;
import java.util.Optional;

public interface IWarehouseStockService {
    public List<WarehouseStock> listAllProductsByWarehouse();
    public Optional<WarehouseStock> findById(int id);
    public Optional<WarehouseStock> findByWaAndPro(Warehouse warehouse, Product product);
    public void save(WarehouseStock warehouseStock);
    public List<WarehouseStock> stockBreak();
}
