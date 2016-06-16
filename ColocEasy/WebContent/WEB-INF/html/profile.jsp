<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:directive.include file="headers.jsp" />
<body>
	<jsp:directive.include file="navBar.jsp" />
	<main> 
	<div class="container">
		<div class="row">
		<c:choose>
			<c:when test="${sessionScope.userSession != null }">
			<h3>Mon compte</h3>
			<br>
			<form action="profile" method="post">
				<input type="hidden" name="id" value="<c:out value="${sessionScope.userSession.id }"/>">
		        <div class="input-field col s12 m4">
		            <i class="material-icons prefix">person</i>
		            <input id="first_name" name="first_name" type="text" class="validate" required value="<c:out value="${sessionScope.userSession.firstName }"/>">
		            <label for="first_name">Prénom *</label>
		        </div>
		        <div class="input-field col s12 m4">
		            <i class="material-icons prefix">person</i>
		            <input id="first_name" name="last_name" type="text" class="validate" required value="<c:out value="${sessionScope.userSession.lastName }"/>">
		            <label for="last_name">Nom *</label>
		        </div>
				<div class="input-field col s12 m4">
					<input class="with-gap" name="sexe" type="radio" id="male" value=0 ${sessionScope.userSession.sexe==0?'checked':''}/>
					<label for="male">Homme</label>
					<input class="with-gap" name="sexe" type="radio" id="female" value=1 ${sessionScope.userSession.sexe==1?'checked':''}/>
					<label for="female">Femme</label>
				</div>
		        <div class="input-field col s12 m12">
		            <i class="material-icons prefix">date_range</i>
		            <input id="birthday" type="date" name="birthday" class="datepicker" required value="<c:out value="${sessionScope.userSession.birth_date }"/>">
		            <label for="birthday">Date de naissance *</label>
		        </div>
		        <div class="input-field col s12 m12">
		            <i class="material-icons prefix">contact_mail</i>
		            <input id="email" name="email" type="email" class="validate" required value="<c:out value="${sessionScope.userSession.login }"/>">
		            <label for="email" data-error="Veuillez saisir une adresse email valide">Email *</label>
		        </div>
		        <div class="input-field col s12 m4">
		            <i class="material-icons prefix">vpn_key</i>
		            <input id="password" name="password" type="password" class="validate" required value="">
		            <label for="password">Mot de passe actuel *</label>
		        </div>
		        <div class="input-field col s12 m4">
		            <i class="material-icons prefix">vpn_key</i>
		            <input id="password" name="password_new" type="password" class="validate" value="">
		            <label for="password">Nouveau mot de passe</label>
		        </div>
		        <div class="input-field col s12 m4">
		            <i class="material-icons prefix">vpn_key</i>
		            <input id="password_confirmation" name="password_confirmation" type="password" class="validate" value="">
		            <label for="password_confirmation" data-error="Le mot de passe n'est pas identique">Confirmation mot de passe</label>
		        </div>
				<button class="waves-effect waves-light btn right deep-orange lighten-1">Enregistrer</button>
			</form>
			</c:when>
			<c:otherwise>
				Vous devez être connecté pour accéder à cette page. 
				<a href="login" class="btn-large waves-effect waves-light deep-orange lighten-1">Connexion</a>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>