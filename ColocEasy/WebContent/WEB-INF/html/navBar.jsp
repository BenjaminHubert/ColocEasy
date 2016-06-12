<%@ page contentType="text/html; charset=ISO-8859-1"%>

<nav class="indigo darken-2" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="index" class="brand-logo white-text">ColocEasy</a>
		<ul class="right hide-on-med-and-down">
			<li>
				<form>
					<div class="input-field">
						<input id="search" type="search" required placeholder="Rechercher"> <label
							for="search"><i class="material-icons">search</i></label> <i
							class="material-icons">close</i>
					</div>
				</form>
			</li>
			<% if( request.getSession().getAttribute("userSession") == null){ %>
			<li><a href="login">Se connecter</a></li>
			<% } else {%>
			<li>Bonjour <%= request.getSession().getAttribute("userSession") %></li>
			<li><a href="logout">Déconnexion</a></li>
			<% } %>
			<li><a href="profile">Profil</a></li>
		</ul>

		<ul id="nav-mobile" class="side-nav">
			<li>
				<form>
					<div class="input-field">
						<input id="search" type="search" required placeholder="Rechercher"> <label
							for="search" class="indigo-text"><i class="material-icons">search</i></label> <i
							class="material-icons">close</i>
					</div>
				</form>
			</li>
			<% if( request.getSession().getAttribute("userSession") == null){ %>
			<li><a href="login">Se connecter</a></li>
			<% } else {%>
			<li>Bonjour <% request.getSession().getAttribute("userSession"); %></li>
			<li><a href="logout">Déconnexion</a></li>
			<% } %>
			<li><a href="profile">Profil</a></li>
		</ul>
		<a href="#" data-activates="nav-mobile" class="button-collapse"><i
			class="material-icons">menu</i></a>
	</div>
</nav>
