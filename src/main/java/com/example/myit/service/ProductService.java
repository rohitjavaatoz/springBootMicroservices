package com.example.myit.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.myit.helper.Helper;
import com.example.myit.pojo.Product;
import com.example.myit.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void save(MultipartFile file) throws IOException {
		Helper help= new Helper();
	List<Product> product=	help.convertListToProduct(file.getInputStream());
	this.productRepository.saveAll(product);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
}
