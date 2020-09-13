package com.tfgshop.online.service;

import com.tfgshop.online.entities.Warehouse;

import java.util.List;
import java.util.Optional;

public interface IWarehouseService {
    public Optional<Warehouse> findById(int id);
    public List<Warehouse> listAllWarehouses();
}
