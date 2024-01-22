<?php
include('connect.php');

$sql = "SELECT * FROM Eassy_Propertycat"; // Assuming 'Essay_C-view' is the correct table name
$response = array();

// Check if the query was successful
if ($result = mysqli_query($con, $sql)) {
    while ($row = mysqli_fetch_assoc($result)) {
        $value["id"] = $row["id"];
        $value["category_name"] = $row["category_name"];
        $value["category_img"] = $row["category_img"];

        array_push($response, $value);
    }

    // Free result set
    mysqli_free_result($result);
} else {
    // If the query was not successful, print the error message
    $response['error'] = mysqli_error($con);
}

echo json_encode($response);

// Close the database connection
mysqli_close($con);
?>
