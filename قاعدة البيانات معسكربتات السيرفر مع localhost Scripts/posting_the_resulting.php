<?php

require "init.php";
//$username = "Azzam";
//$password = "master";
//$tel = "963-991576641";


$userid = $_POST["userid"];
$quizid = $_POST["quizid"];
$totalMarks = $_POST["totalMarks"];
$QDate = $_POST["QDate"];
$Time=$_POST["Time"];;

//$userid ='1';
//$quizid ='1';
//$totalMarks ='95';
//$QDate = 'QDate';
//$Time='Time';




$result = mysqli_query($conn ,"SELECT * FROM donequizzes where userid='$userid' And quizid='$quizid'");

if(mysqli_num_rows($result)>0) {
	

	echo nl2br("This quizzez have made before .\n");
	

}
else
{
$qry = "INSERT INTO donequizzes (userid,quizid,TotalMarks,QDate,Time) VALUES ('$userid','$quizid','$totalMarks','$QDate','$Time')";

if(mysqli_query($conn ,$qry))
{
	
	echo nl2br("insertion successfully. press back to make another quizzes\n");
$result1 = mysqli_query($conn ,"SELECT * FROM user where id='$userid'");
$result2 = mysqli_query($conn ,"SELECT * FROM quiz where id='$quizid'");

	$response = array();
	$response1= array();
if(mysqli_num_rows($result1)>0){
	
	$row = mysqli_fetch_assoc($result1);
		
		$response1=$row['username'];
		
}
	
	if(mysqli_num_rows($result2)>0){
	$row = mysqli_fetch_assoc($result2);
		
		$response2=$row['quiztype'];
	
	}
echo json_encode($response1);

}		
}




mysqli_close($conn);
?>