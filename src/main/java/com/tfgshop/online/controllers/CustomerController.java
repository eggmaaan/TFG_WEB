package com.tfgshop.online.controllers;

import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;
import com.tfgshop.online.service.ISaleService;
import com.tfgshop.online.service.IWarehouseService;
import com.tfgshop.online.service.IWarehouseStockService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IWarehouseStockService warehouseStockService;

    @Autowired
    private ISaleService saleService;

    @RequestMapping("listWarehouses")
    public ModelAndView listWarehouses(ModelAndView model){
        List<Warehouse> warehouses = warehouseService.listAllWarehouses();
        model.addObject("pageTitle", "Almacenes");
        model.addObject("title", "Listado general de almacenes");
        model.addObject("list", warehouses);
        model.setViewName("customer/listWarehouses");
        return model;
    }


    @RequestMapping(value="listProductsByWarehouse/{id}", method= RequestMethod.GET)
    public ModelAndView listProductsByWarehouse(@PathVariable("id") int id, ModelAndView model){
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
        model.addObject("warehouse", warehouse);
        model.addObject("title", "Listado Productos");
        model.addObject("list", products);
        model.setViewName("customer/listProductsByWarehouse");
        return model;
    }

    @RequestMapping(value="saleProduct", method= RequestMethod.POST)
    public ModelAndView saleProduct(@RequestParam("id") int id,
                                    @RequestParam("productName") String productName,
                                    @RequestParam("warehouseName") String warehouseName,
                                    @RequestParam("stock") int stock, ModelAndView model){

        List errors = new ArrayList();

        if(id == 0){
            errors.add("El id no puede ser 0");
        }else{
            WarehouseStock ex = warehouseStockService.findById(id).get();

            if(ex.getWarehouse().getName().equals(warehouseName)){
                //mismo almacen

                if(ex.getProduct().getName().equals(productName)){
                    //mismo producto

                    if(ex.getStock()>= stock){
                        //el stock a comprar no puede ser mayor al que hay

                        Calendar c1 = Calendar.getInstance();

                        WarehouseStock updateStock = new WarehouseStock(ex.getId(),ex.getWarehouse(),ex.getProduct(),ex.getStock() - stock);
                        Sale saleForUser = new Sale();
                        saleForUser.setWarehouse(updateStock.getWarehouse());
                        saleForUser.setProduct(ex.getProduct());
                        saleForUser.setSaleDate(c1);
                        saleForUser.setUnits(stock);

                        warehouseStockService.save(updateStock);
                        saleService.addSale(saleForUser);

                        model.addObject("Exito", "Compra realizada con éxito");

                    }else{
                        errors.add("el stock a comprar no puede ser mayor al que hay");
                    }

                }else{
                    errors.add("El producto no es el mismo");
                }

            }else{
                errors.add("El almacen no se corresponde con el del producto");
            }

        }

            model.addObject("errors", errors);
            model.setViewName("customer/saleEnd");
        return model;
    }

    @RequestMapping(value="saleForm/{id}", method= RequestMethod.GET)
    public ModelAndView saleForm(@PathVariable("id") int id, ModelAndView model){

        WarehouseStock warehouseStock = warehouseStockService.findById(id).get();

        model.addObject("pageTitle", "Venta de Producto");
        model.addObject("warehouseStock", warehouseStock);
        model.addObject("stock", warehouseStock.getStock());
        model.addObject("title", "Listado Productos");
        model.setViewName("customer/saleForm");
        return model;
    }

}
