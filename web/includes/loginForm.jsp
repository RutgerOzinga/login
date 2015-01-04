<%-- 
    Document   : loginForm
    Created on : Jan 1, 2015, 6:34:11 PM
    Author     : raozinga
    Use        : Contains the info for the login form so it can be used in an 
                 include.
--%>
<div class="container">
    <div class="main">

        <form class="login" action="login.do" method="POST" action="#">

            <label> Login </label><br/>
            <span class="error_message"> ${requestScope.login_error} </span><br/><br/>
            <label> Username :</label>
            <input type="text" name="username" id="email">
            <label> Password :</label>
            <input type="password" name="password" id="password">
            <table>
                <input type="submit" value="Login" name="login" id="login"/>

                <input type="button" value="Register" name="Register" id="register"/> 
            </table>
        </form>
        <div id="dialog" title="IT WORKS">
            <form name="register_form" action="register.do" method="POST">
                <object width="425" height="344">
                    <param name="movie"
                           value="http://www.youtube.com/v/J_DV9b0x7v4&hl=en&fs=1">
                    </param>
                    <param name="allowFullScreen" value="true"></param>
                    <param name="allowscriptaccess" value="always"></param>
                    <embed src="http://www.youtube.com/v/J_DV9b0x7v4&hl=en&fs=1"
                           type="application/x-shockwave-flash"
                           allowscriptaccess="always"
                           allowfullscreen="true"
                           width="425" height="344">
                    </embed>
                </object>
            </form>
        </div>
    </div>
</div>
