package com.aot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.aot.beans.Employee;
import com.aot.factory.ConnectionFactory;

public class EmployeeDaoImpl implements EmployeeDao{
	@Override
	public String add(Employee emp) {
		Statement st=null;
		Connection con=null;
		ResultSet rs=null;
		String status="";
		try {
			con=ConnectionFactory.getConnection();
			if(con!=null)
				st=con.createStatement();
			if(st!=null)
				rs=st.executeQuery("select * from emp1 where eno="+emp.getEno());
			boolean b=rs.next();
			if(b==true)
				status="Existed";
			else {
				int rowCount=st.executeUpdate("insert into emp1 values("+emp.getEno()+",'"+emp.getEname()+"',"+emp.getEsal()+",'"+emp.getEaddr()+"')");
				if(rowCount==1)
					status="success";
				else
					status="failure";
			
			}
		}catch(Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public Employee search(int eno) {
		Employee emp=null;
		Statement st=null;
		Connection con=null;
		ResultSet rs=null;
		try {
			con=ConnectionFactory.getConnection();
			if(con!=null)
				st=con.createStatement();
			if(st!=null)
				rs=st.executeQuery("select * from emp1 where eno="+eno);
			if(rs!=null) {
				boolean b=rs.next();
				if(b==true) {
					emp=new Employee();
					emp.setEno(rs.getInt("Eno"));
					emp.setEname(rs.getString("Ename"));
					emp.setEsal(rs.getFloat("Esal"));
					emp.setEaddr(rs.getString("Eaddr"));
				}
					
				else {
					emp=null;
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	@Override
	public String update(Employee emp) {
		Connection con=null;
		Statement st=null;
		String status=null;
		
		try {
			con=ConnectionFactory.getConnection();
			if(con!=null)
				st=con.createStatement();
			int rowCount=0;
			if(st!=null)
				rowCount=st.executeUpdate("update emp1 set ename='"+emp.getEname()+"',esal="+emp.getEsal()+",eaddr='"+emp.getEaddr()+"' where eno="+emp.getEno());
			if(rowCount==1)
				status="success";
			else
				status="Failure";
		}catch(Exception e) {
			status="Failure";
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public String delete(int eno) {
		Statement st=null;
		Connection con=null;
		String status="";
		int count=0;
		try {
			con=ConnectionFactory.getConnection();
			if(con!=null)
				st=con.createStatement();
			Employee emp=search(eno);
			if(emp==null)
				status="Status: Not existed";
			else {
				if(st!=null)
					count=st.executeUpdate("delete from emp1 where eno="+eno);
				if(count==1)
					status="sucess";
				else
					status="failure";
			}
		}catch(Exception e) {
			status="failure";
			e.printStackTrace();
		}
		return status;
	}
}
