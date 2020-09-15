package com.tfgshop.online.controllers;

import com.tfgshop.online.entities.webService.GlobalSale;
import com.tfgshop.online.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class WSController {

    @Autowired
    ISaleService saleService;

    @GetMapping(path = "/totalSales",  produces = {MediaType.APPLICATION_XML_VALUE})
    public List<GlobalSale> totalSales(){
        String wsMethod = "totalSales";
        return saleService.allMethodsWS(wsMethod);
    }
    @GetMapping(path = "/salesByWarehouse",  produces = {MediaType.APPLICATION_XML_VALUE})
    public List<GlobalSale> salesByWarehouse(){
        String wsMethod = "salesByWarehouse";
        return saleService.allMethodsWS(wsMethod);
    }
    @GetMapping(path = "/salesByYear",  produces = {MediaType.APPLICATION_XML_VALUE})
    public List<GlobalSale> salesByYear(){
        String wsMethod = "salesByYear";
        return saleService.allMethodsWS(wsMethod);
    }
    @GetMapping(path = "/salesByTopProducts",  produces = {MediaType.APPLICATION_XML_VALUE})
    public List<GlobalSale> salesByTopProducts(){
        String wsMethod = "salesByTopProducts";
        return saleService.allMethodsWS(wsMethod);
    }


}
