package com.aot.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.aot.beans.Employee;
import com.aot.factory.ConnectionFactory;
import com.aot.factory.EmployeeServiceFactory;
import com.aot.service.EmployeeService;

public class JdbcLayeredApp {
	static {
		ConnectionFactory.getConnection();
	}
	public static void main(String[] args) {
		BufferedReader br=null;
		Employee emp=null;
		while(true) {
			System.out.println("1.Add Employee");
			System.out.println("2.Search Employee");
			System.out.println("3.Update Employee");
			System.out.println("4.Delete Employee");
			System.out.println("5. Exit");
			System.out.println("Enter your option:");
			int option;
			
			try {
				int eno=0;
				String ename="",eaddr="";
				float esal=0.0f;
				String status="";
				EmployeeService empService=EmployeeServiceFactory.getEmployeeService();
				br=new BufferedReader(new InputStreamReader(System.in));
				option=Integer.parseInt(br.readLine());
				switch(option) {
				case 1:
					System.out.println();
					System.out.println("=====Employee Add Module=====");
					System.out.print("Employee Number: ");
					eno=Integer.parseInt(br.readLine());
					System.out.print("Employee Name: ");
					ename=br.readLine();
					System.out.print("Employee Sal: ");
					esal=Float.parseFloat(br.readLine());
					System.out.print("Employee Address: ");
					eaddr=br.readLine();
					
					emp=new  Employee();
					emp.setEno(eno);
					emp.setEname(ename);
					emp.setEaddr(eaddr);
					emp.setEsal(esal);
					
					status=empService.addEmployee(emp);
					
					if(status.equalsIgnoreCase("success"))
						System.out.println("Status: Employee Inserted successfully");
					else if(status.equalsIgnoreCase("failure"))
						System.out.println("Status: Employee Insertion failure");
					else if(status.equalsIgnoreCase("existed"))
						System.out.println("Status: Employee Existed earlier");
					break;
				case 2:
					System.out.println("=====Employee Search Module=====");
					System.out.print("Employee Number: ");
					eno=Integer.parseInt(br.readLine());
					emp=empService.searchEmployee(eno);
					if(emp==null)
						System.out.println("Status: Employee not existed");
					else {
						System.out.println("Status: Employee existed");
						System.out.println("Employee number: "+emp.getEno());
						System.out.println("Employee name: "+emp.getEname());
						System.out.println("Employee sal: "+emp.getEsal());
						System.out.println("Employee address: "+emp.getEaddr());	
					}
					break;
				case 3:
					System.out.println("=====Employee Update Module=====");
					System.out.print("Employee Number: ");
					eno=Integer.parseInt(br.readLine());
					emp=empService.searchEmployee(eno);
					if(emp==null)
						System.out.println("Status: Employee not existed");
					else {
						Employee emp1=new Employee();
						emp1.setEno(eno);
						System.out.println("Employee Name[Old:"+emp.getEname()+"]New: ");
						String val1=br.readLine();
						if(val1==null||val1.equals(""))
							emp1.setEname(emp.getEname());
						else {
							emp1.setEname(val1);
						}
						System.out.println("Employee Salary[Old:"+emp.getEsal()+"]New: ");
						String val2=br.readLine();
						if(val2==null||val2.equals(""))
							emp1.setEsal(emp.getEsal());
						else {
							emp1.setEsal(Float.parseFloat(val2));
						}
						System.out.println("Employee Address[Old:"+emp.getEaddr()+"]New: ");
						String val3=br.readLine();
						if(val3==null||val3.equals(""))
							emp1.setEaddr(emp.getEaddr());
						else {
							emp1.setEaddr(val3);
						}
						status=empService.updateEmployee(emp1);
						if(status.equalsIgnoreCase("success"))
							System.out.println("Status: Employee updating successful");
						else
							System.out.println("Status: Employee updating failure");
					}
					break;
				case 4:
					System.out.println("=====Employee Delete Module=====");
					System.out.print("Employee Number: ");
					eno=Integer.parseInt(br.readLine());
					status=empService.deleteEmployee(eno);
					if(status.equalsIgnoreCase("success"))
						System.out.println("Status: Employee deleted successfully");
					else if(status.equalsIgnoreCase("failure"))
						System.out.println("Status: Employee deletion failed");
					else if(status.equalsIgnoreCase("not existed"))
						System.out.println("Status: Employee not Existed");
					break;
				case 5:
					System.out.println("*****Thank you*****");
					System.exit(0);
					break;
				default:
					System.out.println("Wrong Choice!!");
				
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
