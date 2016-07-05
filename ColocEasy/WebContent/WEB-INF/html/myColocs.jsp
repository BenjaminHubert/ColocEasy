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
						<c:when test="${empty colocList }">
							Vous n'avez ajouté aucune colocation.
						</c:when>
						<c:otherwise>
							<h3>Colocs ajoutées</h3>
							<c:forEach items="${colocList }" var="coloc" varStatus="loop">
								<c:if test="${loop.index%3 == 0}">
									<div class="row">
								</c:if>
								<div class="col s12 m4">
									<div class="card">
										    <div class="card-image waves-effect waves-block waves-light">
										      	<img class="activator" src="img/background3.jpg">
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