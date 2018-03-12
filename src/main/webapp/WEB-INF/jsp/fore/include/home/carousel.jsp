<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/1
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<div id="carousel-of-product" class="carousel-of-product carousel slide1" data-ride="carousel">
    <%--指示器--%>
    <ol class="carousel-indecators">
        <li data-target="#carousel-of-product" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-of-product" data-slide-to="1"></li>
        <li data-target="#carousel-of-product" data-slide-to="2"></li>
        <li data-target="#carousel-of-product" data-slide-to="3"></li>
    </ol>
    <%--包装的幻灯片--%>
    <div class="carousel-inner" role="listbox">
        <div class="item active"><img class="carousel carouselImage" src="img/lunbo/1.jpg"></div>
        <div class="item"><img class="carouselImage" src="img/lunbo/2.jpg"></div>
        <div class="item"><img class="carouselImage" src="img/lunbo/3.jpg"></div>
        <div class="item"><img class="carouselImage" src="img/lunbo/4.jpg"></div>
    </div>
    <%--控制器--%>
    <%--<a class="left carousel-control" href="#carousel-of-product" role="button" data-slide="prev">--%>
        <%--<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>--%>
    <%--</a>--%>
    <%--<a class="right carousel-control" href="#carousel-of-product" role="button" data-slide="next">--%>
            <%--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>--%>
    <%--</a>--%>
</div>
