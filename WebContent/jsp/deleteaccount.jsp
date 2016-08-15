<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.*,com.dbutil.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eyebankmanagement/css/styles.css">
<title>DELETE USER</title>
</head>
<body class="top">

<div class="top-bar" width="400px"><a href="/Medicopedia/html/home.html">Eye Bank MAnagement System

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
{%>

<!-- HEADING -->
<div style=" padding:10px 0 10px 520px; font-size:20px; color:#CC2900; font-family:Adobe caslon pro;">
<h1>DELETE USERS</h1> 
</div>



<% 
Connection con=null;
con=CrudeOperation.createConnection();
String strsql="select * from logindetails where usertype!='admin'";

ResultSet rs=CrudeOperation.getData1(strsql);

%> 
<form method="post" action="/eyebankmanagement/DeleteAccount">
<table style=" margin:1px 0px 0px 390px; padding-left:2px; font-family:Arial Black;
 font-size:28px;">
<tr><th>Select</th><th>UserId</th><th>Password</th><td>User-Type</td></tr>
<% 
try
{
	while(rs.next())
	{%>
<tr><th><input type="checkbox" name="chk" value="<%=rs.getString("userid") %>"></th>
<th style="font-family:Adobe Caslon Pro; font-size:25px; color:#004700 "><%=rs.getString("userid")%></th>
<th style="font-family:Adobe Caslon Pro; font-size:25px; color:#004700"><%=rs.getString("userpass")%></th>
<th style="font-family:Adobe Caslon Pro; font-size:25px; color:#004700"><%=rs.getString("usertype")%></th>
</tr>
<%} %>
</table>
<table>	
<tr><td colspan="4"> <input type="submit" value="Delete" style="width:150px; height:50px; margin:30px 200px 30px 640px; 
background-color:#00CCFF; font-family:Arial Black;padding:2px;font-size:30px;"></td></tr>
</table>
<% }
	catch(SQLException se)
	
	{
		System.out.println(se);
	  
	}%>

</form>	
</div>
</body>
<%} %>
</html>