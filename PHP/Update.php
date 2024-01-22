<?php
    
    include('connect.php');
    
    $id = $_POST["id"];
    $First_name = $_POST["First_name"];
    $Last_name = $_POST["Last_name"];
    $BOD= $_POST["BOD"];
    $Email= $_POST["Email"];
    $Phone= $_POST["Phone"];
    $Password= $_POST["Password"];
    
    
    $sql ="update Essayrental_Registration set First_name='$First_name',Last_name='$Last_name',BOD='$BOD',
    Email='$Email',Phone='$Phone',Password='$Password' where id = '$id'";
    
    if(mysqli_query($con,$sql))
    {
        echo 'updated Succesfully';
    }
    else
    {
        echo 'something went wrong';
    }

?>