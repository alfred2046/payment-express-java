package com.paymentexpress;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class GenerateRequest {

	private String PxPayUserId = "";
	private String PxPayKey = "";
	private String AmountInput = "";
	private String CurrencyInput = "";
	private String EmailAddress = "";
	private String MerchantReference = "";
	private String TxnData1 = "";
	private String TxnData2 = "";
	private String TxnData3 = "";
	private String TxnType = "";
	private String TxnId = "";
	private String BillingId = "";
	private String EnableAddBillCard = "";
	private String UrlSuccess = "";
	private String UrlFail = "";
	private String Opt = "";

	private String Xml;

	public GenerateRequest() {

	}

	private void buildXml() {

		try {
			//document factory create document builder
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//document builder create document
			Document doc = docBuilder.newDocument();
			//document create root node "GenerateRequest"
			Element root = doc.createElement("GenerateRequest");
			// add root node to document
			doc.appendChild(root);

			Element child;
			Text text;
			
			child = doc.createElement("PxPayUserId");
			root.appendChild(child);
			text = doc.createTextNode(this.PxPayUserId);
			child.appendChild(text);

			child = doc.createElement("PxPayKey");
			root.appendChild(child);
			text = doc.createTextNode(this.PxPayKey);
			child.appendChild(text);

			child = doc.createElement("AmountInput");
			root.appendChild(child);
			text = doc.createTextNode(this.AmountInput);
			child.appendChild(text);

			child = doc.createElement("BillingId");
			root.appendChild(child);
			text = doc.createTextNode(this.BillingId);
			child.appendChild(text);

			child = doc.createElement("CurrencyInput");
			root.appendChild(child);
			text = doc.createTextNode(this.CurrencyInput);
			child.appendChild(text);

			child = doc.createElement("EmailAddress");
			root.appendChild(child);
			text = doc.createTextNode(this.EmailAddress);
			child.appendChild(text);

			child = doc.createElement("EnableAddBillCard");
			root.appendChild(child);
			text = doc.createTextNode(this.EnableAddBillCard);
			child.appendChild(text);

			child = doc.createElement("MerchantReference");
			root.appendChild(child);
			text = doc.createTextNode(this.MerchantReference);
			child.appendChild(text);

			child = doc.createElement("Opt");
			root.appendChild(child);
			text = doc.createTextNode(this.Opt);
			child.appendChild(text);

			child = doc.createElement("TxnData1");
			root.appendChild(child);
			text = doc.createTextNode(this.TxnData1);
			child.appendChild(text);

			child = doc.createElement("TxnData2");
			root.appendChild(child);
			text = doc.createTextNode(this.TxnData2);
			child.appendChild(text);

			child = doc.createElement("TxnData3");
			root.appendChild(child);
			text = doc.createTextNode(this.TxnData3);
			child.appendChild(text);

			child = doc.createElement("TxnId");
			root.appendChild(child);
			text = doc.createTextNode(this.TxnId);
			child.appendChild(text);

			child = doc.createElement("TxnType");
			root.appendChild(child);
			text = doc.createTextNode(this.TxnType);
			child.appendChild(text);

			child = doc.createElement("UrlFail");
			root.appendChild(child);
			text = doc.createTextNode(this.UrlFail);
			child.appendChild(text);

			child = doc.createElement("UrlSuccess");
			root.appendChild(child);
			text = doc.createTextNode(this.UrlSuccess);
			child.appendChild(text);

			// ///////////////
			// Output the XML

			// set up a transformer
			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			this.Xml = sw.toString();
		} catch (Exception e) {
		}

	}

	public String getPxPayUserId() {
		return PxPayUserId;
	}

	public void setPxPayUserId(String pxPayUserId) {
		this.PxPayUserId = pxPayUserId;
	}

	public String getPxPayKey() {
		return PxPayKey;
	}

	public void setPxPayKey(String pxPayKey) {
		this.PxPayKey = pxPayKey;
	}

	public String getAmountInput() {
		return AmountInput;
	}

	public void setAmountInput(String amountInput) {
		this.AmountInput = amountInput;
	}

	public String getCurrencyInput() {
		return CurrencyInput;
	}

	public void setCurrencyInput(String currencyInput) {
		this.CurrencyInput = currencyInput;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.EmailAddress = emailAddress;
	}

	public String getMerchantReference() {
		return MerchantReference;
	}

	public void setMerchantReference(String merchantReference) {
		this.MerchantReference = merchantReference;
	}

	public String getTxnData1() {
		return TxnData1;
	}

	public void setTxnData1(String txnData1) {
		this.TxnData1 = txnData1;
	}

	public String getTxnData2() {
		return TxnData2;
	}

	public void setTxnData2(String txnData2) {
		this.TxnData2 = txnData2;
	}

	public String getTxnData3() {
		return TxnData3;
	}

	public void setTxnData3(String txnData3) {
		this.TxnData3 = txnData3;
	}

	public String getTxnType() {
		return TxnType;
	}

	public void setTxnType(String txnType) {
		this.TxnType = txnType;
	}

	public String getTxnId() {
		return TxnId;
	}

	public void setTxnId(String txnId) {
		this.TxnId = txnId;
	}

	public String getBillingId() {
		return BillingId;
	}

	public void setBillingId(String billingId) {
		this.BillingId = billingId;
	}

	public String isEnableAddBillCard() {
		return EnableAddBillCard;
	}

	public void setEnableAddBillCard(String enableAddBillCard) {
		this.EnableAddBillCard = enableAddBillCard;
	}

	public String getUrlSuccess() {
		return UrlSuccess;
	}

	public void setUrlSuccess(String urlSuccess) {
		this.UrlSuccess = urlSuccess;
	}

	public String getUrlFail() {
		return UrlFail;
	}

	public void setUrlFail(String urlFail) {
		this.UrlFail = urlFail;
	}

	public String getOpt() {
		return Opt;
	}

	public void setOpt(String opt) {
		this.Opt = opt;
	}

	public void setXml(String Xml) {
		this.Xml = Xml;
	}

	public String getXml() {
		this.buildXml();
		return this.Xml;	
	}
	
}
