<%@ page import="org.apache.regexp.REUtil" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="cache-control" content="no-store"/>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10"/>
<title></title>
<script type="text/javascript">

</script>
</head>
<body onload="toBank()">

<form  action="<%=request.getContextPath()%>/zrjPay/zrjOrder.do" id="payForm" >
    用户编号:<input type="text"  name="clientId" value="608"><br/>
	<input type="hidden"   name="spbillCreateIp" value="172.16.20.53">
     交易号:<input type="text"  name="orderId" value="12345654321"><br/>
    交易金额:<input type="text"  name="chargeAmount" value="1"><br/>
    <input type="hidden"  name="productCode" value="123"><br/>
    <input type="hidden"  name="productDesc" value="tradeSubject"><br/>
    <input type="submit" value="支付">
</form>

<%--{"clientId":608,"spbillCreateIp":"1:0:1:1",--%>
<%--"chargeAmount":"4000.45","productCode":"e8aea93c-b4f8-4726-a930-f041e0ae59e5","productDesc":"编号为608企业充值"}--%>

</body>
</html>