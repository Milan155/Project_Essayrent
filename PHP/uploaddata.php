<?php 
	
//importing dbDetails file 
include 'connect.php';	

//this is our upload folder 
$upload_path = 'image/';

//Getting the server ip 
$server_ip = gethostbyname(gethostname());

//creating the upload url 
//$upload_url = 'http://'.$server_ip.'/animal/'.$upload_path; 

$upload_url = 'https://'.$_SERVER['SERVER_NAME'] . "/Essayrental-Project/" . $upload_path;
	
	
//getting name from the request 
$emp_name= $_POST['emp_name'];
$emp_mobile = $_POST['emp_mobile'];
$emp_email = $_POST['emp_email'];
$emp_des = $_POST['emp_des'];
  

//getting file info from the request 
$fileinfo = pathinfo($_FILES["image"]["name"]);

//getting the file extension 
$extension = $fileinfo["extension"];

//file url to store in the database 
$file_url = $upload_url . $emp_name . '.' . $extension;

//file path to upload in the server 
$file_path = $upload_path . $emp_name . '.'. $extension; 
			
//saving the file 
move_uploaded_file($_FILES["image"]["tmp_name"],$file_path);

if($file_url=="" && $emp_name=="" && $emp_mobile=="" && $emp_email=="" && $emp_des=="" )
{
       echo '0';
}
else
{
        $sql = "INSERT INTO info (image,emp_name,emp_mobile,emp_email,emp_des) VALUES 
        ('$file_url','$emp_name','$emp_mobile','$emp_email','$emp_des')";
        $ex=mysqli_query($con,$sql);
}
echo $sql;
//exit;

//closing the connection 
mysqli_close($con);

?>