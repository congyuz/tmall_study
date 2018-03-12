<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/21
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<!--<!DOCTYPE html>-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 对数字、日期、国际化消息的格式化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet"/>
    <script src="js/bootstrap/3.3.6/bootsrap.min.js"></script>
    <link href="css/back/style.css" rel="stylesheet"/>
    <title>Title</title>
    <script>
        function checkEmpty(id, name) {
            var value=$("#"+id).val();
            if(value.length==0){
                alert(name+"不能为空");
                <!-- 获取该元素的焦点，浏览器一般会以某种高亮方式来显示 -->
                $("#"+id)[0].focus();
                return true;
            }
            return false;
        }
        function checkNumber(id, name){
            var value=$("#"+id).val();
            if(value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            if(isNaN(value)){
                alert(name+"必须是数字");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }
        function checkInt(id,name) {
            var value=$("#"+id).val();
            if(value.length==0){
                alert(name+"不能为空");
                $("#"+id)[0].focus();
                return false;
            }
            if(parseInt(value)!=value){
                alert(name+"必须是整数");
                $("#"+id)[0].focus();
                return false;
            }
            return true;
        }
        <%--文档载入完毕后，点击的时候执行--%>
        $(function(){
            //"a"代表listCategory.jsp中的用于删除超链接的标签<a>
            $("a").click(function(){
                var deleteLink=$(this).attr("deleteLink");
                console.log(deleteLink);
                if(deleteLink=="true"){
                    var confirmDelete=confirm("确认删除");
                    if(confirmDelete) return true;
                    return false;
                }
            });
        })
    </script>

