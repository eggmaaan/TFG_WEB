package com.tfgshop.online.service;

import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleService {
    public List<Sale> listAllSales();
    public void addSale(Sale sale);
    public List<Sale> findByYear(int year);
    public List<Sale> findByWarehouse(Warehouse warehouse);
    public List<Sale> findByWarehouseAndYear(int id, int year);
}
