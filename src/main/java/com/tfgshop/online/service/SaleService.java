package com.tfgshop.online.service;

import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService implements ISaleService{

    @Autowired
    ISaleRepository saleRepository;

    @Override
    public List<Sale> listAllSales() {
    return saleRepository.findAll();
    }


    @Override
    public void addSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public List<Sale> findByYear(int year) {
        return saleRepository.findByYear(year);
    }

    @Override
    public List<Sale> findByWarehouse(Warehouse warehouse) {
        return saleRepository.findByWarehouse(warehouse);
    }

    @Override
    public List<Sale> findByWarehouseAndYear(int id, int year) {
        return saleRepository.findByWarehouseAndYear(id, year);
    }


}
