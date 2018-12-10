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
                <form id="form_login" action="/servlet/login" method="post" onsubmit="return Validate(this)">
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
                    <div class="input validate" id="captcha">
                        <label for="checkcode">验证码</label>
                        <input type="text" name="checkcode"  id="checkcode"/>
                        <div class="value">
                            <a href="javascript:reload();">
                            <img  src="servlet/image" alt="验证码" id="image" />
                            </a>
                        </div>
                    </div>
                    <div id="btn" class="loginButton" style="position: absolute; left: 169px; top: 359px">
                        <input type="submit" class="button" value="登录"  name="button_login" />
                    </div>
                    <div id="button_findpasswd" class="loginButton" style="position: absolute; left: 320px; top: 361px">
                        <input type="button" class="button" value="忘记密码"  name="button_findpasswd" onclick="goToFindPasswd()"/>
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

    // 该方法用于检验用户名、密码是否为空，以及验证码是否正确
    function Validate(form){

        var userName = form.text_username.value.trim() ;
        var passwd = form.text_password.value.trim() ;

        if( userName== '' || passwd== '' )
        {
            alert( "用户名或密码不得为空！" ) ;
            return false;
        }

        // 若验证码不正确，则返回false
        // 拒绝提交
        if(!verificationcode()){
            return false;
        }

        return true;
    }

    function reload(){
        document.getElementById("image").src="/servlet/image?date="+new Date().getTime();
        $("#checkcode").val("");   // 将验证码清空
    }

    function verificationcode(){

        var isRight = true;

        var text=$.trim($("#checkcode").val());


        /**
         * 下面这个方法目前是不能用的
         * $.post()异步执行，而之前的Validate()是同步执行
         * 也就是说，如果Validate()执行到了$.post()，会开启异步执行
         * 而Validate()本身会继续往下走，
         * 从而结束了函数调用，使得$.post()看上去似乎没有调用
         */
        // $(selector).post(将请求发送到哪个URL(必需),
        //                  连同请求发送到服务器的数据(可选),
        //                  当请求成功时运行的函数(可选),
        //                  预期的服务器响应的数据类型(可选))
        // $.post("/servlet/verification",{op:text},function(data){
        //
        //     alert("data="+data);
        //     data=parseInt($.trim(data));
        //     if(data>0){
        //         $("#span").text("验证成功!").css("color","green");
        //         isRight = true;
        //     }else{
        //         $("#span").text("验证失败!").css("color","red");
        //         reload();  //验证失败后需要更换验证码
        //         isRight = false;
        //     }
        // });

        $.ajax({
            url: "/servlet/verification",
            async: false,//这一步是非常重要的，作用是设置为同步执行
            type: "POST",
            data: {op: text },
            success: function (data) {
                data=parseInt($.trim(data));
                if(data>0){
                    isRight = true;
                }else{
                    alert("验证码错误！");
                    reload();  //验证失败后需要更换验证码
                    isRight = false;
                }
            }
        });

        $("#checkcode").val(""); // 将验证码清空

        return isRight;
    }

    function goToFindPasswd() {
        window.location.href = "/user/findpasswd.jsp";
    }
</script>

</html>
