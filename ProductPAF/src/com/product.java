
package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class product {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/strmgt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProduct(String proCode, String desc, String qty, String price, String category)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into product(`pro_ID`,`proCode`,`desc`,`qty`,`price`,`category`)"
					 + " values (?, ?, ?, ?, ?, ?)";
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, proCode);
			 preparedStmt.setString(3, desc);
			 preparedStmt.setString(4, qty);
			 preparedStmt.setString(5, price);
			 preparedStmt.setString(6, category);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newProduct = readProduct(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the product.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	
	public String readProduct()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Product Code</th><th>Description</th><th>Quantity</th><th>Price</th><th>Category</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from product";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String pro_ID = Integer.toString(rs.getInt("pro_ID"));
				String proCode = rs.getString("proCode");
				 String desc = rs.getString("desc");
				 String qty = rs.getString("qty");
				 String price = rs.getString("price");
				 String category = rs.getString("category");
			
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidProductIDUpdate\' name=\'hidProductIDUpdate\' type=\'hidden\' value=\'" + pro_ID + "' >" + proCode + "</td>"; 
				output += "<td>" + desc + "</td>";
				output += "<td>" + qty + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + category + "</td>";

				  
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-productid='" + pro_ID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	public String updateProduct(String pro_ID, String proCode, String desc, String qty, String price, String category)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE product p SET p.proCode=?,p.desc=?,p.qty=?,p.price=?,p.category=? WHERE p.pro_ID=?";
					   
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, proCode);
			 preparedStmt.setString(2, desc);
			 preparedStmt.setString(3, qty);
			 preparedStmt.setString(4, price);
			 preparedStmt.setString(5, category);
			 preparedStmt.setInt(6, Integer.parseInt(pro_ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newProduct = readProduct();    
			output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the product.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteProduct(String pro_ID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "delete from product where pro_ID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(pro_ID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newProduct = readProduct();    
			output = "{\"status\":\"success\", \"data\": \"" +  newProduct + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}

