<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

${submission.text}
${submission.comment}
${submission.submittedById}
${submission.submissiontime}

<form method="post" action="editfeedbackrating">

<table align=center>

<tr>
<td><label>Feedback</label></td>
<td><input type="text" name="feedback" required></td>
</tr>

<tr><td></td></tr>

<tr>
<td><label>Rating Out of 10</label></td>
<td><input type="number" name="rating" required></td>
</tr>

<tr><td></td></tr>

<tr align=center>

<td colspan="2"><input type="submit" value="Update" class="button"></td>

</tr>

</table>

</form>


</body>
</html>