<%@ page import="java.sql.*,javax.servlet.*,com.dbutil.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eyebankmanagement/css/styles.css">
<title>View Profile</title>
</head>
<body>

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
Connection con=null;
PreparedStatement ps=null;
ResultSet rs=null;
HttpSession hs=request.getSession(false);
String uid=(String)hs.getAttribute("userdetails");
if(uid==null||hs.isNew())
{
	response.sendRedirect("/eyebankmanagement/html/login.html");
}
else
{
	String strsql="select * from recipient where recipientid=?";
	con=CrudeOperation.createConnection();
	try
	{
		ps=con.prepareStatement(strsql);
		ps.setString(1,uid);
		rs=ps.executeQuery();
		rs.next();%>
		<table>
		<tr><td>Name</td><td><%=rs.getString("name") %></td></tr>
		<tr><td>User id</td><td><%=rs.getString("recipientid") %></td></tr>
		<tr><td>Father's Name</td><td><%=rs.getString("fname") %></td></tr>
		<tr><td>Mother's Name</td><td><%=rs.getString("mname") %></td></tr>
		<tr><td>Email</td><td><%=rs.getString("email") %></td></tr>
		<tr><td>Phone No.</td><td><%=rs.getInt("phoneno") %></td></tr>
		<tr><td>Address</td><td><%=rs.getString("address") %></td></tr>
		<tr><td>Gender</td><td><%=rs.getString("gender") %></td></tr>
		<tr><td>DOB</td><td><%=rs.getDate("dob") %></td></tr>

		
		</table>
		<%
		}
	catch(SQLException se)
	{System.out.println(se);
	}
	finally{

		try {
				if(rs!=null)
					{
						rs.close();
					}
				
				if(ps!=null)
					{
						ps.close();
				
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