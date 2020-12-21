<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.xingyun.pay.web.AlipayNotify" %>
<%
    //获取支付宝POST过来反馈信息
    Map<String, String> params = new HashMap<String, String>();
    Map requestParams = request.getParameterMap();
    out.print(requestParams);
    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
        String name = (String) iter.next();
        String[] values = (String[]) requestParams.get(name);
        String valueStr = "";
        for (int i = 0; i < values.length; i++) {
            valueStr = (i == values.length - 1) ? valueStr + values[i]
                    : valueStr + values[i] + ",";
        }
        //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
        //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
        params.put(name, valueStr);
    }

    //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
    //批次号
    //////////////////////////////////////////////////////////////////////////////////////////
    //请在这里加上商户的业务逻辑程序代码

    //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

    //判断是否在商户网站中已经做过了这次通知返回的处理
    //如果没有做过处理，那么执行商户的业务程序
    //如果有做过处理，那么不执行商户的业务程序

    out.println("success");
    boolean verify = AlipayNotify.verify(params);
//        CommonUtil.sendSms("ok","13699237075");
    //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

    //////////////////////////////////////////////////////////////////////////////////////////

%>
