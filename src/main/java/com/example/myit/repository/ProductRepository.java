package com.example.myit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myit.pojo.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
