<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Academic Project Management</title>

</head>
<body>


<br><br>

<h3 align=center><u>View All Faculty</u></h3>

<table align=center border=2>
<tr>
<th>ID</th>
<th>Name</th>

<th>Gender</th>

<th>Department</th>
<th>Designation</th>
<th>Salary</th>
<th>Location</th>
<th>Mail ID</th>
<th>User Name</th>
<th>Contact No</th>

</tr>
<c:forEach items="${facultylist}" var="faculty">

<tr>

<td> <c:out value="${faculty.fid}"></c:out>   </td>
<td> <c:out value="${faculty.fname}"></c:out>   </td>
<td> <c:out value="${faculty.fgender}"></c:out>   </td>

<td> <c:out value="${faculty.fdepartment}"></c:out>   </td>

<td> <c:out value="${faculty.fdesignation}"></c:out>   </td>

<td> <c:out value="${faculty.fsalary}"></c:out>   </td>

<td> <c:out value="${faculty.flocation}"></c:out>   </td>
<td> <c:out value="${faculty.fmailid}"></c:out>   </td>
<td> <c:out value="${faculty.fusername}"></c:out>   </td>
<td> <c:out value="${faculty.fcontactno}"></c:out>   </td>

<td>

<a href='<c:url value='deletefaculty?id=${faculty.fid}'></c:url>'>Delete</a>&nbsp;&nbsp;
<a href='<c:url value='viewfacultybyid?id=${faculty.fid}'></c:url>'>View</a>&nbsp;&nbsp;


</td>

</tr>
</c:forEach>

</table>
</body>
</html>