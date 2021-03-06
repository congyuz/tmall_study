<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/17
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<html>
<head>
    <script>
        $(function(){
            $("#editForm").submit(function(){
                if(!checkEmpty("name","分类名称")) return false;
                return true;
            });
        });
    </script>
    <title>编辑分类</title>
</head>
<body>
    <div class="workingArea">
        <%--<ol>是有序HTML列表，breadcrumb用于导航，表示当前页面所在的位置 --%>
        <ol class="breadcrumb">
            <%--/admin_category_list意味着会覆盖/tmall_ssm，所以不能加/--%>
            <li><a href="admin_category_list">所有分类</a></li>
            <li class="active">编辑分类</li>
        </ol>
        <div class="panel panel-warning editDiv">
            <div class="panel-heading">编辑分类</div>
            <div class="panel-body">
                <form method="post" id="editForm" action="admin_category_update" enctype="multipart/form-data">
                    <table class="editTable">
                        <tr>
                            <td>分类名称</td>
                            <td><input id="name" name="name" value="${c.name}" type="text" class="form-control"></td>
                        </tr>
                        <tr>
                            <td>分类图片</td>
                            <td><input id="categoryPic" accept="image/*" type="file" name="image"></td>
                        </tr>
                        <tr class="submitTR">
                            <td colspan="2" align="center">
                                <input type="hidden" name="id" value="${c.id}">
                                <button type="submit" class="btn btn-success">提交</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
