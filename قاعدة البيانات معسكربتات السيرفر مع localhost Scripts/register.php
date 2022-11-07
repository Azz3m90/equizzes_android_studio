<?php

require "init.php";
//$username = "Azzam";
//$password = "master";
//$tel = "963-991576641";


$username = $_POST["username"];
$password = $_POST["password"];
$tel = $_POST["tel"];
$isAd="False";
$logIn="False";

if($username!=""&&$password!=""&&$tel!=""){
$result = mysqli_query($conn ,"SELECT * FROM user where username='$username'");

if(mysqli_num_rows($result)>0) {

	echo nl2br("registration failed.\n");
	echo nl2br(" user name :$username is used ,try another\n");

}
else
{
$qry = "INSERT INTO user (username,tel,login,password,isAdmin) VALUES ('$username','$tel','$logIn','$password','$isAd')";

if(mysqli_query($conn ,$qry))
{
	
	echo nl2br("registration successfully.\n");
	echo nl2br("your user name is:$username.\n");
	echo nl2br("your password is:$password.\n");
	//echo "Data insertion success";
}
else{
	
	echo "Data insertion error".mysqli_error($conn);
}

}
}
else{
	echo "on or all of the fields is empty , please fill all the fields";
}
mysqli_close($conn);
?>