<?php 

require "init.php";

$quizName= $_POST["quizName"];
//$quizName= "test";


$tempQuizID=0;
 if($quizName!=""){
	$query="SELECT * FROM quiz WHERE quiztype='$quizName'";
	$result=mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0) {
		while($row = mysqli_fetch_assoc($result)){

$tempQuizID=$row['id'];

}

			$queryDEL="DELETE FROM quiz WHERE quiztype='$quizName';";
			if(mysqli_query($conn ,$queryDEL))
				{

					echo nl2br("deletion  successfully.\n");
					echo nl2br("quiz name :$quizName. deleted successfully\n");
					
					$queryDELQusetions="DELETE FROM subquiz WHERE quiztid='$tempQuizID';";
					$results=mysqli_query($conn,$queryDELQusetions);
					
		      
				   
	


						echo nl2br("quiz name :$quizName. options and questions deleted successfully\n");
					
	
					
				
					
					
					

//echo "Data deleted success";
				}
			else{
					echo "Data insertion error".mysqli_error($conn);
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