<!DOCTYPE html>
<html>
<jsp:directive.include file="headers.jsp" />
<body>
	<header>
		<jsp:directive.include file="navBar.jsp" />
	</header>
	<main>
	<div id="index-banner" class="parallax-container">
		<div class="section no-pad-bot">
			<div class="container">
				<br> <br>
				<h1 class="header center white-text">Bienvenue sur Coloc Easy</h1>
				<div class="row center white-text">
					<h4 class="header col s12 light">Spécialiste de la colocation entre particuliers</h4>
				</div>
				<div class="row center">
					<c:choose>
					<c:when test="${sessionScope.userSession != null }">
						<a href="list" id="signup-button" class="btn-large waves-effect waves-light deep-orange lighten-1">Voir la liste</a>
					</c:when>
					<c:otherwise>
						<a href="signup" id="signup-button" class="btn-large waves-effect waves-light deep-orange lighten-1">Se Lancer</a>
					</c:otherwise>
					</c:choose>
				</div>
				<br> <br>

			</div>
		</div>
		<div class="parallax">
			<img src="img/background2.jpg" alt="Unsplashed background img 1">
		</div>
	</div>
	<div class="container">
		<div class="section">
			<!--   Icon Section   -->
			<div class="row">
				<div class="col s12 m4">
					<div class="icon-block">
						<h2 class="center indigo-text">
							<i class="material-icons">flash_on</i>
						</h2>
						<h5 class="center">Lorem Ipsum</h5>

						<p class="light">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nullam scelerisque id nunc nec volutpat. Etiam
							pellentesque tristique arcu, non consequat magna fermentum ac.
							Cras ut ultricies eros. Maecenas eros justo, ullamcorper a sapien
							id, viverra ultrices eros. Morbi sem neque, posuere et pretium
							eget, bibendum sollicitudin lacus.</p>
					</div>
				</div>

				<div class="col s12 m4">
					<div class="icon-block">
						<h2 class="center indigo-text">
							<i class="material-icons">group</i>
						</h2>
						<h5 class="center">Lorem Ipsum</h5>

						<p class="light">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nullam scelerisque id nunc nec volutpat. Etiam
							pellentesque tristique arcu, non consequat magna fermentum ac.
							Cras ut ultricies eros. Maecenas eros justo, ullamcorper a sapien
							id, viverra ultrices eros. Morbi sem neque, posuere et pretium
							eget, bibendum sollicitudin lacus.</p>
					</div>
				</div>

				<div class="col s12 m4">
					<div class="icon-block">
						<h2 class="center indigo-text">
							<i class="material-icons">settings</i>
						</h2>
						<h5 class="center">Lorem Ipsum</h5>

						<p class="light">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nullam scelerisque id nunc nec volutpat. Etiam
							pellentesque tristique arcu, non consequat magna fermentum ac.
							Cras ut ultricies eros. Maecenas eros justo, ullamcorper a sapien
							id, viverra ultrices eros. Morbi sem neque, posuere et pretium
							eget, bibendum sollicitudin lacus.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="parallax-container valign-wrapper">
		<div class="section no-pad-bot">
			<div class="container">
				<div class="row center">
					<h5 class="header col s12 light">Petite phrase stylée 2</h5>
				</div>
			</div>
		</div>
		<div class="parallax">
			<img src="img/background1.jpg" alt="Unsplashed background img 2">
		</div>
	</div>
	<div class="container">
		<div class="section">
			<div class="row">
				<div class="col s12">
					<h4>Les dernières colocs</h4>
					<c:forEach items="${requestScope.lastColocs }" var="coloc" varStatus="loop">
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
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="parallax-container valign-wrapper">
		<div class="section no-pad-bot">
			<div class="container">
				<div class="row center">
					<h5 class="header col s12 light center-align">Petite phrase stylée 3</h5>
				</div>
			</div>
		</div>
		<div class="parallax">
			<img src="img/background3.jpg" alt="Unsplashed background img 2">
		</div>
	</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			$('.parallax').parallax();
		});
	</script>
</body>
</html>