<?php
    
    include('connect.php');
    
    $id = $_POST["id"];
    $Phone = $_POST["Phone"];
    $Password = $_POST["Password"];
    
    
    $sql ="update Essayrental_Registration set Phone='$Phone',Password='$Password',where id = '$id'";
    
    if(mysqli_query($con,$sql))
    {
        echo 'updated Succesfully';
    }
    else
    {
        echo 'something went wrong';
    }

?>