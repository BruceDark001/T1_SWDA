package com.ecommerce.repository;

import com.ecommerce.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaSegura(@Param("categoria") String categoria);
}