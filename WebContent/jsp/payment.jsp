<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>

<%@page import="java.util.Date,com.dbutil.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eyebankmanagement/css/styles.css">
<title>Confirm Payment</title>
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



<% 
HttpSession hs=request.getSession(false);
String uid=(String)hs.getAttribute("userdetails");
if(uid==null||hs.isNew())
             
      {
                  response.sendRedirect("/eyebankmanagement/html/login.html?msg=unauthorizedUser");	
                  
}
else
{

	int eyeid=Integer.parseInt(request.getParameter("msg"));
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
con=CrudeOperation.createConnection();
     String strsql="select * from eye where eyeid=?";
     ps=con.prepareStatement(strsql);
 	ps.setInt(1, eyeid);
 	
 	rs=ps.executeQuery();
     
     try
     {
     rs.next();

%>
<form action="/eyebankmanagement/Payment" method="post">
<table>
<tr><td>Eye ID</td><td><%=rs.getInt("eyeid")%></td></tr>
<tr><td>Donor ID</td><td><%=rs.getString("donorid")%></td></tr>
<tr><td>Price</td><td><%=rs.getString("price")%></td></tr>
<tr><td>Color</td><td><%=rs.getString("color")%></td></tr>

<tr><td><input type="hidden" value="<%=eyeid %>" name="eyeid" id="eyeid"></td></tr>
<tr><td colspan="2" ><input align="center" type="submit" value="Pay"></td></tr>
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


<p>${message}</p>
<c:remove var="message" scope="session" /> 



</body>
</html>