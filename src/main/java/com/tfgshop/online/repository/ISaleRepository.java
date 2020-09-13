package com.tfgshop.online.repository;

import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleRepository extends JpaRepository<Sale, Integer> {

    @Query(value ="SELECT * FROM Sale WHERE YEAR(SALE_DATE) = :year", nativeQuery = true)
    public List<Sale> findByYear(@Param("year") int year);

    @Query("SELECT S FROM Sale S WHERE S.warehouse = :warehouse")
    public List<Sale> findByWarehouse(@Param("warehouse") Warehouse warehouse);

    @Query(value = "SELECT * FROM Sale WHERE id_warehouse = :id and YEAR(SALE_DATE) = :year", nativeQuery = true)
    public List<Sale> findByWarehouseAndYear(@Param("id") int id, @Param("year") int year);
}
