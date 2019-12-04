package com.onlineretail.service.impl;

import java.util.List;

import com.onlineretail.DAO.*;
import com.onlineretail.DAO.jdbc.*;
import com.onlineretail.exception.DuplicateProductException;
import com.onlineretail.model.*;
import com.onlineretail.service.*;

public class ProductServiceImpl implements ProductService {

	private ProductDao productdao;

	public ProductServiceImpl() {
		productdao = new JdbcProductDao();
	}

	@Override
	public int AddProduct(String Name,String Description,double Cost,int Discount,int SubCategoryID,String Image) throws DuplicateProductException 
	{
		if (isDuplicateProductName(Name) == false) {
			Product product = new Product();
			product.setPname(Name);
			product.setPdescription(Description);
			product.setCost(Cost);
			product.setDiscount(Discount);
			product.setSid(SubCategoryID);
			product.setImage(Image);
			
			return productdao.Save(product);
		} else {
			throw new DuplicateProductException("Product Name Already Exists");
		}

	}

	@Override
	public boolean isDuplicateProductName(String productName) throws DuplicateProductException {
		boolean status = false;
		 if(productdao.isDuplicateProductName(productName) == true)
		 {	 
			 status = true;
			 throw new DuplicateProductException("Product Name Already Exists");
		 }
		 else
			 status = false;
		 
		 return status;
	}

	public List<Product> findAll() {
		return productdao.findAll();
	}

	@Override
	public List<Product> findProduct(String productName) {
		return productdao.findProduct(productName);
	}

	@Override
	public void deleteProduct(int productId) {
		productdao.deleteProduct(productId);
	}

	@Override
	public List<SubCategory> findSubCategoryAll() {
		return productdao.findAllSubCategory();
	}	

}
