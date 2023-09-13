<%@page import="model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Sản Phẩm</title>
    <style>
        body{
          background-color: #607D8B;
        }
        .container{
            max-width: 1200px;
            margin: 70px auto;           
        }        
        .main{
            display: flex;
            min-height: 500px; 
            background-color: aliceblue;
        }
        .picture{
            flex-basis: 50%;
        }
        .picture img{
            width: 500px;
            align-items: center;
        }
        .content{
            flex-basis: 50%;
            text-align: left;
        }
        #add{
            margin-top: 30px;
            background-color: #0066FF;
            width: 250px;
            height: 50px;
            text-transform: uppercase;
            color: aliceblue;
            border: #607D8B;
        }
        #size{
            margin-top: 5px;
            width: 200px;
            height: 30px;
        }
        #color{
            margin-top: 15px;
            margin-bottom: 15px;
            width: 200px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="main">
        <div class="picture">
            <img src="${pageContext.request.contextPath}/views/admin/${product.imageUrl}"  alt="">
        </div>
        <div class="content">
            <h2>Chi Tiết Sản Phẩm</h2>
            <form action="add-to-cart?id=${product.id}">
                <div>
                    <label for="">Tên sản phẩm: </label>
                    <h3>${product.name}</h3>
                </div>
                <div>
                    <label for="">Giá: </label>
                    <h3>${product.price}</h3>
                </div>
                <div>
                    <label for=""></label>
                    <select name="size" id="size">
                        <option value="0">- Chọn size -</option>
                        <c:forEach items="${product.size}" var="size">
                            <option value="${size}">${size}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for=""></label>
                    <select name="color" id="color">
                        <option value="0">- Chọn màu -</option>
                        <c:forEach items="${product.colors}" var="color">
                            <option value="${color}">${color}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="">Mô tả: </label>
                    <p>${product.description}</p>
                </div>
                <div>
                    <input type="hidden" id="id" name="id" value="${product.id}">
                </div>
                <div>
                    <input type="submit" id="add" value="Thêm vào giỏ hàng">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>