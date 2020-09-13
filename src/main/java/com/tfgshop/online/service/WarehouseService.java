package com.tfgshop.online.service;

import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService implements IWarehouseService{

    @Autowired
    IWarehouseRepository warehouseRepository;

    @Override
    public Optional<Warehouse> findById(int id) {
        Optional<Warehouse> war = Optional.of(new Warehouse());
            war = warehouseRepository.findById(id);
        return war;
    }

    @Override
    public List<Warehouse> listAllWarehouses() {
        return warehouseRepository.findAll();
    }

}
