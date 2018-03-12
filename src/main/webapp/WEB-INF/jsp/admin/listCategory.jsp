<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/22
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>
<script>
    <%--当文档载入完毕执行--%>
    $(function(){
        //当表单addForm提交时执行function()函数
        $("#addForm").submit(function(){
            if(checkEmpty("name","分类名称")) return false;
            if(checkEmpty("categoryPic","分类图片")) return false;
            return true;
        });
    });
</script>

<title>分类管理</title>

<div class="workingArea">
    <h1 class="label label-info">分类管理</h1>
    <br>
    <br>
    <div class="listDataTableDiv">
        <%--Bootstrap表格样式--%>
        <table class="table table-striped table-bordered table-hover table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>分类名称</th>
                <th>属性管理</th>
                <th>产品管理</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cs}" var="c">
                <tr>
                    <td>${c.id}</td>
                    <td><img height="40px" src="img/category/${c.id}.jpg"></td>
                    <td>${c.name}</td>
                    <%--glyphicon-th-list是Bootstrap中列表形的图标组件--%>
                    <%--因为controller中list()方法的参数名是cid，则此处必须与其相同即"?cid="--%>
                    <td><a href="admin_property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a href="admin_product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
                    <td><a href="admin_category_edit?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a href="admin_category_delete?id=${c.id}" deleteLink="true"><span class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pageDiv">
        <%@ include file="../include/admin/adminPage.jsp" %>
    </div>
    <%--Bootstrap面板样式--%>
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增分类</div>
        <div class="panel-body">
            <%--enctype规定如何对表单数据进行编码--%>
            <form method="post" id="addForm" action="admin_category_add" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input id="name" name="name" type="text" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <%--此处的name值"image"必须与UploadedImgFile类中的MultipartFile变量的变量名相同--%>
                        <td><input id="categoryPic" accept="image/*" type="file" name="image"/></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center"><button type="submit" class="btn btn-success">提交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@ include file="../include/admin/adminFooter.jsp"%>
