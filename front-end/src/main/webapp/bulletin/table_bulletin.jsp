<%@ page import="bulletin.entity.Bulletin" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLDecoder" %><%--
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
    <div id="hd"></div>
    <div id="bd">
        <div id="main">
            <form id="form_bulletin_search" action="/servlet/bulletin/list" method="get">
                <input type="text" style="display: none" name="page_num" value="1"/>
                <div class="search-box ue-clear">
                <div class="search-area">
                    <div class="kv-item ue-clear">
                        <label>条件搜索：</label>
                        <span class="choose">
                            <span class="text">标题</span>
                            <span class="kv-item-content">
                                <input type="text" name="search_bulletin_title" />
                                <span class="division"></span>
                            </span>
                        </span>
                        <span class="choose">
                            <span class="text">内容</span>
                            <span class="kv-item-content">
                                <input type="text" name="search_bulletin_context" />
                                <span class="division"></span>
                            </span>
                        </span>
                        <span class="choose">
                            <span class="text">发布者ID</span>
                            <span class="kv-item-content">
                                <input type="text" name="search_user_id" />
                                <span class="division"></span>
                            </span>
                        </span>
                    </div>
                </div>
                </div>
                <div class="search-button">
                <input class="button" type="submit" value="搜索一下" name="button_search"/>
                </div>
            </form>
        </div>


        <div class="table">
            <div class="opt ue-clear">
                <span class="optarea">
                    <a href="/form_bulletin.jsp" class="add">
                        <i class="icon"></i>
                        <span class="text">添加</span>
                    </a>
                    <a href="<%=session.getAttribute("bulletins_current")%>"class="config">
                        <i class="icon"></i>
                        <span class="text">导出当前页</span>
                    </a>
                </span>
            </div>


            <div class="grid"></div>

            <div class="pagination"></div>
        </div>
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
        label: '公告ID',
        width: 100,
        sortable: 'default',
        name: 'bulletin_id'
    },{
        label:'标题',
        width: 200,
        sortable: 'default',
        name:'bulletin_title'
    },{
        label: '内容概要',
        width: 850,
        name: 'bulletin_content'
    },{
        label: '发布者ID',
        width: 100,
        name: 'user_id'

    },{
        label: '发布时间',
        width:170,
        name: 'published_date'
    }];

    var oper = [];

    var tbody = [];

    var bulletin_str = <%=session.getAttribute("bulletins_json")%>;
    var bulletin_json =eval(bulletin_str);

    // 迷一般的智障操作
    // 但没办法，就先这样用吧
    // 找到合适的解决办法再修改
    oper[0] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[0].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[0].bulletinId;
            window.location.href=url;
        }}];
    oper[1] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[1].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[1].bulletinId;
            window.location.href=url;
        }}];
    oper[2] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[2].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[2].bulletinId;
            window.location.href=url;
        }}];
    oper[3] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[3].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[3].bulletinId;
            window.location.href=url;
        }}];
    oper[4] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[4].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[4].bulletinId;
            window.location.href=url;
        }}];
    oper[5] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[5].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[5].bulletinId;
            window.location.href=url;
        }}];
    oper[6] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[6].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[6].bulletinId;
            window.location.href=url;
        }}];
    oper[7] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[7].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[7].bulletinId;
            window.location.href=url;
        }}];
    oper[8] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[8].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[8].bulletinId;
            window.location.href=url;
        }}];
    oper[9] = [{label:'删除',onclick:function(){
            var url = "/servlet/bulletin/delete?bulletin_id="+bulletin_json[9].bulletinId;
            if(confirm("确认删除该公告吗？")){
                window.location.href=url;
            }
        }},{label:'编辑',onclick: function(){
            var url = "/servlet/bulletin/update?bulletin_id="+bulletin_json[9].bulletinId;
            window.location.href=url;
        }}];

    <%
        // 若用户不为学生，则允许修改公告
        if(userLevel!=1){
            %>

            for(var i = 0; i < bulletin_json.length; i++){
                tbody.push([bulletin_json[i].bulletinId,bulletin_json[i].bulletinTitle,
                    bulletin_json[i].bulletinContext,bulletin_json[i].userId,bulletin_json[i].publishedDate, oper[i]]);
            }

            <%
        }else{

            %>

            for(var i = 0; i < bulletin_json.length; i++){
                tbody.push([bulletin_json[i].bulletinId,bulletin_json[i].bulletinTitle,
                    bulletin_json[i].bulletinContext,bulletin_json[i].userId,bulletin_json[i].publishedDate]);
            }

    <%

}
%>



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
    $('.pagination').pagination(<%=(int)session.getAttribute("bulletin_pages")*10%>,{
        current_page : <%=(int)session.getAttribute("bulletin_current_page")%>,      //当前页码
        num_display_entries : 4, // 中间显示页码的个数
        num_edge_entries : 2, // 末尾显示页码的个数
        prev_show_always : true, //是否总是显示最前页
        next_show_always : true,//是否总是显示最后页
        callback: function(page){
            window.location.href='/servlet/bulletin/list?page_num='+(page+1);
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
