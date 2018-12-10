<%@ page import="student.entity.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: incentancy
  Date: 18-11-20
  Time: 下午11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
    <link rel="stylesheet" type="text/css" href="../css/WdatePicker.css" />
    <link rel="stylesheet" type="text/css" href="../css/skin_/table.css" />
    <link rel="stylesheet" type="text/css" href="../css/jquery.grid.css" />


    <title>表格</title>
</head>

<body>
<div id="container">
    <div id="hd"></div>
    <div id="bd">
        <form id="form_student_search" action="/servlet/student/list" method="get">
            <input type="text" style="display: none" name="page_num" value="1"/>
            <div id="main">
                <div class="search-box ue-clear">
                    <div class="search-area">
                        <div class="kv-item ue-clear">
                            <label>条件搜索：</label>
                            <span class="choose">
                                <span class="text">学号</span>
                                <span class="kv-item-content">
                                    <input type="text" name="search_student_id" />
                                    <span class="division"></span>
                                </span>
                            </span>
                            <span class="choose">
                                <span class="text">姓名</span>
                                <span class="kv-item-content">
                                    <input type="text" name="search_student_name" />
                                    <span class="division"></span>
                                </span>
                            </span>
                            <span class="choose">
                                <span class="text">性别</span>
                                <span class="kv-item-content">
                                    <input type="text" name="search_gender" />
                                    <span class="division"></span>
                                </span>
                            </span>
                            <span class="choose">
                                <span class="text">联系方式</span>
                                <span class="kv-item-content">
                                    <input type="text" name="search_phone" />
                                    <span class="division"></span>
                                </span>
                            </span>
                            <span class="choose">
                                <span class="text">专业号</span>
                                <span class="kv-item-content">
                                    <input type="text" name="search_major_id" />
                                    <span class="division"></span>
                                </span>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="search-button">
                    <input class="button" type="submit" value="搜索一下" name="student_search"/>
                </div>
            </div>

            <div class="table">
                <div class="opt ue-clear">
                    <span class="optarea">
                        <a href="/form_user.jsp" class="add">
                            <i class="icon"></i>
                            <span class="text">添加</span>
                        </a>
                        <a href="<%=session.getAttribute("students_current")%>"class="config">
                            <i class="icon"></i>
                            <span class="text">导出当前页</span>
                        </a>
                    </span>
                </div>
                <div class="grid"></div>

                <div class="pagination"></div>
            </div>
        </form>

    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/jquery.select.js"></script>
<script type="text/javascript" src="../js/core.js"></script>
<script type="text/javascript" src="../js/jquery.pagination.js"></script>
<script type="text/javascript" src="../js/jquery.grid.js"></script>
<script type="text/javascript" src="../js/WdatePicker.js"></script>
<script type="text/javascript">
    $('select').select();

    var head = [{
        label: '学号',
        width: 100,
        sortable: 'default',
        name: 'student_id'
    },{
        label:'姓名',
        width: 200,
        sortable: 'default',
        name:'student_name'
    },{
        label: '性别',
        width: 100,
        name: 'gender'
    },{
        label: '生日',
        width: 300,
        name: 'birthday'

    },{
        label: '联系方式',
        width: 200,
        name: 'email'

    },{
        label: '专业号',
        width: 170,
        name: 'major_id'
    }];

    var oper = [];

    var tbody = [];

    var student_str = <%=session.getAttribute("students_json")%>;
    var student_json =eval(student_str);

    // 迷一般的智障操作
    // 但没办法，就先这样用吧
    // 找到合适的解决办法再修改
    oper[0] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[0].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[0].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[0].studentId;
            url += "&majorId="+student_json[0].majorId;
            window.location.href=url;
        }}];
    oper[1] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[1].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[1].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[1].studentId;
            url += "&majorId="+student_json[1].majorId;
            window.location.href=url;
        }}];
    oper[2] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[2].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[2].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[2].studentId;
            url += "&majorId="+student_json[2].majorId;
            window.location.href=url;
        }}];
    oper[3] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[3].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[3].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[3].studentId;
            url += "&majorId="+student_json[3].majorId;
            window.location.href=url;
        }}];
    oper[4] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[4].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[4].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[4].studentId;
            url += "&majorId="+student_json[4].majorId;
            window.location.href=url;
        }}];
    oper[5] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[5].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[5].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[5].studentId;
            url += "&majorId="+student_json[5].majorId;
            window.location.href=url;
        }}];
    oper[6] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[6].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[6].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[6].studentId;
            url += "&majorId="+student_json[6].majorId;
            window.location.href=url;
        }}];
    oper[7] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[7].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[7].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[7].studentId;
            url += "&majorId="+student_json[7].majorId;
            window.location.href=url;
        }}];
    oper[8] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[8].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[8].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[8].studentId;
            url += "&majorId="+student_json[8].majorId;
            window.location.href=url;
        }}];
    oper[9] = [{label:'删除',onclick:function(){
            var url = "/servlet/student/delete?studentId="+student_json[9].studentId;
            if(confirm("确认删除该学生吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function() {
            var url = "/servlet/student/info?studentId=" + student_json[9].studentId;
            window.location.href = url;
        }},{label:'成绩',onclick: function(){
            var url = "/servlet/subject/list?studentId="+student_json[9].studentId;
            url += "&majorId="+student_json[9].majorId;
            window.location.href=url;
        }}];


    for(var i = 0; i < student_json.length; i++){
        tbody.push([student_json[i].studentId,student_json[i].studentName,
            student_json[i].gender,student_json[i].birthday,student_json[i].email, student_json[i].majorId, oper[i]]);
    }

    $('.grid').Grid({
        thead: head,
        tbody: null,
        height:400,
        checkbox: {
            single:true
        },
        operator: {
            type : "normal",
            width : 100
        },
        sortCallBack : function(name,index,type){
            alert(name+","+index+','+type);
        }

    });

    $('.grid').Grid('addLoading');

    /// 模拟异步
    setTimeout(function(){
        $('.grid').Grid('setData',tbody, head);
    },100)

    // pagination()方法中第一个参数是数据条数，第二个是点击页码后的响应函数
    $('.pagination').pagination(<%=(int)session.getAttribute("student_pages")*10%>,{
        current_page : <%=(int)session.getAttribute("student_current_page")%>,      //当前页码
        num_display_entries : 4, // 中间显示页码的个数
        num_edge_entries : 2, // 末尾显示页码的个数
        prev_show_always : true, //是否总是显示最前页
        next_show_always : true,//是否总是显示最后页
        callback: function(page){
            window.location.href='/servlet/student/list?page_num='+(page+1);
        },
        display_msg: false
    });

    $('.search-box input[type=radio]').click(function(e) {
        if($(this).prop('checked')){
            if($(this).attr('data-define') === 'define'){
                $('.define-input').show();
            }else{
                $('.define-input').hide();
            }
        }
    });
</script>
</html>
