package com.paymentexpress;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;


public class Response {

	private String valid;
	private String amountSettlement;
	private String authCode;
	private String cardName;
	private String cardNumber;
	private String dateExpiry;
	private String dpsTxnRef;
	private String success;
	private String responseText;
	private String dpsBillingId;
	private String cardHolderName;
	private String currencySettlement;
	private String txnData1;
	private String txnData2;
	private String txnData3;
	private String txnType;
	private String currencyInput;
	private String merchantReference;
	private String clientInfo;
	private String txnId;
	private String emailAddress;
	private String billingId;
	private String txnMac;
	private String cardNumber2;

	public Response(String Xml) {

		this.setProperties(Xml);
	}

	public Response() {

	}

	private void setProperties(String Xml) {

		try {
			InputStream is = new ByteArrayInputStream(Xml.getBytes("UTF-8"));

			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(is);

			NodeList nodes = doc.getElementsByTagName("Response");

			Element element = (Element) nodes.item(0);
			this.valid = element.getAttribute("valid");

			NodeList name;
			Element line;

			name = element.getElementsByTagName("AmountSettlement");
			line = (Element) name.item(0);
			this.amountSettlement = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("AuthCode");
			line = (Element) name.item(0);
			this.authCode = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("BillingId");
			line = (Element) name.item(0);
			this.billingId = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("CardHolderName");
			line = (Element) name.item(0);
			this.cardHolderName = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("CardName");
			line = (Element) name.item(0);
			this.cardName = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("CardNumber");
			line = (Element) name.item(0);
			this.cardNumber = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("CardNumber2");
			line = (Element) name.item(0);
			this.cardNumber2 = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("ClientInfo");
			line = (Element) name.item(0);
			this.clientInfo = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("CurrencyInput");
			line = (Element) name.item(0);
			this.currencyInput = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("CurrencySettlement");
			line = (Element) name.item(0);
			this.currencySettlement = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("DateExpiry");
			line = (Element) name.item(0);
			this.dateExpiry = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("DpsBillingId");
			line = (Element) name.item(0);
			this.dpsBillingId = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("DpsTxnRef");
			line = (Element) name.item(0);
			this.dpsTxnRef = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("EmailAddress");
			line = (Element) name.item(0);
			this.emailAddress = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("MerchantReference");
			line = (Element) name.item(0);
			this.merchantReference = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("ResponseText");
			line = (Element) name.item(0);
			this.responseText = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("Success");
			line = (Element) name.item(0);
			this.success = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("TxnData1");
			line = (Element) name.item(0);
			this.txnData1 = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("TxnData2");
			line = (Element) name.item(0);
			this.txnData2 = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("TxnData3");
			line = (Element) name.item(0);
			this.txnData3 = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("TxnId");
			line = (Element) name.item(0);
			this.txnId = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("TxnMac");
			line = (Element) name.item(0);
			this.txnMac = getCharacterDataFromElement(line);

			name = element.getElementsByTagName("TxnType");
			line = (Element) name.item(0);
			this.txnType = getCharacterDataFromElement(line);

		} catch (Exception e) {
			// TODO: Handle Exception if XML cannot be parsed
		}

	}

	private String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	public String isValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getAmountSettlement() {
		return amountSettlement;
	}

	public void setAmountSettlement(String amountSettlement) {
		this.amountSettlement = amountSettlement;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(String dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public String getDpsTxnRef() {
		return dpsTxnRef;
	}

	public void setDpsTxnRef(String dpsTxnRef) {
		this.dpsTxnRef = dpsTxnRef;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public String getDpsBillingId() {
		return dpsBillingId;
	}

	public void setDpsBillingId(String dpsBillingId) {
		this.dpsBillingId = dpsBillingId;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCurrencySettlement() {
		return currencySettlement;
	}

	public void setCurrencySettlement(String currencySettlement) {
		this.currencySettlement = currencySettlement;
	}

	public String getTxnData1() {
		return txnData1;
	}

	public void setTxnData1(String txnData1) {
		this.txnData1 = txnData1;
	}

	public String getTxnData2() {
		return txnData2;
	}

	public void setTxnData2(String txnData2) {
		this.txnData2 = txnData2;
	}

	public String getTxnData3() {
		return txnData3;
	}

	public void setTxnData3(String txnData3) {
		this.txnData3 = txnData3;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getCurrencyInput() {
		return currencyInput;
	}

	public void setCurrencyInput(String currencyInput) {
		this.currencyInput = currencyInput;
	}

	public String getMerchantReference() {
		return merchantReference;
	}

	public void setMerchantReference(String merchantReference) {
		this.merchantReference = merchantReference;
	}

	public String getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getBillingId() {
		return billingId;
	}

	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	public String getTxnMac() {
		return txnMac;
	}

	public void setTxnMac(String txnMac) {
		this.txnMac = txnMac;
	}

	public String getCardNumber2() {
		return cardNumber2;
	}

	public void setCardNumber2(String cardNumber2) {
		this.cardNumber2 = cardNumber2;
	}
	
}
