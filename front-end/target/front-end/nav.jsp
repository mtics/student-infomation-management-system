<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: nao
  Date: 18-12-4
  Time: 下午4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/skin_/nav.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/global.js"></script>
    <title>底部内容页</title>
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

    String updateInfoUrl = null;
    if(userLevel ==1){
        updateInfoUrl = "/servlet/student/info?studentId="+userName;
    }else{
        updateInfoUrl = "/servlet/teacher/info?teacherId="+userName;
    }

    String updatePasswdUrl = "/user/form_user_passwd.jsp";

%>
<body>
<div id="container">
    <div id="bd">
        <div class="sidebar">
            <div class="sidebar-bg"></div>
            <i class="sidebar-hide"></i>
            <ul class="nav">
                <li class="nav-li">
                    <a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">公告管理</span></a>
                    <ul class="subnav">
                        <%  // 若用户等级不为学生，则允许发布新公告
                            if(userLevel != 1){
                            %>
                                <li class="subnav-li" href="/bulletin/form_bulletin.jsp" data-id="10"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">发布公告</span></a></li>
                            <%
                            }
                        %>
                        <li class="subnav-li" href="/servlet/bulletin/list" data-id="11"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">公告记录</span></a></li>
                    </ul>
                </li>
                <li class="nav-li current">
                    <a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">用户信息设置</span></a>
                    <ul class="subnav">
                        <li class="subnav-li current" href="main.jsp" data-id="1"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">首页</span></a></li>
                        <%  // 若用户等级不为学生，则允许添加新用户、查看学生列表
                            if(userLevel != 1){
                        %>
                                <li class="subnav-li" href="/user/form_user.jsp" data-id="2"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">新增用户</span></a></li>
                                <li class="subnav-li" href="/servlet/student/list" data-id="3"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">学生列表</span></a></li>
                                <%
                                if(userLevel == 3){
                                %>
                                    <li class="subnav-li" data-id="4" href="/servlet/teacher/list" ><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">教师列表</span></a></li>
                        <%
                                }
                            }
                        %>
                        <li class="subnav-li current" href="<%=updateInfoUrl%>" data-id="8"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">修改信息</span></a></li>
                        <li class="subnav-li current" href="<%=updatePasswdUrl%>" data-id="9"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">修改密码</span></a></li>
                    </ul>
                </li>

                <%
                    if(userLevel == 1){
                %>
                    <li class="nav-li">
                        <a href="javascript:;" class="ue-clear"><i class="nav-ivon"></i><span class="nav-text">成绩管理</span></a>
                        <ul class="subnav">
                            <li class="subnav-li"  href="/servlet/score/query?student_id=<%=userName%>" data-id="5"><a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i><span class="subnav-text">成绩查询</span></a></li>

                        </ul>
                    </li>
                <%
                    }
                %>

            </ul>
            <div class="tree-list outwindow">
                <div class="tree ztree"></div>
            </div>
        </div>
        <div class="main">
            <div class="title">
                <i class="sidebar-show"></i>
                <ul class="tab ue-clear">

                </ul>
                <i class="tab-more"></i>
                <i class="tab-close"></i>
            </div>
            <div class="content">
            </div>
        </div>
    </div>
</div>

<div class="more-bab-list">
    <ul></ul>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
    <div class="opt-panel-bl"></div>
</div>
</body>
<script type="text/javascript" src="js/nav.js"></script>
<script type="text/javascript" src="js/Menu.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
    var menu = new Menu({
        defaultSelect: $('.nav').find('li[data-id="1"]')
    });

    // 左侧树结构加载
    var setting = {};

    var zNodes =[
        { name:"公告管理",
            children: [
                { name:"发布公告",icon:'img/skin_/leftlist.png'},
                { name:"公告记录",icon:'img/skin_/leftlist.png'}
            ]},
        { name:"用户信息设置", open:true,
            children: [
                { name:"首页", checked:true,icon:'img/skin_/leftlist.png'},
                { name:"表单",icon:'img/skin_/leftlist.png'},
                { name:"表格",icon:'img/skin_/leftlist.png'},
                { name:"自定义设置",icon:'img/skin_/leftlist.png'}
            ]},
        { name:"工作安排",
            children: [
                { name:"工作安排",icon:'img/skin_/leftlist.png'},
                { name:"安排管理",icon:'img/skin_/leftlist.png'},
                { name:"类型选择",icon:'img/skin_/leftlist.png'},
                { name:"自定义设置",icon:'img/skin_/leftlist.png'}
            ]},
        { name:"数据管理",
            children: [
                { name:"工作安排",icon:'img/skin_/leftlist.png'},
                { name:"安排管理",icon:'img/skin_/leftlist.png'},
                { name:"类型选择",icon:'img/skin_/leftlist.png'},
                { name:"自定义设置",icon:'img/skin_/leftlist.png'}
            ]}
    ];

    $.fn.zTree.init($(".tree"), setting, zNodes);


    $('.sidebar h2').click(function(e) {
        $('.tree-list').toggleClass('outwindow');
        $('.nav').toggleClass('outwindow');
    });

    $(document).click(function(e) {
        if(!$(e.target).is('.tab-more')){
            $('.tab-more').removeClass('active');
            $('.more-bab-list').hide();
        }
    });
</script>
</html>