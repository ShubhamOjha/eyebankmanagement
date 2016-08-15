<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.*,com.dbutil.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eyebankmanagement/css/styles.css">
<title>Get Eye</title>
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

{Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;
con=CrudeOperation.createConnection();
String strsql="select * from eye where status=?";

String status="false";

%> 

<table style=" margin:1px 0px 0px 390px; padding-left:2px; font-family:Arial Black;
 font-size:28px;">
<tr><th>Eye ID</th><th>Donor Id</th><th>Price</th><td>Color</td></tr>
<% 
try
{
	ps=con.prepareStatement(strsql);
	ps.setString(1, status);
	rs=ps.executeQuery();
	
	while(rs.next())
	{%>
<tr>
<th style="font-family:Adobe Caslon Pro; font-size:25px; color:#004700 "><%=rs.getString("eyeid")%></th>
<th style="font-family:Adobe Caslon Pro; font-size:25px; color:#004700 "><%=rs.getString("donorid")%></th>
<th style="font-family:Adobe Caslon Pro; font-size:25px; color:#004700"><%=rs.getInt("price")%></th>
<th style="font-family:Adobe Caslon Pro; font-size:25px; color:#004700"><a href="/eyebankmanagement/jsp/payment.jsp?msg=<%=rs.getInt("eyeid")%>"><%=rs.getString("color") %></a></th>
</tr>
<%} %>
</table>
<% }
	catch(SQLException se)
	
	{
		System.out.println(se);
	  
	}
	}%>






</div>

</body>
</html>