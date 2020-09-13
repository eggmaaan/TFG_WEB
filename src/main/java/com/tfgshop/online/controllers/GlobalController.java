package com.tfgshop.online.controllers;

import com.tfgshop.online.entities.Product;
import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;
import com.tfgshop.online.service.IProductService;
import com.tfgshop.online.service.ISaleService;
import com.tfgshop.online.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class GlobalController {

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ISaleService saleService;


    @RequestMapping("index")
    public ModelAndView index(ModelAndView model){
        model.addObject("pageTitle", "TFGSHOP");
        model.addObject("title", "Listado de opciones de la TFGSHOP");
        return model;
    }

    @RequestMapping("listWarehouses")
    public ModelAndView listWarehouses(ModelAndView model){
        List<Warehouse> warehouses = warehouseService.listAllWarehouses();
        model.addObject("pageTitle", "Almacenes");
        model.addObject("title", "Listado general de almacenes");
        model.addObject("list", warehouses);
        model.setViewName("public/listWarehouses");
        return model;
    }


    @RequestMapping({"listProducts"})
    public ModelAndView listProducts(ModelAndView model){

        List<Product> products = productService.listAllProducts();
        model.addObject("pageTitle", "Productos");
        model.addObject("title", "Listado general de Productos");
        model.addObject("list", products);
        model.setViewName("public/listProducts");
        return model;
    }

    @RequestMapping(value="selectByWarehouse/{id}", method= RequestMethod.GET)
    public ModelAndView selectByWarehouse(@PathVariable("id") int id, ModelAndView model){

        Warehouse warehouse = null;
        if(warehouseService.findById(id).isPresent()){
            warehouse = warehouseService.findById(id).get();
            System.out.println(warehouse);
        }
        Set<WarehouseStock> products = warehouse.getWarehouseStocks();
        for(WarehouseStock ware : products){
            System.out.println("Relacion -> " + products);
        }

        model.addObject("pageTitle", "Productos");
        model.addObject("title", "Listado Productos");
        model.addObject("list", products);
        model.setViewName("public/listProductsByWarehouse");
        return model;
    }


    @RequestMapping("listSales")
    public ModelAndView listSales(ModelAndView model){
        List<Sale> sales = saleService.listAllSales();
        List<Warehouse> warehouses = warehouseService.listAllWarehouses();

        model.addObject("pageTitle", "Ventas");
        model.addObject("title", "Listado general de ventas");
        model.addObject("list", sales);
        model.addObject("warehousesBox", warehouses);
        model.setViewName("public/listSales");
        return model;
    }

    @RequestMapping("find")
    public ModelAndView find(ModelAndView model, @RequestParam("idWarehouse") String idWarehouse,
                             @RequestParam("saleYear") String saleYear){

        List<Sale> sales = new ArrayList<>();
        List<Warehouse> warehouses = warehouseService.listAllWarehouses();
        Warehouse warehouse = null;

        int yearForm = 0;
        if(!saleYear.isEmpty()){
            yearForm = Integer.parseInt(saleYear);
        }
        int id = 0;
        if(!idWarehouse.isEmpty()){
            id = Integer.parseInt(idWarehouse);
        }

        if(yearForm > 0 && id > 0){
            warehouse = warehouseService.findById(id).get();
            sales = saleService.findByWarehouseAndYear(warehouse.getId(), yearForm);
        }else if(id > 0){
            warehouse = warehouseService.findById(id).get();
            sales = saleService.findByWarehouse(warehouse);
        }else if (yearForm > 0) {
            sales = saleService.findByYear(yearForm);
        }else{
            System.out.println("No ha buscado por nada");
        }





        model.addObject("pageTitle", "Ventas");
        model.addObject("title", "Listado general de ventas");
        model.addObject("list", sales);
        model.addObject("warehousesBox", warehouses);
        model.setViewName("public/listSales");

        return model;
    }


}
