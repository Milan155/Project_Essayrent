<?php
    
    include('connect.php');
    
    $First_name	 = $_POST["First_name"];
    $Last_name = $_POST["Last_name"];
    $BOD = $_POST["BOD"];
    $Email = $_POST["Email"];
    $Phone = $_POST["Phone"];
    $Password = $_POST["Password"];
    
    
    
    if($First_name=="" && $Last_name=="" && $BOD=="" && $Email=="" && $Phone=="" && $Password==""  )
    {
        echo '0';
    }
    else
    {
        $sql = "insert into Essayrental_Registration (First_name,Last_name,BOD,Email,Phone,Password) values ('$First_name','$Last_name','$BOD','$Email','$Phone','$Password')";
        
        mysqli_query($con,$sql);
    
    }
?>