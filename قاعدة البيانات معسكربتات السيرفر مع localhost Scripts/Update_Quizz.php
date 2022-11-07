<?php 

require "init.php";

$quizName= $_POST["quizName"];
$newQuizName= $_POST["newQuizName"];
//$quizName= "Android Quiz";
//$newQuizName="java";


 if(($quizName!="")&&($newQuizName!="")){
	$query="SELECT * FROM quiz WHERE quiztype='$quizName'";
	$result=mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0) {
		

			$queryInsert="UPDATE quiz SET quiztype='$newQuizName'  WHERE quiztype='$quizName';";
			if(mysqli_query($conn ,$queryInsert))
				{

					echo nl2br("updated  successfully.\n");
					echo nl2br("quiz name :$quizName. updated successfully into ($newQuizName ) \n");

//echo "Data deleted success";
				}
			else{
					echo "Updated error".mysqli_error($conn);
			}

	}
	else{
		echo nl2br("it seems there isn't a quizz in that name : $quizName . in our database.");
	}
 }

 else{
		echo "QuizName field is Empty fill it and try again";
 }

mysqli_close($conn);	

?>