				<!DOCTYPE html>
				<html lang="en" dir="ltr">
				  <head>
				    <meta charset="utf-8">
				    <title>Proment</title>
				    <link rel="stylesheet" href="studentloginstyle.css">
				   <script src="https://kit.fontawesome.com/a076d05399.js"></script>
				  </head>
				  <body>
				    <div class="bg-img">
				      <div class="content">
				        <header>Admin Login Form</header>
				        <form method="POST" action="checkadminlogin">
				          <div class="field">
				            <span class="fa fa-user"></span>
				            <input type="text" required placeholder="Username" name="auname" required>
				          </div>
				          <div class="field space">
				            <span class="fa fa-lock"></span>
				            <input type="password" class="pass-key" required placeholder="Password" name="apwd">
				            <span class="show">SHOW</span>
				          </div>
				          <br>
				          <div class="field">
				            <input type="submit" value="LOGIN">
				          </div>
				        </form>
				        
				       
				      </div>
				    </div>
				
				    <script>
				      const pass_field = document.querySelector('.pass-key');
				      const showBtn = document.querySelector('.show');
				      showBtn.addEventListener('click', function(){
				       if(pass_field.type === "password"){
				         pass_field.type = "text";
				         showBtn.textContent = "HIDE";
				         showBtn.style.color = "#3498db";
				       }else{
				         pass_field.type = "password";
				         showBtn.textContent = "SHOW";
				         showBtn.style.color = "#222";
				       }
				      });
				    </script>
				
				
				  </body>
				</html>
