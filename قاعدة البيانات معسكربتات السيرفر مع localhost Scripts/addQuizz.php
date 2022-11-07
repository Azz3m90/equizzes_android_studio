<?php 

require "init.php";

$quizName= $_POST["quizName"];
//$quizName= "Android Quiz";

 if($quizName!=""){
	 $query="SELECT * FROM quiz WHERE quiztype='$quizName'";
	 $result=mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0) {
		echo nl2br("it seems there is a quiz with that name , write a Valid One or delete the stored quiz in the database with that name");
		
	} else
	{
		$queryInsert="INSERT INTO quiz (quiztype) VALUES ('$quizName');";
		if(mysqli_query($conn ,$queryInsert))
{
	
	echo nl2br("insertion successfully.\n");
	echo nl2br("quiz name added is :$quizName.\n");
	
	//echo "Data insertion success";
}
else{
	
	echo "Data insertion error".mysqli_error($conn);
}
		
	}
	 
 }
 else{
	 echo "QuizName field is Empty fill it and try again";
 }

mysqli_close($conn);	

?>