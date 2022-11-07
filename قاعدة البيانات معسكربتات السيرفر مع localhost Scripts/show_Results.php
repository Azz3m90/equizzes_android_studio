<?php 

require "init.php";

$quizName= $_POST["quizName"];


 if($quizName!=""){
	$query="SELECT * FROM quiz WHERE quiztype='$quizName';";
	$result = mysqli_query($conn ,$query);

     $response = array();
	 
						 if(mysqli_num_rows($result)>0){
							 
								while($row=mysqli_fetch_assoc($result)){
									
								 
									$response[]=$row;
								   $tempID=$row['id'];
						$qry1 = "SELECT * FROM donequizzes where quizid='$tempID';";
									$result1 = mysqli_query($conn ,$qry1);
									if(mysqli_num_rows($result1)>0){
										
											while($row1=mysqli_fetch_assoc($result1)){
												$response[]=$row1;
												$userTempID=$row1['userid'];
						$query="SELECT * FROM user where id='$userTempID';";
						$getUserNames =mysqli_query($conn,$query);
						if(mysqli_num_rows($getUserNames)>0){
							while($row2=mysqli_fetch_assoc($getUserNames)){
								$response[]=$row2;
								
								
								
								
								
								
										}
						
						}//taking the usernames from user Tables
						else{
							$response['status']='faild';
	$response['Error']="we faild to get the user with id : $userTempID ";
							
							
						}
												

									        }//taking the user id
									
									$json['status']='success';
	array_push($response,$json);
									
									
									     }//testing if the quizz made from a user or not
										 
										 else{
											 $response['status']='faild';
	$response['Error']="this quizz isn't made from any user"; 
											 
										 }
										
								        
								}									
							 
			
						 }//testing if there a quizz inside quiz table]
						else{
			 $response['status']='faild';
	$response['Error']="there isn't a quizz with that name:$quizName in our dataBase";
						 }
	 

	 

 }//end of first condition if quiz name is valid
 
else{
	$response['status']='faild';
	$response['Error']='quiz name is empty please Enter a valid quizName';
}
echo json_encode($response);

mysqli_close($conn);	

	

?>