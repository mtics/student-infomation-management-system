<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: nao
  Date: 18-12-10
  Time: 上午8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/skin_/index.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/global.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
    <title>首页</title>
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

    String updatePasswdUrl = "user/form_user_passwd.jsp";

%>
<body>
<div id="container">
    <div id="hd">
    </div>
    <div id="bd">
        <div id="main">
            <ul class="nav-list ue-clear">
                <li class="nav-item desk">
                    <a href="<%=updatePasswdUrl%>">
                        <p class="icon"></p>
                        <p class="title">修改密码</p>
                    </a>
                </li>
                <li class="nav-item notice">
                    <a href="/servlet/bulletin/list">
                        <p class="icon"></p>
                        <p class="title">公告通知</p>
                    </a>
                </li>

                <%
                    if(userLevel != 1){
                        %>
                    <li class="nav-item news">
                        <a href="bulletin/form_bulletin.jsp">
                            <p class="icon"></p>
                            <p class="title">发布公告</p>
                        </a>
                    </li>
                    <li class="nav-item plan">
                        <a href="/user/form_user.jsp">
                            <p class="icon"></p>
                            <p class="title">新增用户</p>
                        </a>
                    </li>
                    <li class="nav-item contacts">
                        <a href="/servlet/student/list">
                            <p class="icon"></p>
                            <p class="title">学生列表</p>
                        </a>
                    </li>
                <%
                      if(userLevel == 3){
                          %>

                        <li class="nav-item contacts">
                            <a href="/servlet/teacher/list">
                                <p class="icon"></p>
                                <p class="title">教师列表</p>
                            </a>
                        </li>
                        <%
                      }

                    }
                    if(userLevel == 1){
                        %>

                        <li class="nav-item logs">
                            <a href="/servlet/score/query?student_id=<%=userName%>">
                                <p class="icon"></p>
                                <p class="title">成绩查询</p>
                            </a>
                        </li>
                <%
                    }
                %>

            </ul>

        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    var minwidth = 282;
    resizeWidth();
    $(top.window).resize(function(e) {
        resizeWidth();
    });
    $(function() {
        $( ".content-list" ).sortable({
            revert: true,
            handle:'h2'
        });

    });

    function resizeWidth (){
        if($('#main').width() / 3 < minwidth){
            $('.content-item').width(($('#main').width() / 2) - 15);
        }else{
            $('.content-item').width(($('#main').width() / 3) - 15);
        }

    }
</script>
</html>
