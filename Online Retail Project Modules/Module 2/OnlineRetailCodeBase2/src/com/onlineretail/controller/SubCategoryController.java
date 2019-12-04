package com.onlineretail.controller;

import java.util.*;

import com.onlineretail.DAO.*;
import com.onlineretail.DAO.jdbc.*;
import com.onlineretail.exception.DuplicateSubCategoryException;
import com.onlineretail.model.*;
import com.onlineretail.service.*;
import com.onlineretail.service.impl.*;

public class SubCategoryController {
	public SubCategoryService subCategoryService;
	public SubCategoryDao subCategoryDao;
	public Scanner scanner;

	public SubCategoryController() {
		scanner = new Scanner(System.in);
		subCategoryService = new SubCategoryServiceImpl();
		subCategoryDao = new JdbcSubCategoryDao();
	}

	public String addSubCategory() throws Exception {
		String result = null;
		try {
			System.out.println("Add Sub Category");
			System.out
					.println("----------------------------------------------------");
			String Name, Description;
			int CategoryId, status;
			System.out.println("Enter Sub Category Name:  ");
			Name = scanner.nextLine();
			System.out.println("Enter Description:  ");
			Description = scanner.nextLine();
			findCategoryAll();
			do {
				System.out.println("Enter Category ID:  ");
				CategoryId = scanner.nextInt();
				status = subCategoryDao.findByCategoryId(CategoryId);
			} while (status == 0);
			if (status == 1) {
				if (subCategoryService.AddSubCategory(Name, Description,
						CategoryId) == 1) {
					result = "Sub Category Added";
				} else {
					result = "Sub Category Not Added";
				}
			}
		} catch (DuplicateSubCategoryException e) {
			result = "Sub Category Name Already Exists";
		}
		return result;

	}

	public void findAll() {
		List<SubCategory> subcategories = subCategoryService.findAll();
		Collections.sort(subcategories);
		System.out.println(String.format("%1$-6s", "ID") + String.format("%1$-20s","Name") 
				+ String.format("%1$-30s","Description") + "Category ID");
		for (SubCategory subcategory : subcategories) {
			if (subcategory.getSubcname().length() > 0) {
				System.out.println(subcategory.toString());
			}
		}
	}

	public int findSubCategoryNameDetails(String subCategoryName) {
		return subCategoryDao.findBySubCategoryName(subCategoryName);
	}

	public void subCategoryNameDetails(String subCategoryName) {
		List<SubCategory> subcategories = subCategoryService
				.findSubCategory(subCategoryName);
		Collections.sort(subcategories);
		System.out.println(String.format("%1$-6s", "ID") + String.format("%1$-20s","Name") 
				+ String.format("%1$-30s","Description") + "Category ID");
		for (SubCategory subcategory : subcategories) {
			System.out.println(subcategory.toString());
		}
	}

	public void deleteSubCategory(int subCategoryId) {
		subCategoryService.deleteSubCategory(subCategoryId);
	}

	public void findCategoryAll() {
		List<Category> catgeories = subCategoryService.findCategoryAll();
		System.out.println("ID \tName\t\tDescription");
		for (Category category : catgeories) {
			if (category.getCname().length() > 0) {
				System.out.print(category.getCid() + "\t");
				System.out.print(category.getCname() + "\t\t");
				System.out.println(category.getDescription());
			}
		}
	}

	
}
