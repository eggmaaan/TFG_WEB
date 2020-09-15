package com.tfgshop.online.service;

import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.webService.GlobalSale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<GlobalSale> allMethodsWS(String wsMethod) {

        List<GlobalSale> list = null;
        if(wsMethod.equals("totalSales")){
            list = createList(saleRepository.totalSales());

        }else if(wsMethod.equals("salesByWarehouse")){
            list = createList(saleRepository.salesByWarehouse());

        }else if(wsMethod.equals("salesByYear")){
            list = createList(saleRepository.salesByYear());

        }else if(wsMethod.equals("salesByTopProducts")){
            list = createList(saleRepository.salesByTopProducts());
        }

        return list;

    }


    private List<GlobalSale> createList(List<String> repo){
        List<GlobalSale> saleList = new ArrayList<>();

        for(String data : repo){
            String[] getData = data.split(",");
            GlobalSale globalSale = new GlobalSale(getData[0], Integer.parseInt(getData[1]), Double.parseDouble(getData[2]));
            saleList.add(globalSale);
        }

        return saleList;
    }

}
