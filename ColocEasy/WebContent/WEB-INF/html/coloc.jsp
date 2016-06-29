<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:directive.include file="headers.jsp" />
<body>
	<jsp:directive.include file="navBar.jsp" />
	<main>
	<div class="container">
		<div class="row">
			<div class="col s12">
				<c:choose>
					<c:when test="${param.id != null}">
						<div class="row">
							<div class="col s6"><h3><c:out value="${requestScope.coloc.titre }"></c:out></h3></div>
							<div class="col s6 right-align"><h3><c:out value="${requestScope.coloc.rent }"></c:out> €/mois</h3></div>
						</div>
						<div class="row">
							<div class="slider">
							    <ul class="slides">
							    <c:choose>
							    	<c:when test="${not empty imageList }">
							    		<c:forEach items="${imageList }" var="image">
							    			<li><img src="${image.path }"></li>
							    		</c:forEach>
						    		</c:when>
						    		<c:otherwise>
						    			<li><img><div class="caption center-align"><h5 class="light grey-text text-lighten-3">Pas d'image</h5></div>
						    		</c:otherwise>
					    		</c:choose>
							    </ul>
							</div>
						</div>
						<div class="row">
							<ul class="collapsible" data-collapsible="expandable">
							    <li>
							      <div class="collapsible-header active"><i class="material-icons">info_outline</i>Caractéristiques</div>
							      <div class="collapsible-body">
								      <p>Surface: <c:out value="${requestScope.coloc.surface }"></c:out>m<sup>2</sup></p>
								      <p>Pièces: <c:out value="${requestScope.coloc.rooms }"></c:out></p>
								      <p>Capacité: <c:out value="${requestScope.coloc.capacity }"></c:out> personne<c:if test="${requestScope.coloc.capacity > 1}">s</c:if></p>
								      <p>Lieu: Paris <c:out value="${requestScope.coloc.district }"></c:out><sup>e</sup></p>
							      </div>
							    </li>
							    <li>
							      <div class="collapsible-header"><i class="material-icons">subject</i>Description</div>
							      <div class="collapsible-body"><p><c:out value="${requestScope.coloc.description }"></c:out></p></div>
							    </li>
							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<div class="row">
							La page que vous recherchez n'existe pas
						</div>
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>
