<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1"/>
		<title>Demo Application</title>
	</head>
	<body>
	<%String pathWebcontent=request.getContextPath();%>
	<form action="weatherServlet" method="GET">
	<select name="country" size="2">
        <option value="London">London</option>
        <option value="Hong kong">Hong kong</option>
    </select>
    <input type="submit" name="submit" value="submit" />
	</form>
	
	
	</body>
</html>