package com.oms.parser;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import com.oms.util.DbUtil;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
public class XmlMap {
	
	public void makeFile(){
		 Element item = null; 
	     Statement st=null;
	     ResultSetMetaData resultmetadata = null;
	     ResultSet rs;
	     Connection connection;
	     Document xmlDoc = null;
	     try{
	     connection = DbUtil.getConnection();
	     xmlDoc = new DocumentImpl();                
	     Element root = xmlDoc.createElement("login_info");
	     xmlDoc.appendChild(root);
	     Element user=null;
	        			
		st=connection.createStatement();
			
		rs=st.executeQuery("select * from login_master");
		
		resultmetadata = rs.getMetaData();
		int numCols = resultmetadata.getColumnCount();
		while (rs.next()) {
			user=xmlDoc.createElement("user");
			root.appendChild(user);	
			for(int i=1;i<=numCols;i++){
				String colName = resultmetadata.getColumnName(i);
				String colVal=null;
				if(resultmetadata.getColumnTypeName(i).equalsIgnoreCase("number"))
				{
					colVal =Long.toString( rs.getLong(i)) ;
				}
				else if(resultmetadata.getColumnTypeName(i).equalsIgnoreCase("varchar2"))
				{
					colVal = rs.getString(i);
				}	
				item = xmlDoc.createElement(colName);
				item.appendChild(xmlDoc.createTextNode(colVal));
				user.appendChild(item);
			}		 
		}
		TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
		Transformer transformer = transformerFactory.newTransformer(); 
		DOMSource domSource = new DOMSource(xmlDoc); 
		StreamResult streamResult = new StreamResult(new File("D:/oms_workspace/OfficeManagementSystem/WebContent/Login.xml")); 
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(domSource, streamResult);
	}catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}
}



