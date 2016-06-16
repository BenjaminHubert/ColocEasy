<%@ page pageEncoding="UTF-8"%>
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
						<span class="card-title">Inscription</span>
						<div class="error red-text text-darken-1">
							<c:if test="${requestScope.errorMessage != null}">
								<c:out value="${requestScope.errorMessage }"/>
							</c:if>
						</div><br>
						<c:choose>
							<c:when test="${sessionScope.userSession == null }">
								<form action="signup" method="post">
									<div class="col s6">
										<div class="input-field">
											<input type="text" id="fname_field" name="first_name" required>
											<label for="fname_field">Prénom</label> 
										</div>
									</div>
									<div class="col s6">
										<div class="input-field">
											<input type="text" id="lname_field" name="last_name" required>
											<label for="lname_field">Nom</label> 
										</div>
									</div>
									<div class="col s12">
										<div class="input-field">
											<input type="text" id="login_field" name="login" required>
											<label for="login_field">E-mail</label> 
										</div>
										<div class="input-field">
											<input type="date" class="datepicker" id="bday_field" name="birth_date">
				            				<label for="bday_field">Date de naissance</label> 
										</div>
										<div class="input-field" style="margin-top:0">
											<label>Genre</label><br>
											<input class="with-gap" name="sexe" type="radio" id="male" value=0 />
			      							<label for="male">Homme</label>
			      							<input class="with-gap" name="sexe" type="radio" id="female" value=1 />
			      							<label for="female">Femme</label>
			      						</div>
			      						<br>
										<div class="input-field">
											<input type="password" id="pwd_field" name="password" required>
											<label for="pwd_field">Mot de passe</label> 
										</div>
										<div class="input-field">
											<input type="password" id="confirmation_field" name="confirm" required>
											<label for="confirmation_field">Confirmation du mot de passe</label> 
										</div>
									</div>
									<div class="right-align">
										<button href="signin" id="signin-button" class="btn-large waves-effect waves-light deep-orange lighten-1">S'inscrire</button>
									</div>
								</form>
							</c:when>
							<c:otherwise>
								Bien tenté mais vous êtes déjà inscrit sur le site. :)
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>