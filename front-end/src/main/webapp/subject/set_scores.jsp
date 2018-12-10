<%@ page import="java.util.Map" %>
<%@ page import="subject.entity.Score" %><%--
  Created by IntelliJ IDEA.
  User: nao
  Date: 18-12-9
  Time: 下午9:28
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
    String studentId = (String) request.getSession().getAttribute("student_id");
    String majorId = (String) request.getSession().getAttribute("major_id");

    Map<String, Score> scoreMap = (Map<String, Score>) request.getSession().getAttribute("score_map");
%>
<body>
<div id="container">
    <div id="hd">
    </div>
    <div id="bd">
        <div id="main">
            <h2 class="subfild">
                <span>成绩录入</span>
            </h2>
            <form id="form_user" action="/servlet/score/submit" method="post">
                <input type="text" style="display: none" name="text_user_id" value="<%=studentId%>"/>
                <input type="text" style="display: none" name="text_major_id" value="<%=majorId%>"/>

                <%
                    for(Map.Entry<String, Score> entry : scoreMap.entrySet()){

                        String subjectName = entry.getKey();

                        Score score = entry.getValue();

                        // 隐藏写入课程id，
                        // 显式写入课程名、成绩
                        %>
                            <input type="text" style="display: none" name="text_score_id_<%=score.getSubjectId()%>" value="<%=score.getScoreId()%>"/>
                            <div class="subfild-content base-info">
                                <div class="kv-item ue-clear">
                                    <label><span class="impInfo">*</span><%=subjectName%></label>
                                    <div class="kv-item-content">
                                        <input type="text" name="text_score_value_<%=score.getSubjectId()%>" value="<%=score.getScoreValue()%>"/>
                                    </div>
                                </div>
                            </div>
                        <%

                    }
                %>

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

</script>
</html>
