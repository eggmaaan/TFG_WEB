package com.tfgshop.online.entities;

import javax.persistence.*;

@Entity
@Table(name="WAREHOUSESTOCK")
public class WarehouseStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    @JoinColumn(name = "ID_WAREHOUSE")
    private Warehouse warehouse;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    @JoinColumn(name = "ID_PRODUCT")
    private Product product;

    @Column(name="STOCK")
    private int stock;


    public WarehouseStock() {

    }

    public WarehouseStock(Warehouse warehouse, Product product, int stock) {
        this.warehouse = warehouse;
        this.product = product;
        this.stock = stock;
    }

    public WarehouseStock(int id, Warehouse warehouse, Product product, int stock) {
        this.id = id;
        this.warehouse = warehouse;
        this.product = product;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "WarehouseStock{" +
                "id=" + id +
                ", warehouse=" + warehouse +
                ", product=" + product +
                ", stock=" + stock +
                '}';
    }
}
