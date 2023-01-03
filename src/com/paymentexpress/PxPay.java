package com.paymentexpress;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import org.w3c.dom.*;
import java.io.*;

public class PxPay {

	public static String GenerateRequest(String userId, String key, GenerateRequest gr, String Url) {

		try {
			gr.setPxPayUserId(userId);
			gr.setPxPayKey(key);

			String inputXml = gr.getXml();
			//send xml request to pxpay 2.0 get a response
			System.out.println("inputXml: " + inputXml);
			System.out.println("submit > " + Url);
			String responseXml = PxPay.SubmitXml(inputXml, Url);

			System.out.println("responseXml < " + responseXml);
			
			//parse response to document
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(responseXml.getBytes("UTF-8"));
			Document doc = docBuilder.parse(is);

			NodeList nodes = doc.getElementsByTagName("Request");

			Element element = (Element) nodes.item(0);
			NodeList name;
			Element line;

			//get redirect url in response
			name = element.getElementsByTagName("URI");
			line = (Element) name.item(0);
			String uri = PxPay.getCharacterDataFromElement(line);
			return uri;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";

	}

	public static Response ProcessResponse(String UserId, String Key, String result, String Url) throws Exception {

		String inputXml = "<ProcessResponse>"
				+ "<PxPayUserId>" + UserId + "</PxPayUserId>"
				+ "<PxPayKey>" + Key + "</PxPayKey>"
				+ "<Response>" + result + "</Response>"
				+ "</ProcessResponse>";
		
		String outputXml;

		outputXml = PxPay.SubmitXml(inputXml, Url);

		Response response = new Response(outputXml);
		return response;

	}

	private static String SubmitXml(String Xml, String Url) throws Exception {
		HttpClient client = new DefaultHttpClient();

		// Prepare the POST request
		HttpPost pxpayRequest = new HttpPost(Url);
		pxpayRequest.setEntity(new StringEntity(Xml));

		// Execute the request and extract the response
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = client.execute(pxpayRequest, responseHandler);

		return responseBody;
	}

	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
}
	
}
