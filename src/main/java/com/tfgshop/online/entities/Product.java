package com.tfgshop.online.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="PRODUCTS")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product",cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    private Set<WarehouseStock> WarehouseStocks;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product",cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    private Set<Sale> sales;

    public Product(){

    }

    public Product(String name, double price){
        this.name = name;
        this.price = price;

    }

    public Product(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<WarehouseStock> getWarehouseStocks() {
        return WarehouseStocks;
    }

    public void setWarehouseStocks(Set<WarehouseStock> warehouseStocks) {
        WarehouseStocks = warehouseStocks;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
