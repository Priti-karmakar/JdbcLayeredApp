package com.aot.service;

import com.aot.beans.Employee;
import com.aot.dao.EmployeeDao;
import com.aot.factory.EmployeeDaoFactory;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public String addEmployee(Employee emp) {
		EmployeeDao empDao=EmployeeDaoFactory.getEmployeeDao();
		String status=empDao.add(emp);
		return status;
	}

	@Override
	public Employee searchEmployee(int eno) {
		EmployeeDao empDao=EmployeeDaoFactory.getEmployeeDao();
		Employee emp=empDao.search(eno);
		return emp;
	}

	@Override
	public String updateEmployee(Employee emp) {
		EmployeeDao empDao=EmployeeDaoFactory.getEmployeeDao();
		String status=empDao.update(emp);
		return status;
	}

	@Override
	public String deleteEmployee(int eno) {
		EmployeeDao empDao=EmployeeDaoFactory.getEmployeeDao();
		String status=empDao.delete(eno);
		return status;
	}

}
