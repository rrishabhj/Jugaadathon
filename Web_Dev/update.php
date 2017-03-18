<?php
$servername = "localhost";
$username = "id1041177_arctic";
$password = "8ballpool";
$dbname = "id1041177_hospital";


// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$ud = $_POST["uud"];
$pm = $_POST["prpm"];
$pr = $_POST["prsym"];
$sta = $_POST["prsta"];

$sql = "UPDATE CASES SET PR_PM = '$pm', PR_SYM = '$pr', PR_STA = '$sta' WHERE UID = '$ud'";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>