<?php
    include('connect.php');

    //$select="SELECT * from Essay_ViewD2;
     $select="select * from Essay_ViewD2";
    $result=mysqli_query($con,$select);
 
    $response= array();
         
    while ($row = mysqli_fetch_array($result))
    {
        $value = array();
        $value["id"] = $row["id"];
        $value["category_img"] = $row["category_img"];
        $value["category_name"] = $row["category_name"];
       
        
    
        array_push($response, $value);
    }
    echo json_encode($response);
?>