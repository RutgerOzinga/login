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

            <h2><b> Log in </b></h2>
            <span class="error_message"> ${requestScope.login_error} </span><br/><br/>
            <span class="error_message"> ${requestScope.register_error} </span><br /><br />
            <label> Gebruikersnaam:</label>
            <input type="text" name="username" id="username">
            <label> Wachtwoord:</label>
            <input type="password" name="password" id="password">
            <table>
                <input type="submit" value="Log in" name="login" id="login"/>

                <input type="button" value="Registreer" name="Register" id="register"/> 
            </table>
        </form>
        <div id="dialog" title="Registreer">
            <form name="register_form" action="register.do" method="POST">
                <div id="dialog" title="Basic dialog">
                    <form name="register_form" action="register.do" method="POST">
                        
                        <table>
                            <tr>
                                <td>Gebruikersnaam</td>
                                <td><input name="userName" type="text" /></td>
                            </tr>
                            <tr>
                                <td>Wachtwoord</td>
                                <td><input name="password" type="password" /></td>
                            </tr>
                            <tr>
                                <td>Voornaam</td>
                                <td><input name="firstName" type="text" /></td>
                            </tr>
                            <tr>
                                <td>Achternaam</td>
                                <td><input name="lastName" type="text" /></td>
                            </tr>
                            <tr>
                                <td>E-mail</td>
                                <td><input name="email" type="text" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="register" /></td>
                                <td><a href="index.jsp">Heb je al een account?</a></td>
                            </tr>

                        </table>
                    </form>
                </div>
            </form>
        </div>
    </div>
</div>
