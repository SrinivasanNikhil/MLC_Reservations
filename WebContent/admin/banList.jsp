<%-- 
@author: Ginger Nix (Ronnie Xu started - I fixed it and added lines
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Ban</title>
		<!-- JS -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
		<script type="text/javascript" charset="utf8" src="jquery/DataTables/jquery.dataTables.js"></script>
		<script type="text/javascript" charset="utf8" src="jquery/Responsive/js/dataTables.responsive.js"></script>
        
        <!-- CSS -->
  		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="admin/table-ban.css">
 		<link rel="stylesheet" type="text/css" href="jquery/DataTables/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="jquery/DataTables/dataTables.uikit.js">
        <link rel="stylesheet" type="text/css" href="jquery/Responsive/css/responsive.dataTables.css">
		<script> 
			// used to load header and footer html
			$(function() {
				$("#header1").load("admin/adminheader.html"); 
				$("#footer").load("footer.html"); 
			});
			
			// jQuery for datepicker plugin
			$(function() {
			    $( "#from" ).datepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      numberOfMonths: 3,
			      onClose: function( selectedDate ) {
			        $( "#to" ).datepicker( "option", "minDate", selectedDate );
			      }
			    });
			    $( "#to" ).datepicker({
			      defaultDate: "+1w",
			      changeMonth: true,
			      numberOfMonths: 3,
			      onClose: function( selectedDate ) {
			        $( "#from" ).datepicker( "option", "maxDate", selectedDate );
			      }
		    	});
			});
			// jQuery for Datatable plugin for pagination, sort, and search
			$(document).ready( function () {
				$('table.mdl-data-table').DataTable( { responsive: true,
					columnDefs: [
				             {
				                 targets: [ 0, 1, 2 ],
				                 className: 'mdl-data-table__cell--non-numeric'
				             }
				         ]
				});
			});
		</script> 
	</head>
	<body>
		<!-- Header -->
		<div id="header1"></div>
	
		<!-- Content -->
		<br><br>
			${message}
		<br>
		<div>
			${table}
		</div>
		<!-- End Content -->
		
		<!-- Footer -->
		<div id="footer"></div>
	</body>