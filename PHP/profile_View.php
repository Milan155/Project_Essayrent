<?php
 include('connect.php');

$Phone = $_POST['Phone'];
$Password = $_POST['Password']; 


$sql="select * from Essayrental_Registration where Phone='$Phone' and Password = '$Password'";

$r=mysqli_query($con,$sql);

$response["result"]=array();

while($row=mysqli_fetch_array($r))
{
        $value=array();
    
        $value["id"] = $row["id"];
        $value["First_name"] = $row["First_name"];
        $value["Last_name"] = $row["Last_name"];
        $value["BOD"] = $row["BOD"];
        $value["Email"] = $row["Email"];
        $value["Phone"] = $row["Phone"];
        $value["Password"] = $row["Password"];
        
        array_push($response["result"],$value);
}

echo json_encode($response);
mysqli_close($con);
 
?>