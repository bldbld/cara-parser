/*
 * CARA PROJECT!
 */
package org.cara.core;
 
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * CARA Manager.
 * <p>
 * CARA Manager负责存储CARA的全局信息。
 * </p>
 * 
 * @author GUSSET
 * @version 0.1 11/03/2010
 */
public final class CaraManager {

	public static void main(String[] args) {
		// CaraManager cm = new CaraManager();
		CaraManager.readXmlConfigFile();
	}
	
	/**
	 * 不可实例化。
	 */
	private CaraManager (){
	}

	/**
	 * 初始化工作。
	 */
	public static void initCaraManager() {
		readXmlConfigFile();
	}

	/**
	 * 返回配置选项。
	 */
	public static String getValue(String name) {
		return xmlConfigMap.get(name);
	}

	/**
	 * Read the CaraConfig File.
	 * <p>
	 * 读取CaraConfig文件，并更新到xmlConfigMap。
	 * </p>
	 */
	private static void readXmlConfigFile() {
		xmlConfigMap.clear();
		File f = new File(caraConfigName);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			NodeList properties = doc.getElementsByTagName("properity");
			for (int i = 0; i < properties.getLength(); i++) {
				String name = doc.getElementsByTagName("name").item(i)
						.getFirstChild().getNodeValue();
				String value = doc.getElementsByTagName("value").item(i)
						.getFirstChild().getNodeValue();
				// System.out.println(name + " " + value);
				xmlConfigMap.put(name, value);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回CaraConfig文件名。
	 * 
	 * @return caraConfigName
	 */
	public static String getCaraConfigName() {
		return caraConfigName;
	}

	/**
	 * The value is used for CaraConfig file's name storage.
	 * <p>
	 * CaraConfig文件名。
	 * </p>
	 */
	private static String caraConfigName = "cara-config.xml";

	/**
	 * 记录了CaraConfig内容。
	 * <p>
	 * 记录格式：<NAME, VALUE>。
	 * </p>
	 */
	private static Map<String, String> xmlConfigMap = new HashMap<String, String>();
}
