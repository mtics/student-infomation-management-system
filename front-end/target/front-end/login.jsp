<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/skin_/login.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.select.js"></script>
    <title>SIMS登录</title>
</head>

<body>
<div id="container">
    <div id="bd">
        <div id="main">
            <div class="login-box">
                <div id="logo"></div>
                <h1></h1>
                <form id="form_login" action="/servlet/loginServlet" method="post" onsubmit="return Validate(this)">
                    <div class="input username" id="username">
                        <label>用户名</label>
                        <span></span>
                        <input type="text" name="text_username" />
                    </div>
                    <div class="input psw" id="psw">
                        <label>密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <span></span>
                        <input type="password" name="text_password" />
                    </div>
                    <!--
                    <div class="input validate" id="captcha">
                        <label for="captcha_value">验证码</label>
                        <input type="text" id="captcha_value" />
                        <div class="value">X3D5</div>
                    </div>
                    -->
                    <div id="btn" class="loginButton">
                        <input type="submit" class="button" value="登录"  name="button_login" />
                    </div>
                </form>
            </div>
        </div>
        <div id="ft">CopyRight&nbsp;2014&nbsp;&nbsp;版权所有&nbsp;&nbsp;More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> &nbsp;&nbsp;</div>
    </div>

</div>

</body>
<script type="text/javascript">
    var height = $(window).height() > 445 ? $(window).height() : 445;
    $("#container").height(height);
    var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
    $('#bd').css('padding-top', bdheight);
    $(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
        $("#container").height(height);
        var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
        $('#bd').css('padding-top', bdheight);
    });
    $('select').select();

    function Validate(form){

        var userName = form.text_username.value.trim() ;
        var passwd = form.text_password.value.trim() ;

        if( userName== '' || passwd== '' )
        {
            alert( "用户名或密码不得为空！" ) ;
            return false;
        }
        return true;
    }
</script>

</html>
