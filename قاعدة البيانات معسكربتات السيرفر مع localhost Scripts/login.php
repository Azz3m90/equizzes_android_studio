<?php 

require "init.php";

$Username = $_POST["username"];

$password = $_POST["password"];
//$Username = "Azzam";
//$password = "master";

$UserID=0;



$qry = "SELECT * FROM user where username='$Username' AND
password='$password'";

$result = mysqli_query($conn ,$qry);

 
$response = array();
if(mysqli_num_rows($result)>0){
$qry = "SELECT * FROM user where username='$Username' AND
login='False'";
$testLogin= mysqli_query($conn ,$qry);
if(mysqli_num_rows($testLogin)>0){
/*	$qry = "UPDATE user SET login = 'True' WHERE username='$Username' AND login='False'";
	$changLoginField = mysqli_query($conn ,$qry);*/
	$user=array();
	$json['status']='success';
array_push($user,$json);
while($row = mysqli_fetch_assoc($result)){
$user[]=$row;
$UserID=$row['id'];

}



$qry = "SELECT * FROM quiz";

$result = mysqli_query($conn ,$qry);

 
$response = array();
if(mysqli_num_rows($result)>0){
	
	
while($row = mysqli_fetch_assoc($result)){
    
	$response[]=$row;
	 $tempID=$row['id'];
	      $qry1 = "SELECT * FROM donequizzes where quizid='$tempID';";
									$result1 = mysqli_query($conn ,$qry1);
									if(mysqli_num_rows($result1)>0){
										$numberOfUsers=0;
									while($row1=mysqli_fetch_assoc($result1)){
										$numberOfUsers=$numberOfUsers+1;
					
											}
									 $qry2 = "SELECT * FROM donequizzes where quizid='$tempID' AND userid='$UserID';";
									$result2 = mysqli_query($conn ,$qry2);
									if(mysqli_num_rows($result2)>0){
										
										$json['state']='false';
									$json['QuizName']=$row['quiztype'];
									$json['quizid']=$row['id'];
									$json['numberOfUsers']=$numberOfUsers;
									}else{
								   $json['state']='True';
									$json['QuizName']=$row['quiztype'];
									$json['quizid']=$row['id'];
									$json['nameOfQuiz']=$row['quiztype'];
								
									
									$json['numberOfUsers']=$numberOfUsers;
										
									}
										
									
									$json['nameOfQuiz']=$row['quiztype'];
									
								
									array_push($response,$json);
									
									}//check if the Quiz itrates inside donequizzes table
									else{
										$json['state']='false';
										$json['numberOfUsers']='0';
									$json['QuizName']=$row['quiztype'];
									$json['quizid']=$row['id'];
									$json['nameOfQuiz']=$row['quiztype'];
									$json['quizidInCaseuserNot made Exam']=$row['id'];
									array_push($response,$json);
										
									
										
										
									}
								
	                             

}//fetching quiz list

 
}

$finalRes=array();
$finalRes=array_merge($user,$response);


echo json_encode($finalRes);
echo '<br>';


}
else{
	$json['status']='failed';
	$json['Error']='hmmmm! it seems the user : '.$Username.' is login';

	echo json_encode($json);
	
}
} 
else
{
	$json['status']='failed';
	$json['Error']='wrong username or password , please retry again';
	array_push($response,$json);
     echo json_encode($response);
	}



mysqli_close($conn);	

?>