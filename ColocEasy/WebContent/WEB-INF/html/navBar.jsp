<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="indigo darken-2" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="index" class="brand-logo white-text">ColocEasy</a>
		<!-- Liste déroulante -->
		<ul id="dropdown1" class="dropdown-content">
			<li><a href="addColoc">Ajouter une coloc</a></li>
			<li><a href="myColocs">Mes colocs</a></li>
			<li><a href="profile">Profil</a></li>
			<li class="divider"></li>
			<li><a href="logout">Déconnexion</a></li>
		</ul>		
		<ul class="right hide-on-med-and-down">			
			<c:choose>
				<c:when test="${sessionScope.userSession != null }">
					<li><a class="dropdown-button" href="#!" data-activates="dropdown1"><i class="material-icons left">settings</i>Options<i class="material-icons right">arrow_drop_down</i></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="login">Se connecter</a></li>
				</c:otherwise>
			</c:choose>
		</ul>

		<!-- Menu mobile -->
		<ul id="nav-mobile" class="side-nav">
			<li>
				<form>
					<div class="input-field">
						<input id="search" type="search" required placeholder="Rechercher"> <label
							for="search" class="indigo-text"><i class="material-icons">search</i></label> <i
							class="material-icons">close</i>
					</div>
				</form>
				<c:choose>
					<c:when test="${sessionScope.userSession != null }">
						<li><a href="addColoc">Ajouter une coloc</a></li>
						<li><a href="myColocs">Mes colocs</a></li>
						<li><a href="profile">Profil</a></li>
						<li><a href="logout">Déconnexion</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="login">Se connecter</a></li>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>
		<a href="#" data-activates="nav-mobile" class="button-collapse"><i
			class="material-icons">menu</i></a>
	</div>
</nav>
