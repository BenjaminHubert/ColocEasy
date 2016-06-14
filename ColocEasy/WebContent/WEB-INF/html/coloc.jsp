<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:directive.include file="headers.jsp" />
<body>
	<jsp:directive.include file="navBar.jsp" />
	<main>
	<div class="container" style="padding:1%;">
		<div class="slider" style="width:60%; display:inline-block">
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
		<ul class="collapsible" data-collapsible="expandable" style="width:39%; float:right; margin:0">
		    <li>
		      <div class="collapsible-header active"><i class="material-icons">filter_drama</i>First</div>
		      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
		    </li>
		    <li>
		      <div class="collapsible-header"><i class="material-icons">place</i>Second</div>
		      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
		    </li>
		    <li>
		      <div class="collapsible-header"><i class="material-icons">whatshot</i>Third</div>
		      <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
		    </li>
		</ul>
	</div>
	</main>
	<jsp:directive.include file="footer.jsp" />
	<jsp:directive.include file="shared_scripts.jsp" />
</body>
</html>