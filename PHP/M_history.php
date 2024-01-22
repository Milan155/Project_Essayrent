<?php
    
    include('connect.php');
    
    $sql="select * from info";
    
    $r=mysqli_query($con,$sql);
    $response=array();
    
    while($row=mysqli_fetch_array($r))
    {
        
        $value["id"]=$row["id"];
        $value["image"]=$row["image"];
        $value["emp_des"]=$row["emp_des"];

          array_push($response, $value);
    }
    echo json_encode($response);
    mysqli_close($con);

?>