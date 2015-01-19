$(document).ready(initialize);

//This was a script that was used to switch between pages when a was logged in.
function initialize() {
    username = $("#toChange").data("user");
    if(username === null|username===""){
    $("#toChange").load("includes/loginForm.jsp");
} else{
        $("#toChange").load("includes/workshop2.jsp");
        $("#logout").load("includes/logout.jsp");
    }
}