package com.myapp.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.demo.model.Product;
import com.myapp.demo.repo.ProductRepository;

@RestController
public class ProductAPI {

	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/products")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		productRepository.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@PostMapping("/products/bulkinsert")
	public ResponseEntity<List<Product>> saveAll(@RequestBody List<Product> products) {
		productRepository.saveAll(products);
		return new ResponseEntity<List<Product>>(products, HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productRepository.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/products/greaterthan/{price}")
	public ResponseEntity<List<Product>> findByPriceGreaterThan(@PathVariable("price") double price) {
		List<Product> products = productRepository.findByPriceGreaterThan(price);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@DeleteMapping("/products/delete/{id}")
	public ResponseEntity<List<Product>> delete(@PathVariable("id") Integer id) {
		productRepository.delete(productRepository.getOne(id));
		return new ResponseEntity<List<Product>>(productRepository.findAll(), HttpStatus.OK);
	}
	
}
