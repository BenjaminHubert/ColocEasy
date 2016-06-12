<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:directive.include file="headers.jsp" />
<body>
	<jsp:directive.include file="navBar.jsp" />
	<main> 
	<form>
	<div class="container">
		<div class="row">
			<h3>Mon compte</h3>
	        <div class="input-field col s12 m6">
	            <i class="material-icons prefix">person</i>
	            <input id="first_name" name="first_name" type="text" class="validate" required value="">
	            <label for="first_name">Prénom *</label>
	        </div>
	        <div class="input-field col s12 m6">
	            <i class="material-icons prefix">person</i>
	            <input id="first_name" name="first_name" type="text" class="validate" required value="">
	            <label for="last_name">Nom *</label>
	        </div>
	        <div class="input-field col s12 m12">
	            <i class="material-icons prefix">date_range</i>
	            <input id="birthday" type="date" name="birthday" class="datepicker" required value="">
	            <label for="birthday">Date de naissance *</label>
	        </div>
	        <!-- <div class="input-field col s12 m6">
	        	<i class="material-icons prefix">store</i>
	        	<input id="adress_1" name="address" type="text" class="validate" required value="">
	        	<label for="address_1">Adresse *</label>
	        </div>
	        <div class="input-field col s12 m6">
	        	<i class="material-icons prefix">my_location</i>
	        	<input id="zip_code" name="zip_code" type="text" class="validate" required value="">
	        	<label for="zip_code">Code postal</label>
	        </div>
	        <div class="input-field col s12 m6">
	        	<i class="material-icons prefix">my_location</i>
	        	<input id="city" name="city" type="text" class="validate" required value="">
	        	<label for="city">Ville</label>
	        </div>
	        <div class="input-field col s12 m6">
	        	<i class="material-icons prefix">contact_phone</i>
	        	<input id="phone" name="phone" type="text" class="validate" required value="">
	        	<label for="phone">Téléphone</label>
	        </div> -->
	        <div class="input-field col s12 m12">
	            <i class="material-icons prefix">contact_mail</i>
	            <input id="email" name="email" type="email" class="validate" required value="">
	            <label for="email" data-error="Veuillez saisir une adresse email valide">Email *</label>
	        </div>
	        <div class="input-field col s12 m6">
	            <i class="material-icons prefix">vpn_key</i>
	            <input id="password" name="password" type="password" class="validate" required value="">
	            <label for="password">Mot de passe *</label>
	        </div>
	        <div class="input-field col s12 m6">
	            <i class="material-icons prefix">vpn_key</i>
	            <input id="password_confirmation" name="password_confirmation" type="password" class="validate" required value="">
	            <label for="password_confirmation" data-error="Le mot de passe n'est pas identique">Confirmation mot de passe *</label>
	        </div>
			<a class="waves-effect waves-light btn right deep-orange lighten-1">Enregistrer</a>
		</div>
	</div>
	</form>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>