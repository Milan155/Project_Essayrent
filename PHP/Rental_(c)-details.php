<?php
    include('connect.php');

    $C_id=$_REQUEST["data"];
    
    $select="SELECT * from House_rentview_C where C_id='$C_id'";
   // $select="select * from Category_Images";
    $result=mysqli_query($con,$select);
 
    $response= array();
         
    while ($row = mysqli_fetch_array($result))
    {
        $value = array();
        $value["id"] = $row["id"];
        $value["C_id"] = $row["C_id"];
        $value["Price"] = $row["Price"];
        $value["Location"] = $row["Location"];
        $value["Description"] = $row["Description"];
        $value["Image"] = $row["Image"];
        
    
        array_push($response, $value);
    }
    echo json_encode($response);
?>