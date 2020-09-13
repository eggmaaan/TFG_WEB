package com.tfgshop.online.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="WAREHOUSE")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="LOCATION")
    private String location;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "warehouse",cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    private Set<WarehouseStock> WarehouseStocks;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "warehouse",cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REMOVE,CascadeType.REFRESH})
    private Set<Sale> sales;

    public Warehouse(){

    }

    public Warehouse(String name, String location){
        this.name = name;
        this.location = location;

    }

    public Warehouse(int id, String name, String location){
        this.id = id;
        this.name = name;
        this.location = location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
