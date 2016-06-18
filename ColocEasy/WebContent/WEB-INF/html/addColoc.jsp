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
						<h3>Ajouter une colocation</h3>
						<form action="addColoc" method="post">
							<div class="row">
								<div class="input-field col s12 m6">
						            <i class="material-icons prefix">label</i>
						            <input id="title" name="title" type="text" required>
						            <label for="title">Titre de l'annonce *</label>
						        </div>
						        <div class="file-field input-field col s12 m6">
						        	<div class="btn">
							        	<span>Images</span>
							        	<input type="file" multiple>
						        	</div>
						        	<div class="file-path-wrapper">
						        		<input class="file-path" type="text" placeholder="Choisissez une ou plusieurs images">
						        	</div>
						        </div>
					        </div>
					        <div class="row">
					        	<div class="input-field col s12">
						            <i class="material-icons prefix">reorder</i>
						            <textarea id="description" name="description" class="materialize-textarea" required></textarea>
						            <label for="description">Description *</label>
						        </div>
					        </div>
					        <div class="row">
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">room</i>
						            <input id="district" name="district" type="number" class="validate" min="0" max="20" required>
						            <label for="district" data-error="Arrondissement incorrect">Arrondissement *</label>
						        </div>
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">info</i>
						            <input id="surface" name="surface" type="number" class="validate" min="9" required>
						            <label for="surface" data-error="Doit être un nombre supérieur à 9m²">Surface (en m²) *</label>
						        </div>
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">supervisor_account</i>
						            <input id="capacity" name="capacity" type="number" class="validate" min="1" required>
						            <label for="capacity" data-error="Doit être un nombre supérieur à 1 personne">Capacité (en personnes) *</label>
						        </div>
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">store</i>
						            <input id="rooms" name="rooms" type="number" class="validate" min="1" required>
						            <label for="rooms" data-error="Doit être un nombre supérieur à 1 pièce">Nombre de pièces *</label>
						        </div>
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">payment</i>
						            <input id="rent" name="rent" type="number" class="validate" min="0" required>
						            <label for="rent" data-error="Doit être un nombre positif">Loyer (en €/mois) *</label>
						        </div>
					        </div>
							<div class="row">
								<button class="waves-effect waves-light btn right deep-orange lighten-1">Enregistrer</button>
							</div>
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