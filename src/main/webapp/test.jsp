<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: sks
  Date: 2017/7/25
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <script type="text/javascript" src="/skin/js/common/jquery.js"></script>
    <title></title>

    <script>

        function loadData() {
//            alert(111)
            $.post("/cookie/loadData", {"key1":"value11111","key1":"value11111222"}, function (data,status) {
                console.log(data.length)
                debugger;
                for( var i =0; i<data.length;i++){
                   console.log(data[i].name)
                }
            },"json");
        }
    </script>
</head>
<body >
<input type="button" onclick="loadData()" value="click">
</body>
</html>
