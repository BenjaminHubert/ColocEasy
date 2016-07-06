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
					<c:when test="${sessionScope.userSession != null}">
						<c:choose>
							<c:when test="${requestScope.forbidden != 1 }">
								<h3>Éditer une colocation</h3>
								<c:if test="${requestScope.errorMessage != null}">
									<c:out value="${requestScope.errorMessage }"/>
								</c:if>
								<div class="row">
								<ul class="tabs">
							        <li class="tab col s3"><a href="#tab1" class="active">Général</a></li>
							        <li class="tab col s3"><a href="#tab2">Images</a></li>
						        </ul>
						        <div id="tab1">
									<form action="editColoc" method="post" enctype="multipart/form-data">
										<div class="row">
											<div class="input-field col s12 m4">
									            <i class="material-icons prefix">label</i>
									            <input id="title" name="title" type="text" required value="${requestScope.coloc.titre }">
									            <label for="title">Titre de l'annonce *</label>
									        </div>
									        <div class="file-field input-field col s12 m4">
									        	<div class="btn">
										        	<span>Images</span>
										        	<input type="file" name="files" multiple>
									        	</div>
									        	<div class="file-path-wrapper">
									        		<input class="file-path" type="text" placeholder="Choisissez une ou plusieurs images">
									        	</div>
									        </div>
									        <div class="col s12 m4">
									        	<div class="switch">
									        		La colocation est-elle disponible ?
									        		<label>
									        		Non
									        		<input type="checkbox" name="enabled" <c:if test="${requestScope.coloc.isEnabled == 1}">checked ="checked"</c:if>>
									        		<span class="lever"></span>
									        		Oui
									        		</label>
								        		</div>
									        </div>
								        </div>
								        <div class="row">
								        	<div class="input-field col s12">
									            <i class="material-icons prefix">reorder</i>
									            <textarea id="description" name="description" class="materialize-textarea" required>${requestScope.coloc.description }</textarea>
									            <label for="description">Description *</label>
									        </div>
								        </div>
								        <div class="row">
											<div class="input-field col s12 m3">
									            <i class="material-icons prefix">room</i>
									            <input id="district" name="district" type="number" class="validate" min="0" max="20" required value="${requestScope.coloc.district }">
									            <label for="district" data-error="Arrondissement incorrect">Arrondissement *</label>
									        </div>
											<div class="input-field col s12 m3">
									            <i class="material-icons prefix">info</i>
									            <input id="surface" name="surface" type="number" class="validate" min="9" required value="${requestScope.coloc.surface }">
									            <label for="surface" data-error="Doit être un nombre supérieur à 9m²">Surface (en m²) *</label>
									        </div>
											<div class="input-field col s12 m3">
									            <i class="material-icons prefix">supervisor_account</i>
									            <input id="capacity" name="capacity" type="number" class="validate" min="1" required value="${requestScope.coloc.capacity }">
									            <label for="capacity" data-error="Doit être un nombre supérieur à 1 personne">Capacité (en personnes) *</label>
									        </div>
											<div class="input-field col s12 m3">
									            <i class="material-icons prefix">store</i>
									            <input id="rooms" name="rooms" type="number" class="validate" min="1" required value="${requestScope.coloc.rooms }">
									            <label for="rooms" data-error="Doit être un nombre supérieur à 1 pièce">Nombre de pièces *</label>
									        </div>
											<div class="input-field col s12 m3">
									            <i class="material-icons prefix">payment</i>
									            <input id="rent" name="rent" type="number" class="validate" min="0" required value="${requestScope.coloc.rent }">
									            <label for="rent" data-error="Doit être un nombre positif">Loyer (en €/mois) *</label>
									        </div>
								        </div>
										<input type="hidden" value="${param.id }" name="id">
										<div class="row">
											<button class="waves-effect waves-light btn right deep-orange lighten-1">Enregistrer</button>
										</div>
							        </form>
								</div>
								<div id="tab2">
						        	<div class="row">
							        	<form action="deleteImage" method="post">
								        	<div class="col s12">
								        		Images actuelles:
								        		<c:forEach items="${imageList }" var="image" varStatus="loop">
													<c:if test="${loop.index%3 == 0}">
														<div class="row">
													</c:if>
														<div class="col s4 valign-wrapper">
															<div class="col s1 valign">
																<input type="checkbox" id="img${image.id}" name="img${image.id}" class="filled-in">
																<label for="img${image.id}"></label>
															</div>
															<div class="col s11">
																<img src="upload_img/${image.path }" class="responsive-img">
															</div>
														</div>
													<c:if test="${loop.index%3 == 2}">
														</div>
													</c:if>
												</c:forEach>
							        		</div>
											<input type="hidden" value="${param.id }" name="id">
											<div class="row">
												<button class="waves-effect waves-light btn right deep-orange lighten-1">Supprimer</button>
											</div>
										</form>
						        	</div>
								</div>
							</c:when>
							<c:otherwise>
								Accès refusé.
							</c:otherwise>					
						</c:choose>
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