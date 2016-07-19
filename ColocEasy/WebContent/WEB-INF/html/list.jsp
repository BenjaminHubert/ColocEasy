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
					<h3>Liste des colocations</h3>
					<div class="row">
						<form method="get" action="">
							<div class="input-field col s4">
								<select multiple name="district">
									<option value="" disabled selected>Arrondissement</option>
									<option value="1">1<sup>er</sup></option>
									<option value="2">2<sup>ème</sup></option>
									<option value="3">3<sup>ème</sup></option>
									<option value="4">4<sup>ème</sup></option>
									<option value="5">5<sup>ème</sup></option>
									<option value="6">6<sup>ème</sup></option>
									<option value="7">7<sup>ème</sup></option>
									<option value="8">8<sup>ème</sup></option>
									<option value="9">9<sup>ème</sup></option>
									<option value="10">10<sup>ème</sup></option>
									<option value="11">11<sup>ème</sup></option>
									<option value="12">12<sup>ème</sup></option>
									<option value="13">13<sup>ème</sup></option>
									<option value="14">14<sup>ème</sup></option>
									<option value="15">15<sup>ème</sup></option>
									<option value="16">16<sup>ème</sup></option>
									<option value="17">17<sup>ème</sup></option>
									<option value="18">18<sup>ème</sup></option>
									<option value="19">19<sup>ème</sup></option>
									<option value="20">20<sup>ème</sup></option>
								</select>
								<label>Arrondissement</label>
							</div>
							<div class="input-field col s2">
								<input type="number" name="sMinSurface" value="${param.sMinSurface }">
								<label>Surface min</label>
							</div>
							<div class="input-field col s2">
								<input type="number" name="sMaxSurface" value="${param.sMaxSurface }">
								<label>Surface max</label>
							</div>
							<div class="input-field col s2">
								<input type="number" name="sMinRent" value="${param.sMinRent }">
								<label>Loyer min</label>
							</div>
							<div class="input-field col s2">
								<input type="number" name="sMaxRent" value="${param.sMaxRent }">
								<label>Loyer max</label>
							</div>
							<div class="row">
								<button class="waves-effect waves-light btn right deep-orange lighten-1">Rechercher</button>
							</div>
						</form>
					</div>
					<c:choose>
						<c:when test="${empty colocList }">
							La liste est vide :'(
						</c:when>
						<c:otherwise>
							<c:forEach items="${colocList }" var="coloc" varStatus="loop">
								<c:if test="${loop.index%3 == 0}">
									<div class="row">
								</c:if>
								<div class="col s12 m4">
									<div class="card">
										    <div class="card-image waves-effect waves-block waves-light">
										      	<c:choose>
													<c:when test="${colocImages[loop.index].path != null}">
										      			<img class="activator" src="upload_img/${colocImages[loop.index].path }">
													</c:when>
													<c:otherwise>
										      			<img class="activator" src="img/default.jpg">
													</c:otherwise>					    	
										    	</c:choose>
										    </div>
										    <div class="card-content">
												<span class="card-title activator grey-text text-darken-4"><c:out value="${coloc.titre }"></c:out><i class="material-icons right">more_vert</i></span>
											    <p><a href="<c:url value="coloc?id=${coloc.id }"/>">Voir</a></p>
										    </div>
										    <div class="card-reveal">
											    <span class="card-title grey-text text-darken-4"><c:out value="${coloc.titre }"></c:out><i class="material-icons right">close</i></span>
											    <p><c:out value="${coloc.description }"></c:out></p>
										    </div>
									  </div>
								</div>
								<c:if test="${loop.index%3 == 2}">
									</div>
								</c:if>
							</c:forEach>
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