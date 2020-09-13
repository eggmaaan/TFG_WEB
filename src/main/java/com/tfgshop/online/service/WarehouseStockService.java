package com.tfgshop.online.service;

import com.tfgshop.online.entities.Product;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;
import com.tfgshop.online.repository.IWarehouseStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseStockService implements IWarehouseStockService{

    @Autowired
    IWarehouseStockRepository warehouseStockRepository;

    @Override
    public List<WarehouseStock> listAllProductsByWarehouse() {
        return warehouseStockRepository.findAll();
    }

    @Override
    public Optional<WarehouseStock> findById(int id) {
       return warehouseStockRepository.findById(id);
    }

    @Override
    public Optional<WarehouseStock> findByWaAndPro(Warehouse warehouse, Product product) {
        return warehouseStockRepository.findByWaAndPro(warehouse,product);
    }

    @Override
    public void save(WarehouseStock warehouseStock) {
        warehouseStockRepository.save(warehouseStock);
    }

    @Override
    public List<WarehouseStock> stockBreak() {
        return warehouseStockRepository.stockBreak();
    }


}
