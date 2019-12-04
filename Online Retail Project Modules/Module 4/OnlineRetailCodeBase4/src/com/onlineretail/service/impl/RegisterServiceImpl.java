package com.onlineretail.service.impl;

import java.sql.Date;
import java.util.List;

import com.onlineretail.DAO.*;
import com.onlineretail.DAO.jdbc.*;
import com.onlineretail.exception.DuplicateNameException;
import com.onlineretail.model.*;
import com.onlineretail.service.*;

public class RegisterServiceImpl implements RegisterService {

	private RegisterDao registerdao;

	public RegisterServiceImpl() {
		registerdao = new JdbcRegisterDao();
	}

	@Override
	public int AddRegister(String UserName, String Password, String PhNo,
			String Gender, String City, String Country, Date regDate)
			throws DuplicateNameException {
		if (isDuplicateUserName(UserName) == false) {

			Registration register = new Registration();
			register.setUsername(UserName);
			register.setPassword(Password);
			register.setPhnnumber(PhNo);
			register.setGender(Gender);
			register.setCity(City);
			register.setCountry(Country);
			register.setRegistereddate(regDate);

			return registerdao.Save(register);
		} else {
			throw new DuplicateNameException("User Name Already Exists");
		}

	}

	@Override
	public boolean isDuplicateUserName(String userName) throws DuplicateNameException {
		boolean status = false;
		 if(registerdao.isDuplicateUserName(userName) == true)
		 {	 
			 status = true;
			 throw new DuplicateNameException("User Name Already Exists");
		 }
		 else
			 status = false;
		 
		 return status;
	}
	
	public List<Registration> findAll() {
		return registerdao.findAll();
	}

	@Override
	public void deleteRegistration(int Id) {
		registerdao.deleteRegistration(Id);
	}	

}
