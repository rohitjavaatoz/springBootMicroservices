package com.example.myit.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.myit.helper.Helper;
import com.example.myit.pojo.Product;
import com.example.myit.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException{
		if(Helper.checkExcelFormat(file))
		{
			this.service.save(file);
			return ResponseEntity.ok(Map.of("message","File is uploaded and data is saved in db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload correct excel formate");
		
	}
	
	@GetMapping("/product")
	public List<Product> getAllProduct(){
		return this.service.getAllProducts();
	}
	
}
