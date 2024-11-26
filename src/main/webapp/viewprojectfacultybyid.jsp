<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
      <title>Academic Project Management System</title>
      <link rel="stylesheet" href="studenthomestyle.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
 <nav>
         <label class="logo">ProMent</label>
         <ul>
            <li><a class="active" href="facultyhome">Home</a></li>
             <li><a href="viewfacultyproject">View Projects</a></li>
            <li><a href="viewfacultybyid?id=${facid}">View Profile</a></li>
            <li><a href="facultylogin">Logout</a></li>
         </ul>
      </nav>

<h1 align=center>Project View Faculty</h1>


<br>
<h3 align=right>Welcome&nbsp;<c:out value="${funame}"></c:out></h3>


<br>

<table align=center>

<tr><th>Project ID:</th>&nbsp;<td>${project.pid}</td></tr>
<tr><th>Project Title:</th>&nbsp;<td>${project.title}</td></tr>

<tr><th>Project Domain:</th>&nbsp;<td>${project.domain}</td></tr>
<tr><th>Description:</th>&nbsp;<td>${project.description}</td></tr>


<tr><th>Project Lead ID:</th>&nbsp;<td>${pleadname}</td></tr>
<tr><th>Contributor 1:</th>&nbsp;<td>${con1name}</td></tr>
<tr><th>Contributor 2:</th>&nbsp;<td>${con2name}</td></tr>
<tr><th>Faculty Mentor:</th>&nbsp;<td>${fmentorname}</td></tr>
<tr><th>Progress:</th>&nbsp;<td>${progress}%</td></tr>


</table>

<br><br><br><br><br>
<h3 align = center>Reviews</h3>

<table align=center border=2>
    <tr>
 <th>Review ID</th>
	<th>Component</th>
	<th>Deadline</th>
	<th>Status</th>
	<th>Submission</th>
    </tr>
    <c:forEach items="${reviewlist}" var="review">
    
    <tr>
 
    <td> <c:out value="${review.previewid}"></c:out>   </td>
    <td> <c:out value="${review.component}"></c:out>   </td>
    <td> <c:out value="${review.deadline}"></c:out>   </td>
    <td> <c:out value="${review.status}"></c:out>   </td>
	
     
     <td>
    <a href='<c:url value='viewsubmissiontoreview?rid=${review.id}'></c:url>'>View Submission</a>&nbsp;&nbsp;
    
    
    </td>
    
    </tr>
    </c:forEach>
    
    </table>
    
    <h4 align=center>Add review </h4>
    
    
    <form method="post" action="addreview">

<table align=center>

<tr>
<td><label>Review Number</label></td>
<td><input type="password" name="rid" required></td>
</tr>

<tr>
<td><label>Deadline</label></td>
<td><input type="date" name="deadline" required></td>
</tr>
<tr>
<td><label>Component</label></td>
<td><input type="text" name="component" required></td>
</tr>


<tr align=center>

<td colspan="2"><input type="submit" value="Add Review" class="button"></td>

</tr>

</table>

</form>
    
    
    
    

</body>
</html>

 
