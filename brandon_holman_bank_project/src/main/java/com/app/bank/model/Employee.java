package com.app.bank.model;

import java.util.Date;

public class Employee extends User{
//need to work on the relationship between User and Employee
	private int empId;
	
	public Employee() {
		
	}

	public Employee(int userId, String name, String email, char userType, String password, int phoneNumber,
			String country, String address, Date dateOfBirth, int empId) {
		super(userId, name, email, userType, password, phoneNumber, country, address, dateOfBirth);
		this.empId = empId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + "]";
	}
}
