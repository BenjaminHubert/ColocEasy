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
						<form>
							<div class="row">
								<div class="input-field col s12 m6">
						            <i class="material-icons prefix">label</i>
						            <input id="title" name="title" type="text" class="validate" required>
						            <label for="title">Titre de l'annonce *</label>
						        </div>
						        <div class="file-field input-field col s12 m6">
						        	<div class="btn">
							        	<span>File</span>
							        	<input type="file" multiple>
						        	</div>
						        	<div class="file-path-wrapper">
						        		<input class="file-path validate" type="text" placeholder="Choisissez une ou plusieurs images">
						        	</div>
						        </div>
					        </div>
					        <div class="row">
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">room</i>
						            <input id="title" name="title" type="text" class="validate" required>
						            <label for="title">Arrondissement *</label>
						        </div>
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">info</i>
						            <input id="title" name="title" type="text" class="validate" required>
						            <label for="title">Surface *</label>
						        </div>
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">supervisor_account</i>
						            <input id="title" name="title" type="text" class="validate" required>
						            <label for="title">Capacité (en personnes)*</label>
						        </div>
								<div class="input-field col s12 m3">
						            <i class="material-icons prefix">payment</i>
						            <input id="title" name="title" type="text" class="validate" required>
						            <label for="title">Loyer *</label>
						        </div>
					        </div>
					        <div class="row">
					        	<div class="input-field col s12 m6">
						            <i class="material-icons prefix">reorder</i>
						            <textarea id="description" name="description" class="materialize-textarea" required></textarea>
						            <label for="description">Description *</label>
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