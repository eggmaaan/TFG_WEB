package com.tfgshop.online.repository;

import com.tfgshop.online.entities.Sale;
import com.tfgshop.online.entities.Warehouse;
import com.tfgshop.online.entities.webService.GlobalSale;
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

    /*
    WebService
    Ventas totales (unidades de productos y €)
     */
    @Query(value = "SELECT B.NAME AS PRODUCTO,SUM(UNITS) UNIDADES, TRUNCATE(SUM(UNITS)*B.PRICE,2) BENEFICIOS " +
            "FROM SALE A, PRODUCTS B WHERE A.ID_PRODUCT = B.ID GROUP BY ID_PRODUCT", nativeQuery = true)
    public List<String> totalSales();

    @Query(value = "SELECT C.NAME ALMACEN, SUM(UNITS) UNIDADES, TRUNCATE(SUM(UNITS)*B.PRICE,2) BENEFICIOS " +
            "FROM SALE A, PRODUCTS B, WAREHOUSE C WHERE A.ID_WAREHOUSE = C.ID AND B.ID = A.ID_PRODUCT GROUP BY ID_WAREHOUSE ", nativeQuery = true)
    public List<String> salesByWarehouse();

    @Query(value = "SELECT YEAR(SALE_DATE) AS YEAR, SUM(UNITS) AS UNIDADES, TRUNCATE(SUM(UNITS)*B.PRICE,2) AS BENEFICIOS " +
            "FROM SALE A, PRODUCTS B WHERE A.ID_PRODUCT = B.ID GROUP BY YEAR(SALE_DATE)", nativeQuery = true)
    public List<String> salesByYear();

    @Query(value = "SELECT B.NAME PRODUCTO, SUM(UNITS) UNIDADES, TRUNCATE(SUM(UNITS)*B.PRICE,2) BENEFICIOS " +
            "FROM SALE A, PRODUCTS B WHERE A.ID_PRODUCT = B.ID GROUP BY A.ID_PRODUCT ORDER BY UNIDADES DESC limit 10", nativeQuery = true)
    public List<String> salesByTopProducts();



}

