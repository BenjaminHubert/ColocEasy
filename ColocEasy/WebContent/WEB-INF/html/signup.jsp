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
						<span class="card-title">Inscription</span><br>
						<div class="col s6">
							<div class="input-field">
								<input type="text" id="fname_field">
								<label for="fname_field">Prénom</label> 
							</div>
						</div>
						<div class="col s6">
							<div class="input-field">
								<input type="text" id="lname_field">
								<label for="lname_field">Nom</label> 
							</div>
						</div>
						<div class="col s12">
							<div class="input-field">
								<input type="text" id="login_field">
								<label for="login_field">E-mail</label> 
							</div>
							<div class="input-field">
								<input type="date" class="datepicker" id="bday_field">
								<label for="bday_field">Date de naissance</label> 
							</div>
							<div class="input-field" style="margin-top:0">
								<label>Genre</label><br>
								<input class="with-gap" name="gender" type="radio" id="male"  />
      							<label for="male">Homme</label>
      							<input class="with-gap" name="gender" type="radio" id="female"  />
      							<label for="female">Femme</label>
      						</div>
      						<br>
							<div class="input-field">
								<input type="password" id="pwd_field">
								<label for="pwd_field">Mot de passe</label> 
							</div>
							<div class="input-field">
								<input type="password" id="confirmation_field">
								<label for="confirmation_field">Confirmation du mot de passe</label> 
							</div>
						</div>
						<div class="right-align">
							<a href="signin" id="signin-button" class="btn-large waves-effect waves-light deep-orange lighten-1">S'inscrire</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>