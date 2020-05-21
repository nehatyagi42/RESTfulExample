package com.quarntine.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.quarntine.model.Customer;
public class AuthService {
	
	 private HibernateTemplate hibernateTemplate;
	 
	
	private static Logger log = Logger.getLogger(AuthService.class);

	private AuthService() { }

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings( { "unchecked", "deprecation" } )
	public boolean findCustomer(String customername,String customerpassword) {
		log.info("Checking the user in the database");
		boolean isValidUser = false;
		String sqlQuery = "from Customers customer where customer.name=? and customer.password=?";
		try {
			List<Customer> customerlist = (List<Customer>) hibernateTemplate.find(sqlQuery, customername, customerpassword);
			if(customerlist != null && customerlist.size() > 0) {
				log.info("Id= " + customerlist.get(0).getId() + ", Name= " + customerlist.get(0).getName() + ", Password= " + customerlist.get(0).getPassword());
				isValidUser = true;
			}
		} catch(Exception e) {
			isValidUser = false;
			log.error("An error occurred while fetching the user details from the database", e);	
		}
		return isValidUser;
	}
	}

