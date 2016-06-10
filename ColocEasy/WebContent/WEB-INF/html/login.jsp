<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:directive.include file="headers.jsp" />
<body>
	<jsp:directive.include file="navBar.jsp" />
		<main>
		<div class="row">
			<div class="col s12 m6 offset-m3">
				<div class="card">
					<div class="card-content">
						<span class="card-title">Connexion</span><br>
						<form action="login" method="post">
							<div class="input-field">
								<input type="text" id="login_field" name="login">
								<label for="login_field">E-mail</label> 
							</div>
							<div class="input-field">
								<input type="password" id="pwd_field" name="password" class="validate">
								<label for="pwd_field">Mot de passe</label> 
							</div>
							<div class="right-align">
								<a href="signup" id="signin-button" class="btn-large waves-effect waves-light indigo">Inscription</a>
								<button id="signin-button" class="btn-large waves-effect waves-light deep-orange lighten-1">Connexion</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>

