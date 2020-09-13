package com.tfgshop.online.controllers;

import com.tfgshop.online.entities.Product;
import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;
import com.tfgshop.online.service.IProductService;
import com.tfgshop.online.service.ISaleService;
import com.tfgshop.online.service.IWarehouseService;
import com.tfgshop.online.service.IWarehouseStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IWarehouseStockService warehouseStockService;

    @Autowired
    private IProductService productService;

    @RequestMapping("index")
    public ModelAndView index(ModelAndView model){
        model.addObject("pageTitle", "TFGSHOP");
        model.addObject("title", "Panel de gestion del Stock");
        model.setViewName("warehouse/indexWarehouse");
        return model;
    }



    @RequestMapping("restore")
    public ModelAndView listWarehouses(ModelAndView model){
        List<Warehouse> warehouses = warehouseService.listAllWarehouses();
        List<Product> products = productService.listAllProducts();
        model.addObject("pageTitle", "Almacenes");
        model.addObject("title", "Gestión del Stock");
        model.addObject("warehouses", warehouses);
        model.addObject("products", products);
        model.setViewName("warehouse/restoreStock");
        return model;
    }

    @RequestMapping("restore/{id}")
    public ModelAndView listWarehouses(ModelAndView model, @PathVariable("id") int id){
        List<String> errors = new ArrayList<>();
        try{
            WarehouseStock warehouseStock = warehouseStockService.findById(id).get();

            model.addObject("pageTitle", "Almacenes");
            model.addObject("title", "Gestión del Stock");
            model.addObject("warehouseStock", warehouseStock);
            model.setViewName("warehouse/restoreStockId");

        }catch (Exception e){
            errors.add("NO se encuentra el producto");
            model.addObject("errors", errors);
            model.setViewName("warehouse/updateEnd");
        }

        return model;
    }

    @RequestMapping("updateStock")
    public ModelAndView updateStock(ModelAndView model, @RequestParam("idWarehouse") int idWarehouse,
                                                        @RequestParam("idProduct") int idProduct,
                                                        @RequestParam("stock") String stock){
        List errors = new ArrayList();

        int stockParse = 0;

        try{
            stockParse  = Integer.parseInt(stock);
        }catch(Exception e){
            errors.add("El stock debe de ser numerico");
        }

        if(stockParse == 0){
            errors.add("El stock no puede ser 0");

        }else{

            Warehouse warehouse = warehouseService.findById(idWarehouse).get();
            Product product = productService.findById(idProduct).get();
            WarehouseStock warehouseStock = new WarehouseStock();

            try{
                warehouseStock = warehouseStockService.findByWaAndPro(warehouse, product).get();
                warehouseStock.setStock(warehouseStock.getStock() + stockParse);
            }catch(Exception e){

                warehouseStock.setWarehouse(warehouse);
                warehouseStock.setProduct(product);
                warehouseStock.setStock(stockParse);

            }
            warehouseStockService.save(warehouseStock);

        }
        model.addObject("errors", errors);
        model.setViewName("warehouse/updateEnd");
        return model;
    }
    @RequestMapping("listStockBreak")
    public ModelAndView listStockBreak(ModelAndView model){
        List<WarehouseStock> warehouseStocks = warehouseStockService.stockBreak();
        model.addObject("pageTitle", "Rotura Stock");
        model.addObject("title", "Listado de productos en rotura de Stock");
        model.addObject("list", warehouseStocks);
        model.setViewName("warehouse/listStockBreak");
        return model;

    }


}
