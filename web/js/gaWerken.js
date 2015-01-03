$(document).ready(initialize);

function initialize() {
    username = $("#toChange").data("user");
    alert("."+username+".");
    if(username === null|username===""){
    $("#toChange").load("includes/loginForm.jsp");
} else{
        $("#toChange").load("includes/workshop2.jsp");
    }
}