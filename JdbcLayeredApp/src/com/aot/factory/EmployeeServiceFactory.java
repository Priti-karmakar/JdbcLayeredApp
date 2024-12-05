package com.aot.factory;

import com.aot.service.EmployeeService;
import com.aot.service.EmployeeServiceImpl;

public class EmployeeServiceFactory {
	private static EmployeeService empService;
	static {
		empService=new EmployeeServiceImpl();
	}
	public static EmployeeService getEmployeeService() {
		return empService;
	}
}
