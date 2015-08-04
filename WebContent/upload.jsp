<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Data Analysis Application</title>
</head>
<body>

	<center>
        <form method="post" action="UploadServlet" enctype="multipart/form-data">
            Select file to upload: <input type="file" name="uploadFile" />
            <br/>
            <br/> 
            <input type="submit" value="Upload" />
        </form>
        <br>
        <h3><a href="welcome.html">Go Back to Welcome Page</a></h3> 
    </center>
</body>
</html>
<STYLE type=text/css>
A:link {
 COLOR: Gray /*The color of the link*/
}
A:visited {
 COLOR: #800080 /*The color of the visited link*/
}
A:hover {
 COLOR: Red /*The color of the mouseover or 'hover' link*/
}
BODY { COLOR: #800080 /*The color of all the other text within the body of the page*/
{ 
</STYLE>