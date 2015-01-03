<%-- 
    Document   : logout
    Created on : 3-jan-2015, 20:52:33
    Author     : Mariska
--%>
<form class="login" action="logout.do" method="POST" action="#">
    <div class="main">
        <label> Hallo, ${sessionScope.user.username}</label><br/>
        <table>
            <input type="submit" value="Logout" name="logout" id="login"/>    
        </table>
    </div>
</form>
