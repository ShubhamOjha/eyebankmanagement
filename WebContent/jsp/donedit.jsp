<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>

<%@page import="java.util.Date,com.dbutil.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eyebankmanagement/css/styles.css">
<title>Edit Profile</title>
</head>

<body class="top">

<div class="top-bar" width="400px"><a href="/Medicopedia/html/home.html">Eye Bank Management System

</a>
</div>
<div class="sidebar">
<br>
<br>
<a href="/eyebankmanagement/html/login.html">Login</a>
<br><br>
<a href="/eyebankmanagement/html/donreg.html">Register as Donor</a>
<br><br>
<a href="/eyebankmanagement/html/recreg.html">Register as Recipient</a>


</div>

<div class="menu">
<div class="holder">
<ul>
<li><a href="/eyebankmanagement/html/AboutUs.html" >ABOUT US</a></li>
<li><a href="/eyebankmanagement/html/ContactUs.html">CONTACT US</a></li>
<li></li>
</ul>

</div >


</div>


<div class="cbody" style="float: left;">



<% 
HttpSession hs=request.getSession(false);
String uid=(String)hs.getAttribute("userdetails");
if(uid==null||hs.isNew())
             
      {
                  response.sendRedirect("/eyebankmanagement/html/login.html?msg=unauthorizedUser");	
                  
}
else
{
     String strsql="select * from donor where donorid=?";
     ResultSet rs=CrudeOperation.getData(strsql,uid);
     try
     {
     rs.next();

%>
<form action="/eyebankmanagement/DonEdit" method="post">
<table>
<tr><td>Email id</td><td><input type="text" value="<%=rs.getString("email")%>" name="txtem" ></td>
<tr><td>Phone No.</td><td><input type="text" value="<%=rs.getInt("phoneno")%>" name="txtph" ></td>
<tr><td>Address</td><td><input type="text" value="<%=rs.getString("address")%>" name="add" ></td>
<tr><td>Father's Name</td><td><input type="text" value="<%=rs.getString("fname")%>" name="ftnm" ></td>
<tr><td>Mother's Name</td><td><input type="text" value="<%=rs.getString("mname")%>" name="mtnm" ></td>
<tr><td><input type="hidden" value"<%=uid%>"name="txtuid" ></td></tr>
<tr><td colspan="2" ><input align="center" type="submit" value="submit"></td></tr>
</table>
</form>
<%	}
 	catch(SQLException se)
 	{System.out.println(se);
 	}
 	finally{

 		try {
 				if(rs!=null)
 					{
 						rs.close();
 					}
 				
 				
 			}
 		
 		catch(SQLException se)
 		{ 
 			System.out.println(se);
 		}
 	}	
 }

%>



</div>



</body>
</html>