<%@ page import="entity.Bulletin" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: incentancy
  Date: 18-11-20
  Time: 下午11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div id="main">
            <div class="search-box ue-clear">
                <div class="search-area">
                    <div class="kv-item ue-clear">
                        <label>选择时间：</label>
                        <div class="kv-item-content ue-clear">
                            <span class="choose">
                                <span class="checkboxouter">
                                    <input type="radio" name="time" />
                                    <span class="radio"></span>
                                </span>
                                <span class="text">全部</span>
                            </span>
                            <span class="choose">
                                <span class="checkboxouter">
                                    <input type="radio" name="time" />
                                    <span class="radio"></span>
                                </span>
                                <span class="text">近3天</span>
                            </span>
                            <span class="choose">
                                <span class="checkboxouter">
                                    <input type="radio" name="time" />
                                    <span class="radio"></span>
                                </span>
                                <span class="text">近一周</span>
                            </span>
                            <span class="choose">
                                <span class="checkboxouter">
                                    <input type="radio" name="time" />
                                    <span class="radio"></span>
                                </span>
                                <span class="text">近一月</span>
                            </span>
                            <span class="choose">
                                <span class="checkboxouter">
                                    <input type="radio" name="time" data-define="define" />
                                    <span class="radio"></span>
                                </span>
                                <span class="text">自定义</span>
                            </span>
                            <span class="define-input">
                            	<input type="text" placeholder="开始时间" />
                                <span class="division"></span>
                                <input type="text" placeholder="结束时间" />
                            </span>
                        </div>
                    </div>
                    <div class="kv-item ue-clear">
                        <label>选择类型:</label>
                        <div class="kv-item-content">
                            <select>
                                <option>全部</option>
                                <option>全部</option>
                                <option>全部</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="search-button">
                    <input class="button" type="button" value="搜索一下" />
                </div>
            </div>

            <div class="table">
                <div class="grid"></div>

                <div class="pagination"></div>
            </div>
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
        width: 400,
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

    var oper = [{label:'删除',onclick:function(){
            alert('删除');
        }},{label:'编辑',onclick: function(){
            alert('编辑')
        }}]

    var tbody = [];

    <%--var bulletin_str = <%=session.getAttribute("bulletin_json")%>;--%>
    <%--var bulletin_json =eval(bulletin_str)--%>

    <%--for(var i = 0; i < bulletin_json.length; i++){--%>
        <%--tbody.push([bulletin_json[i].bulletinId,bulletin_json[i].bulletinTitle,--%>
            <%--bulletin_json[i].bulletinContext,bulletin_json[i].userId,bulletin_json[i].publishedDate, oper]);--%>
    <%--}--%>

    <c:forEach items="${bulletin_list}" var="bulletin">
        tbody.push(["${bulletin.getBulletinId()}", "${bulletin.userId}", "${bulletin.bulletinTitle}",
            "${bulletin.publishedDate}", "${bulletin.bulletinContext}", oper]);
    </c:forEach>

    <%--<c:forEach items="${bulletin_list}" var="bulletin">--%>
    <%--tbody.push([${bulletin.getBulletinId()}, ${bulletin.userId}, ${bulletin.bulletinTitle},--%>
        <%--${bulletin.publishedDate}, ${bulletin.bulletinContext}, oper]);--%>
    <%--</c:forEach>--%>

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
    },2000)


    $('.pagination').pagination(100,{
        callback: function(page){
            alert(page);
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
