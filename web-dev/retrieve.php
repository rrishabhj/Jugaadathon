<html>
<head>
<link rel="stylesheet" type="text/css" href="frontPageCSS.css">
 <meta name="viewport" content="width=device-width, user-scalable=no" />
</head>
<body>
<div class="container">
    
    <div class="bgimg"><h3 align="center">Hospitalisation form</h3>
<?php
$servername = "localhost";
$username = "id1041177_arctic";
$password = "8ballpool";
$dbname = "id1041177_hospital";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$ud = $_POST["uid"];

$sql = "SELECT name,gender,DOB,relation,address,P_PM,P_SYM,P_STA FROM CASES WHERE UID='$ud'";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        echo "name: " . $row["name"]. "<br>". "gender: " . $row["gender"]. "<br>". "date of birth : " . $row["DOB"]. "<br>". "Relation: ".$row["relation"].  "<br>". "address: ".$row["address"].  "<br>". "prescribed medicine: ".$row["P_PM"].  "<br>". "symptoms: ".$row["P_SYM"]. "<br>". "status: ".$row["P_STA"];
    }
} else {
    echo "0 results";
}

mysqli_close($conn);
?>
</div>
<div style='text-align: right;position: fixed;z-index:9999999;bottom: 0; width: 100%;cursor: pointer;line-height: 0;'>

</body>
</html>