<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="com.paymentexpress.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="responseBean" class="com.paymentexpress.Response" />
<jsp:setProperty name="responseBean" property="*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Payment Express - Java Implementation</title>
</head>
<body>
<%
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//Below three values configurable from web.xml
	String PxPayUrl = getServletContext().getInitParameter("PxPayUrl");
	String PxPayUserId = getServletContext().getInitParameter("PxPayUserId");
	String PxPayKey = getServletContext().getInitParameter("PxPayKey");

	System.out.println(dateFormat.format(Calendar.getInstance().getTime()) +  " pxpayuserid: " + PxPayUserId);
	System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " pxpayurl: " + PxPayUrl);
	
	if (request.getParameter("submit") != null) {
		System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " submit: " + request.getParameter("submit"));
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		System.out.println(req);
		String url = req.getRequestURL().toString();
		System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " url " + url);
		
		GenerateRequest gr = new GenerateRequest();

		gr.setAmountInput(request.getParameter("amount"));
		gr.setBillingId(request.getParameter("billingId"));
		gr.setCurrencyInput(request.getParameter("currencyInput"));
		gr.setEmailAddress(request.getParameter("emailAddress"));
		gr.setEnableAddBillCard(request.getParameter("enableAddBillCard"));
		gr.setMerchantReference(request.getParameter("merchantReference"));
		gr.setOpt(request.getParameter("opt"));
		gr.setTxnData1(request.getParameter("txnData1"));
		gr.setTxnData2(request.getParameter("txnData2"));
		gr.setTxnData3(request.getParameter("txnData3"));
		gr.setTxnId(request.getParameter("txnId"));
		gr.setTxnType(request.getParameter("txnType"));
		gr.setUrlFail(url);
		gr.setUrlSuccess(url);
				
		String redirectUrl = PxPay.GenerateRequest(PxPayUserId, PxPayKey, gr, PxPayUrl);

		if (redirectUrl != "") {
			System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " Success!!");
			response.sendRedirect(redirectUrl);
		} else {
			//failure to generate payment page URL here
			System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " Failure");
			redirectUrl = this.getServletContext().getAttribute("failUrl").toString();
			response.sendRedirect(redirectUrl);
		}
		System.out.println("within submit");
	} else if (request.getParameter("result") != null) {
		System.out.println("within result");
		try {
			//Return of response payload, handle at PayPx servlet
			//http://localhost:8080/PayPx/?result=000900545941900019daae659177&userid=XYZ
			responseBean = PxPay.ProcessResponse(PxPayUserId, PxPayKey, request.getParameter("result"), PxPayUrl);
			System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " response text: " + responseBean.getResponseText());
			System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " txnid: " + responseBean.getTxnId());
			System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " CN: " + responseBean.getCardNumber());
			System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " CN2: " + responseBean.getCardNumber2());
		} catch (Exception e) {
			//failure to decode result
			System.out.println(dateFormat.format(Calendar.getInstance().getTime()) + " " + e.toString());
			e.printStackTrace();
		}
	}
%>
<div>
<div style="float: left;">
<form action="" method="post">
<table>
	<tr>
		<td colspan="2" align="center"><b>Input</b></td>
	</tr>
	<tr>
		<td>Amount</td>
		<td><input type="text" name="amount" value="1.00" /></td>
	</tr>
	<tr>
		<td>Billing ID</td>
		<td><input type="text" name="billingId" /></td>
	</tr>
	<tr>
		<td>Currency</td>
		<td><input type="text" name="currencyInput" value="USD" /></td>
	</tr>
	<tr>
		<td>Email Address</td>
		<td><input type="text" name="emailAddress" /></td>
	</tr>
	<tr>
		<td>Enable Add Bill Card</td>
		<td><input type="text" name="enableAddBillCard" /></td>
	</tr>
	<tr>
		<td>Merchant Reference</td>
		<td><input type="text" name="merchantReference"
			value="my merchant ref" /></td>
	</tr>
	<tr>
		<td>TxnData 1</td>
		<td><input type="text" name="txnData1" /></td>
	</tr>
	<tr>
		<td>TxnData 2</td>
		<td><input type="text" name="txnData2" /></td>
	</tr>
	<tr>
		<td>TxnData 3</td>
		<td><input type="text" name="txnData3" /></td>
	</tr>
	<tr>
		<td>Txn ID</td>
		<td><input type="text" name="txnId" /></td>
	</tr>
	<tr>
		<td>Transaction Type</td>
		<td><input type="text" name="txnType" value="Purchase" /></td>
	</tr>
	<tr>
		<td>Options</td>
		<td><input type="text" name="opt" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" name="submit" /></td>
	</tr>
</table>
</form>
</div>
<div>
<table>
	<tr>
		<td colspan="2" align="center"><b>Output</b></td>
	</tr>
	<tr>
		<td>Amount</td>
		<td><%=responseBean.getAmountSettlement()%></td>
	</tr>
	<tr>
		<td>Auth Code</td>
		<td><%=responseBean.getAuthCode()%></td>
	</tr>
	<tr>
		<td>Billing ID</td>
		<td><%=responseBean.getBillingId()%></td>
	</tr>
	<tr>
		<td>Cardholder Name</td>
		<td><%=responseBean.getCardHolderName()%></td>
	</tr>
	<tr>
		<td>Card Name</td>
		<td><%=responseBean.getCardName()%></td>
	</tr>
	<tr>
		<td>Card Number</td>
		<td><%=responseBean.getCardNumber()%></td>
	</tr>
	<tr>
		<td>Cardnumber2</td>
		<td><%//responseBean.getCardNumber2()%></td>
	</tr>
	<tr>
		<td>Client Info</td>
		<td><%=responseBean.getClientInfo()%></td>
	</tr>
	<tr>
		<td>Currency Input</td>
		<td><%=responseBean.getCurrencyInput()%></td>
	</tr>
	<tr>
		<td>Amount Settlement</td>
		<td><%=responseBean.getAmountSettlement()%></td>
	</tr>
	<tr>
		<td>Expiry Date</td>
		<td><%=responseBean.getDateExpiry()%></td>
	</tr>
	<tr>
		<td>DPS Billing ID</td>
		<td><%=responseBean.getDpsBillingId()%></td>
	</tr>
	<tr>
		<td>DPS Txn Ref</td>
		<td><%//responseBean.getDpsTxnRef()%></td>
	</tr>
	<tr>
		<td>Email Address</td>
		<td><%=responseBean.getEmailAddress()%></td>
	</tr>
	<tr>
		<td>Merchant Reference</td>
		<td><%=responseBean.getMerchantReference()%></td>
	</tr>
	<tr>
		<td>Response Text</td>
		<td><%=responseBean.getResponseText()%></td>
	</tr>
	<tr>
		<td>Success</td>
		<td><%=responseBean.getSuccess()%></td>
	</tr>
	<tr>
		<td>TxnData1</td>
		<td><%=responseBean.getTxnData1()%></td>
	</tr>
	<tr>
		<td>TxnData2</td>
		<td><%=responseBean.getTxnData2()%></td>
	</tr>
	<tr>
		<td>TxnData3</td>
		<td><%=responseBean.getTxnData3()%></td>
	</tr>
	<tr>
		<td>TxnId</td>
		<td><%=responseBean.getTxnId()%></td>
	</tr>
	<tr>
		<td>TxnMac</td>
		<td><%=responseBean.getTxnMac()%></td>
	</tr>
	<tr>
		<td>Transaction Type</td>
		<td><%=responseBean.getTxnType()%></td>
	</tr>
</table>
</div>
</div>
</body>
</html>