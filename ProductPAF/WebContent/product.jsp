<%@ page import="com.product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/product.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Product SERVICE</h1>
				<form id="formProduct" name="formProduct" method="post" action="product.jsp">  
					Product Code:  
 	 				<input id="proCode" name="proCode" type="text"  class="form-control form-control-sm">
					<br> Description:   
  					<input id="desc" name="desc" type="text" class="form-control form-control-sm">   
  					<br> Quantity:   
  					<input id="qty" name="qty" type="text"  class="form-control form-control-sm">
					<br>Price:
					<input id="price" name="price" type="text"  class="form-control form-control-sm">
					<br>Category:
					<input id="category" name="category" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divProductGrid">
					<%
					    product productObj = new product();
						out.print(productObj.readProduct());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>