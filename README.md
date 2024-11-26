The objective of this project is to develop a web application that connects instructors and students for project submissions, grading, and alerts. It consists of four main models namely student, faculty, admin, and project.

Admin Module: Admin is the super user who has access to all the database related operations in the project. Admin can register a student and a faculty to the website. Admin can make student as a team lead. 
Details of all the members who are registered on the website can be viewed by admin. Admin can unregister a faculty or a student from the website and also can update the details of registered members.

Student Module: A student can be made team-lead by the admin. The student who has got team-lead position can select the project domain and also the contributors to the project from the available list of registered students. 
The size limit of each team is 3. Team-lead can select the faculty mentor from the available list of the mentors. Each member of the team can submit the assignments or reports in the submissions portal. 
The team members can also view the feedback provided by the faculty mentor after the successful evaluation.

Faculty Module: Each faculty can guide a maximum of 3 teams. Faculty can view the submissions made by the teams and provide feedback accordingly. He can view the details of individual team members which are mapped to him.

Spring Boot Architecture: 
1.Controller: client interacts with the controller and this controller layer consists of API's and URL.
2.Service layer: Client controller interacts with the service layer which consists of business logic and its implementation here
3.Repository layer: It consists of all the queries related to CRUD Operations 
4.DAO layer(Data Access Object): It consists of persistence logic which ensures that the changes made are saved permanently to the database.
5.Database layer: It contains the database which has tables and data stored in it.

Directory Structure:
Models package: it contains POJO classes, which has attributes for models, setter and getter methods for those attributes
Controller: client controller class file has API's related to all the CRUD operations
Service package: service and service implementation class files for the models, business logic required for the application
Repository package: Queries to perform CRUD operations and commit the changes to the database.
