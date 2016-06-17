<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:directive.include file="headers.jsp" />
<body>
	<jsp:directive.include file="navBar.jsp" />
	<main>
	<div class="container">
		<div class="row">
			<div class="col s8 offset-s2">
				<div class="row">
					<div class="col s6"><h3><c:out value="${requestScope.coloc.titre }"></c:out></h3></div>
					<div class="col s6 right-align"><h3><c:out value="${requestScope.coloc.rent }"></c:out> €</h3></div>
				</div>
				<div class="row">
					<div class="slider">
					    <ul class="slides">
					      <li>
					        <img src="https://static.pexels.com/photos/87223/pexels-photo-87223.jpeg">
					        <div class="caption center-align">
					          <h3>This is our big Tagline!</h3>
					          <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
					        </div>
					      </li>
					      <li>
					        <img src="https://static.pexels.com/photos/4703/inside-apartment-design-home.jpg">
					        <div class="caption left-align">
					          <h3>Left Aligned Caption</h3>
					          <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
					        </div>
					      </li>
					      <li>
					        <img src="https://static.pexels.com/photos/2705/bed-bedroom-room-furniture.jpg">
					        <div class="caption right-align">
					          <h3>Right Aligned Caption</h3>
					          <h5 class="light grey-text text-lighten-3">Here's our small slogan.</h5>
					        </div>
					      </li>
					    </ul>
					</div>
				</div>
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
		</div>
	</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>

<!-- 

		
 -->