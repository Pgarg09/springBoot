package com.spring.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.spring.stockmarket.bean.GBCEDataModelSingleton;

@SpringBootApplication
@ComponentScan(basePackages = { "com.*"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//Initializing the values for GBCE Stock's Data
		GBCEDataModelSingleton.getStockDataModel();
	}

}
