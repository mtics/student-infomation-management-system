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
            <form id="form_bulletin" action="/servlet/bulletinServlet" method="post" onsubmit="return Validate(this)">
                <div class="subfild-content base-info">
                    <div class="kv-item ue-clear">
                        <label><span class="impInfo">*</span>公告标题</label>
                        <div class="kv-item-content">
                            <input type="text" placeholder="公告标题" name="text_bulletin_title" />
                        </div>
                        <span class="kv-item-tip">标题字数限制在60个字符内</span>
                    </div>
                </div>
                <div class="subfild-content remarkes-info">
                    <div class="kv-item ue-clear">
                        <label><span class="impInfo">*</span>公告内容</label>
                        <div class="kv-item-content">
                            <textarea placeholder="公告内容" name="text_bulletin_content" style="width:800px;height:240px;"></textarea>
                        </div>
                    </div>
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
