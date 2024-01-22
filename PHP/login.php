<?php
    
    include('connect.php');
    
    $Phone=$_REQUEST["Phone"];
    $Password=$_REQUEST["Password"];
    
    $sql="select * from Essayrental_Registration where Phone='$Phone' and Password ='$Password'";
    
    
    $ex=mysqli_query($con,$sql);
    
    $no=mysqli_num_rows($ex);
    
    
    if($no>0)
    {
    $fet=mysqli_fetch_object($ex);
    echo json_encode(['code'=>200]);
    }
    else
    {
    echo "0";
    }

?>