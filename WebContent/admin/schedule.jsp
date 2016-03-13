<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Building Schedule</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
		<script type="text/javascript" charset="utf8" src="jquery/DataTables/jquery.dataTables.js"></script>
		<script type="text/javascript" charset="utf8" src="jquery/DataTables/dataTables.material.js"></script>
		<script type="text/javascript" charset="utf8" src="jquery/Responsive/js/dataTables.responsive.js"></script>
		<!-- Meta -->
        
        <meta name="description" content="">
        <meta name="author" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
       
        <!-- Template CSS -->
  		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="admin/table-schedule.css">
 		<link rel="stylesheet" type="text/css" href="jquery/DataTables/jquery.dataTables.css">
        <link rel="stylesheet" type="text/css" href="jquery/DataTables/dataTables.material.css">
        <link rel="stylesheet" type="text/css" href="jquery/Responsive/css/responsive.dataTables.css">
		
		<script> 
			// used to load header and footer html
			$(function() {
				$("#header").load("admin/adminheader.html"); 
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
		<div id="header"></div>
		<h2>${msg}</h2>
		<form name="BuildingSelect" action="Schedule" method="post">
			<p>${buildings}
			<label for="from">From </label> <input type="text" id="from" name="from" placeholder="mm/dd/yyyy"> <label for="to">To </label> <input type="text" id="to" name="to" placeholder="mm/dd/yyyy">
			<input name="enterBuilding" type="submit" value="Enter"></p>
		</form>
		<p>${schedule}</p>
		<div id="footer"></div>
	</body>
</html>