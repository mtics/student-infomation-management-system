<%--
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

<body>
<div id="container">
    <div id="hd">
    </div>
    <div id="bd">
        <div id="main">
            <h2 class="subfild">
                <span>公告信息</span>
            </h2>
            <form id="form_user" action="/servlet/user/insert" method="post" onsubmit="return Validate(this)" enctype="multipart/form-data">
                <input type="text" style="display: none" name="text_user_id" value="<%=session.getAttribute("user_id")%>"/>
                <input type="text" style="display: none" name="text_user_level" value="<%=session.getAttribute("user_level")%>"/>
                <input type="text" style="display: none" name="text_user_portrait" value="<%=session.getAttribute("portrait")%>"/>
                <div class="kv-item ue-clear">
                    <label><span class="impInfo">*</span>用户姓名</label>
                    <div class="kv-item-content">
                        <input type="text" placeholder="用户姓名" name="text_user_name" value="<%=session.getAttribute("user_name")%>"/>
                    </div>
                    <span class="kv-item-tip">姓名限制在6个字以内</span>
                </div>

                <div class="kv-item ue-clear time">
                    <label><span class="impInfo">*</span>生日</label>
                    <div class="kv-item-content">
                        <input type="text" placeholder="生日" name="text_user_birthday"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<%=session.getAttribute("birthday")%>"/>
                        <i class="time-icon"></i>
                    </div>
                </div>
                <div class="kv-item ue-clear">
                    <label><span class="impInfo">*</span>性别</label>

                    <div class="kv-item-content">
                    	<span class="choose">
                            <span class="checkboxouter">
                                <input type="radio" name="text_user_gender" value="男" <% if(session.getAttribute("gender").equals("男")){%>checked="checked"<%}%>/>
                                <span class="radio"></span>
                            </span>
                            <span class="text">男</span>
                        </span>
                        <span class="choose">
                            <span class="checkboxouter">
                                <input type="radio" name="text_user_gender" value="女" <% if(session.getAttribute("gender").equals("女")){%>checked="checked"<%}%>/>
                                <span class="radio"></span>
                            </span>
                            <span class="text">女</span>
                        </span>
                    </div>
                </div>

                <div class="kv-item ue-clear">
                    <label><span class="impInfo">*</span>邮箱</label>
                    <div class="kv-item-content">
                        <input type="text" placeholder="请输入邮箱" name="text_user_email" value="<%=session.getAttribute("email")%>"/>
                    </div>
                </div>
                <div class="kv-item ue-clear">
                    <label><span class="impInfo">*</span>学院/专业ID</label>
                    <div class="kv-item-content">
                        <input type="text" placeholder="请输入学院/专业ID" name="text_user_major" value="<%=session.getAttribute("major_id")%>"/>
                    </div>
                    <span class="kv-item-tip">学生输入专业ID，教师输入学院ID</span>
                </div>

                <div class="buttons">
                    <input class="button" type="submit" value="发布" />
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

        var bulletinTitle = form1.text_bulletin_title.value.trim() ;
        var bulletinContent = form1.text_bulletin_content.value.trim() ;

        if( bulletinTitle== '' || bulletinContent== '' )
        {
            alert( "公告标题和内容不得为空！" ) ;
            return false;
        }
        return true;
    }
</script>
</html>
