package com.onlineretail.controller;

import java.io.*;
import java.util.*;

import com.onlineretail.DAO.*;
import com.onlineretail.DAO.jdbc.*;
import com.onlineretail.exception.DuplicateProductException;
import com.onlineretail.model.*;
import com.onlineretail.service.*;
import com.onlineretail.service.impl.*;

public class ProductController {
	public ProductService productService;
	public ProductDao productDao;
	public Scanner scanner;

	public ProductController() {
		productService = new ProductServiceImpl();
		productDao = new JdbcProductDao();
		scanner = new Scanner(System.in);
	}

	public String addProduct() throws Exception {
		int status;
		String result = null;
		try {
			System.out.println("Add Product");
			System.out
					.println("----------------------------------------------------");
			String Name, Description, Image;
			double Cost;
			int Discount, sID;

			System.out.println("Enter Product Name:  ");
			Name = scanner.nextLine();
			System.out.println("Enter Description:  ");
			Description = scanner.nextLine();
			System.out.println("Enter Price:  ");
			Cost = scanner.nextDouble();
			System.out.println("Enter Discount:  ");
			Discount = scanner.nextInt();
			findSubCategoryAll();
			do {
				System.out.println("Enter Sub Category ID:  ");
				sID = scanner.nextInt();
				status = productDao.findBySubCategoryId_Product(sID);
			} while (status == 0);
			scanner.nextLine();
			System.out.println("Enter Image:  ");
			Image = scanner.nextLine();
			if (status == 1) {
				if (productService.AddProduct(Name, Description, Cost,
						Discount, sID, Image) == 1) {
					result = "Product Added";
				} else {
					result = "Product Not Added";
				}
			}
		} catch (DuplicateProductException e) {
			result = "Product Name Already Exists";
		}
		return result;
	}

	public void findAll() {
		List<Product> products = productService.findAll();
		Collections.sort(products);
		System.out
			.println(String.format("%1$-6s", "ID") + String.format("%1$-20s", "Name")
				+ String.format("%1$-20s", "Description") + String.format("%1$-10s", "Price")
					+ String.format("%1$-10s", "Discount") + String.format("%1$-18s", "Sub Category ID") + "Image");
		for (Product product : products) {
			if (product.getPname().length() > 0) {
				System.out.println(product.toString());
			}
		}
	}

	public int findProductNameDetails(String productName) {
		return productDao.findProductName(productName);
	}
	
	public void ProductNameDetails(String productName) {
			List<Product> products = productService.findProduct(productName);
			System.out.println(String.format("%1$-6s", "ID") + String.format("%1$-20s", "Name")
				+ String.format("%1$-20s", "Description") + String.format("%1$-10s", "Price")
				+ String.format("%1$-10s", "Discount") + String.format("%1$-18s", "Sub Category ID") + "Image");
			
			for (Product product : products) {
				System.out.println(product.toString());
			}
	}
	
	public void deleteProduct(int productId) {
		productService.deleteProduct(productId);
	}

	
	public void findSubCategoryAll() {
		List<SubCategory> subcategories = productService.findSubCategoryAll();
		System.out.println("ID \tName\t\tDescription\t\tCategory ID");
		for (SubCategory subcategory : subcategories) {
			if (subcategory.getSubcname().length() > 0) {
				System.out.print(subcategory.getSid() + "\t");
				System.out.print(subcategory.getSubcname() + "\t\t");
				System.out.print(subcategory.getSdescription() + "\t\t");
				System.out.println(subcategory.getCid());
			}
		}
	}

	
}
