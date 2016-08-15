<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/eyebankmanagement/css/styles.css">
<title>Donate Eye</title>

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
	response.sendRedirect("/eyebankmanagement/html/login.html");
}
else
{%>
<form action="/eyebankmanagement/Donate" method="post">


<table>
<tr><td>Asking Price</td><td><input type="number" name="price" id="price"></td></tr>
<tr><td><input type="hidden" value="<%=uid%>"name="txtuid" ></td></tr>
<tr><td><input type="submit" value="submit"></td><td><input type="reset" value="reset"></td></tr>

<%} %>





</table>







</form>



<p>${message}</p>
<c:remove var="message" scope="session" /> 



</div>



</body>
</html>