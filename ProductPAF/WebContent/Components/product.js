$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateProductForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidProductIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "ProductAPI",  
			type : type,  
			data : $("#formProduct").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onProductSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onProductSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divProductGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidProductIDSave").val("");  
	$("#formProduct")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidProductIDSave").val($(this).closest("tr").find('#hidProductIDUpdate').val());     
	$("#proCode").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#desc").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#qty").val($(this).closest("tr").find('td:eq(2)').text());
	$("#price").val($(this).closest("tr").find('td:eq(3)').text());  
	$("#category").val($(this).closest("tr").find('td:eq(4)').text());     
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "ProductAPI",   
		type : "DELETE",   
		data : "pro_ID=" + $(this).data("productid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onProductDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onProductDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divProductGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateProductForm() 
{  
	// proCode  
	if ($("#proCode").val().trim() == "")  
	{   
		return "Insert Pcode.";  
	} 

	// DESCRIPTION------------------------  
	if ($("#desc").val().trim() == "")  
	{   
		return "Insert desc.";  
	}
	// qty------------------------  
	if ($("#qty").val().trim() == "")  
	{   
		return "Insert qty.";  
	}  
		
	//price-------------------------------
	 var tmpAmount = $("#price").val().trim();
	if (!$.isNumeric(tmpAmount)) 
	 {
	 return "Insert price.";
	 }
// category------------------------  
	if ($("#category").val().trim() == "")  
	{   
		return "Insert category.";  
	}

	return true; 
}