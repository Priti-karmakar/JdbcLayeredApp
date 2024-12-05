package com.aot.factory;

import com.aot.dao.EmployeeDao;
import com.aot.dao.EmployeeDaoImpl;

public class EmployeeDaoFactory {
	private static EmployeeDao empDao;
	static {
		empDao=new EmployeeDaoImpl();
	}
	public static EmployeeDao getEmployeeDao() {
		return empDao;
	}
}
