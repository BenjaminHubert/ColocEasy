/**
 * Activer le menu responsive
 */
$(".button-collapse").sideNav();

/**
 * Activer les datepickers
 */
$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15 // Creates a dropdown of 15 years to control year
 });

/**
* Activer le slideshow
*/
$(document).ready(function(){
	$('.slider').slider();
});