<?php 

require "init.php";

$quizId=$_POST["quizID"];;
//$subquizid=$_POST["subQuizID"];

//$quizId='1';

$qry = "SELECT * FROM subquiz where quiztid='$quizId';";

$result = mysqli_query($conn ,$qry);

$response = array();


if(mysqli_num_rows($result)>0){
	$json['status']='success';
	array_push($response,$json);
while($row=mysqli_fetch_assoc($result)){
	
 
	$response[]=$row;
   $tempID=$row['id'];
	$qry1 = "SELECT * FROM options where subQuizId=$tempID;";
	$result1 = mysqli_query($conn ,$qry1);
	while($row1=mysqli_fetch_assoc($result1)){
			$response[]=$row1;
	}
		
}

}

else{
	$response['status']='faild';
	$response['Error']='there are no quizzez stetted';
}
echo json_encode($response);

mysqli_close($conn);	

?>