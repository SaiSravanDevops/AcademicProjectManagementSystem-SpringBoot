<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=ISO-8859-1" isELIgnored="false"%>

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
            <li><a class="active" href="studenthome">Home</a></li>
            <li>
               <a href="#">Project
               <i class="fas fa-caret-down"></i>
               </a>
               <ul>
                  <li><a href="projectcreation">Add Project</a></li>
                  <li><a href="viewuserproject">View Project</a></li>
               </ul>
            </li>
            <li><a href="viewstudentbyid?id=${stuid}">View Profile</a></li>
            <li><a href="studentlogin">Logout</a></li>
         </ul>
      </nav>

<h1 align=center>PROJECT MANAGEMENT SYSTEM</h1>

<br>



<br><br>

<h3 align=center><u>Project Creation</u></h3>

<br>
<span class="blink">
<h3 align=center style="color: red"><c:out value="${msg}"/></h3>
</span>
<br>

<form:form action="addproject" method="post" modelAttribute="proj">

<table align=center>

<tr>
<td><label>Project Title</label></td>
<td>
<form:input path="title" required="required"></form:input>
</td>
</tr>

<tr><td></td></tr>

<tr>
    <td><label>Domain</label></td>
    <td>
<form:select path="domain" required="required">
<form:option value="Project Management System">Project Management System</form:option>
<form:option value="Student Assessment Management System">Student Assessment Management System</form:option>
<form:option value="Airline Reservation Management System">Airline Reservation Management System</form:option>

</form:select>
    </td>
</tr>

<tr><td></td></tr>
<tr>
    <td><label>Description</label></td>
    <td>
    <form:input path="description" required="required"></form:input>
    </td>
    </tr>
<br>

<tr><td></td></tr>

<tr>
<td><label>Contributor 1</label></td>

<select name="contributor1" required="required">
        
        <option value="-1">---Select---</option>
        <c:forEach items="${studentlist}" var="student">
        <option value="${student.sid}">  Name: ${student.sname} </option>
 		</c:forEach>
 		
        </select>
</tr>

<tr><td></td></tr>

<tr>

<td><label>Contributor 2</label></td>
<select name="contributor2" required="required">
        
        <option value="-1">---Select---</option>
        <c:forEach items="${studentlist}" var="student">
        <option value="${student.sid}">ID: ${student.sid}  Name: ${student.sname} </option>
 		</c:forEach>
 		
        </select>
</tr>

<tr><td></td></tr>
<tr><td></td></tr>

<tr align=center>
<td colspan=2><input type="submit" value="Register" class="button"></td>
</tr>

</table>

</form:form>



</body>
</html>

 
