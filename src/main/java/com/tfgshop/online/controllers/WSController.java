package com.tfgshop.online.controllers;

import com.tfgshop.online.entities.Product;
import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;
import com.tfgshop.online.service.IProductService;
import com.tfgshop.online.service.ISaleService;
import com.tfgshop.online.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class WSController {

    @Autowired
    ISaleService saleService;

    @GetMapping(path = "/totalSales",  produces = {MediaType.APPLICATION_XML_VALUE})
    public List<String> totalSales(){
        return saleService.totalSales();
    }

}
