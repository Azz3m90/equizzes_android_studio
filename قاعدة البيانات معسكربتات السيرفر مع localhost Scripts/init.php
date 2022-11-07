<?php
 

 
$db_name="eq";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";
 
$conn = mysqli_connect($server_name,$mysql_user, $mysql_pass,$db_name);
 
 
 
// Check connection
 
if (!$conn) {
 
   die("Connection failed: " . mysqli_connect_error());
 
}
 
//echo nl2br("Connected successfully .\n");
 
?>