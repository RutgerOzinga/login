<%-- 
    Document   : loginHtmlHeader
    Created on : Dec 30, 2014, 3:25:56 PM
    Author     : Raozinga
    Use        : Contains the header so it can be used in an include.
--%>
<base href="${initParam.base_url}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Current page: ${param.page_title}</title>
<link href="css/loginCss.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<meta charset="utf-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<script>
    $(function () {
        $("#dialog").dialog({
            width: 470,
            autoOpen: false,
            resizable: false,
            modal: true});


        $("#register").click(function () {
            $("#dialog").dialog("open");
        });

    });
</script>
