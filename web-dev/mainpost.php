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
$ud = $_POST["uid"];
$nm = $_POST["name"];
$gen = $_POST["gender"];
$db = $_POST["dob"];
$rl = $_POST["rel"];
$ad = $_POST["add"];
$pmm = $_POST["pm"];
$sm = $_POST["sym"];
$pss = $_POST["ps"];
$sql = "INSERT INTO CASES(UID, name, gender, DOB, relation, address, P_PM, P_SYM, P_STA)
VALUES ('$ud', '$nm' , '$gen', '$db', '$rl', '$ad', '$pmm', '$sm', '$pss')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>