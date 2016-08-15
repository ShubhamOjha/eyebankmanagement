package com.dbutil;
import java.sql.*;

public class CrudeOperation 
{
	private static ResultSet rs=null;
	private static Connection con=null;
	private static PreparedStatement ps=null;
	public static Connection createConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/eyebank","root","root");
		
		}

	catch(ClassNotFoundException |SQLException se)
		{
			System.out.println(se);
		} return con;
		
	
	
	}

	
	public static ResultSet getData(String sql,String userid)
	{
		con=createConnection();
		try{
			ps=con.prepareStatement(sql);
			ps.setString(1,userid);
			rs=ps.executeQuery();
		}catch(SQLException se)
		{
			System.out.println(se);
			
	
		}
	
	return (rs);
	}
	
	
	public static ResultSet getData1(String sql)
	{
		con= createConnection();
		try
		{
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
		}
		
		catch(SQLException se)
	
		{
			System.out.println(se);
		}
		
		
		return rs;}		

	
	
	
	
}

	
