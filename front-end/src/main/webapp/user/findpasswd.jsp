<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <link rel="stylesheet" type="text/css" href="../css/skin_/login.css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/jquery.select.js"></script>
    <title>密码找回</title>
</head>

<body>
<div id="container">
    <div id="bd">
        <div id="main">
            <div class="login-box">
                <div id="logo"></div>
                <h1></h1>
                <form id="form_find_passwd" action="/servlet/passwd/find" method="post" onsubmit="return Validate(this)">
                    <div class="input username" id="username" style="top: 245px">
                        <label>用户名</label>
                        <span></span>
                        <input type="text" name="text_username" />
                    </div>
                    <div id="btn" class="loginButton" style="position: absolute; left: 240px; top: 320px">
                        <input type="submit" class="button" value="找回密码"  name="button_submit" />
                    </div>
                </form>
            </div>
        </div>
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

    // 该方法用于检验用户名、密码是否为空，以及验证码是否正确
    function Validate(form){

        var userName = form.text_username.value.trim() ;

        if( userName== '' || passwd== '' )
        {
            alert( "请输入用户名！" ) ;
            return false;
        }

</script>

</html>
