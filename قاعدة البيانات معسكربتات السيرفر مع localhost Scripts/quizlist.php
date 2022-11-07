<?php 

require "init.php";

$UserID='9';

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
										
										$json['state']='True';
									$json['QuizName']=$row['quiztype'];
									$json['quizid']=$row['id'];
									$json['numberOfUsers']=$numberOfUsers;
									}else{
								   $json['state']='false';
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

 echo json_encode($response);
}

else
{
	$response['status']='failed';
	$response['Error']='There is no quizzes right now';
echo json_encode($response);	
}


mysqli_close($conn);	

?>