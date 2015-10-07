package com.oms.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.oms.cryptography.AESEncryption;
import com.oms.model.UserTO;


public class Parser {
		public UserTO checkPassword(UserTO userTO)
		{
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(false);
			DocumentBuilder builder;
			Document document=null;
			try
			{
				builder=factory.newDocumentBuilder();				
				document=builder.parse(new File("D:/oms_workspace/OfficeManagementSystem/WebContent/Login.xml"));
			

				NodeList nList = document.getElementsByTagName("user");
				for (int temp = 0; temp < nList.getLength(); temp++) {
			 
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
						
			            try {
							if((Long.parseLong(eElement.getElementsByTagName("EMP_ID").item(0).getTextContent())==userTO.getEmployeeID()&&(eElement.getElementsByTagName("PASSWORD").item(0).getTextContent().equals(AESEncryption.encrypt(userTO.getPassword())))))
							{
								userTO.setFlag(true);
								userTO.setRole(eElement.getElementsByTagName("ROLE").item(0).getTextContent());
								break;
							}
							else
							{
								userTO.setFlag(false);
							}
						} catch (NumberFormatException e) {
							
							e.printStackTrace();
						} catch (DOMException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
	 
			}catch(ParserConfigurationException e){
				e.printStackTrace();
			} catch (SAXException e) {
			} catch (IOException e) {
			}
			return userTO;
		}
}
