<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: incentancy
  Date: 18-11-14
  Time: 下午7:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <link rel="stylesheet" type="text/css" href="../css/WdatePicker.css" />
    <link rel="stylesheet" type="text/css" href="../css/skin_/form.css" />
    <link href="../umeditor/themes/default/_css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/global.js"></script>
    <script type="text/javascript" src="../js/jquery.select.js"></script>
    <script type="text/javascript" src="../js/WdatePicker.js"></script>
    <script type="text/javascript">
        window.UMEDITOR_HOME_URL = 'umeditor/';  // 请换成绝对路径
    </script>
    <script type="text/javascript" src="../js/umeditor.config.js"></script>
    <script type="text/javascript" src="../js/editor_api.js"></script>
    <script type="text/javascript" src="../umeditor/lang/zh-cn/zh-cn.js"></script>
    <title>基础信息</title>
</head>
<%
    Cookie cookie = null;
    String userName = null;
    int userLevel = -1;

    Cookie[] cookies = request.getCookies();

    // 如果cookies中无值，则跳转到登录界面
    if(cookies == null){
%><script>window.location.href='/login.jsp'</script><%
    }else{
        for(int i = 0; i < cookies.length; i++){
            cookie = cookies[i];

            if(cookie.getName().equals("username")){
                userName = URLDecoder.decode(cookie.getValue());
            }else if (cookie.getName().equals("userlevel")){
                userLevel = Integer.parseInt(URLDecoder.decode(cookie.getValue()));
            }
        }
    }

%>
<body>
<div id="container">
    <div id="hd">
    </div>
    <div id="bd">
        <div id="main">
            <h2 class="subfild">
                <span>密码修改</span>
            </h2>
            <form id="form_user" action="/servlet/password/update" method="post" onsubmit="return Validate(this)">
                <input type="text" style="display: none" name="text_user_id" value="<%=userName%>"/>
                <input type="text" style="display: none" name="text_user_level" value="<%=userLevel%>"/>
                <div class="subfild-content base-info">
                    <div class="kv-item ue-clear">
                        <label><span class="impInfo">*</span>输入新密码</label>
                        <div class="kv-item-content">
                            <input type="password" name="text_user_new_passwd"/>
                        </div>
                    </div>
                </div>
                <div class="subfild-content base-info">
                    <div class="kv-item ue-clear">
                        <label><span class="impInfo">*</span>重复新密码</label>
                        <div class="kv-item-content">
                            <input type="password" name="text_user_new_passwd_again"/>
                        </div>
                    </div>
                </div>

                <div class="buttons">
                    <input class="button" type="submit" value="提交" />
                    <input class="button" type="reset" value="重置" />
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $('select').select();
    showRemind('input[type=text],textarea','color5');
    UM.getEditor('content');

    function Validate(form1){

        var newPasswd = form1.text_user_new_passwd.value.trim() ;
        var newPasswdRepeat = form1.text_user_new_passwd_again.value.trim() ;

        if( newPasswd == '' || newPasswdRepeat == '' )
        {
            alert( "新密码不得为空！" ) ;
            return false;
        }

        if( newPasswd != newPasswdRepeat )
        {
            alert( "两次密码不相同，请重新输入！" ) ;
            return false;
        }

        return true;
    }
</script>
</html>
