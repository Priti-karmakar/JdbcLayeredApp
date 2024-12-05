package com.aot.service;

import com.aot.beans.Employee;

public interface EmployeeService {
	public String addEmployee(Employee emp);
	public Employee searchEmployee(int eno);
	public String updateEmployee(Employee emp);
	public String deleteEmployee(int eno);
}
