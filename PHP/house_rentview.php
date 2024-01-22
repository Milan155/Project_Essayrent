<?php
    
    include('connect.php');
    
    $sql = "select * from House_rentview";
    
    $r = mysqli_query($con,$sql);
    $response = array();
    
    while($row = mysqli_fetch_array($r))
    {
        $value["id"] = $row["id"];
        $value["Price"] = $row["Price"];
        $value["Location"] = $row["Location"];
        $value["Description"] = $row["Description"];
        $value["Image"] = $row["Image"];
     
        array_push($response,$value);
    }
    
    echo json_encode($response);
    mysqli_close($con);

?>
