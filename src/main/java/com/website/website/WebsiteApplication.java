package com.website.website;

import com.website.website.repository.ProductAndSupplierInCartRepository;
import com.website.website.service.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsiteApplication
{
	@Autowired
	private Warehouse warehouse;

	@Autowired
	private ProductAndSupplierInCartRepository productAndSupplierInCartRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebsiteApplication.class, args);
	}
}
