package com.mb.api.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Utilities {
    public static final String ENVIRONMENT = "sit";

    public static final String PATH_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/";
//    public static final String PATH_RESOURCE = Utilities.class.getResource("/environments").getPath();

    /**
     *
     * @param path of file
     * @return content of file
     * @throws IOException
     */
    public static String getContentsFile(String path) throws IOException {
//        String filePath = System.getProperty("user.dir") + ("/src/test/resources/" + path).replaceAll("\\/{2,}","/");
        String filePath = path;
        System.out.printf(filePath);
        try {
//            String filePath = Utilities.class.getResource(path).getPath();
//            String filePath = Utilities.class.getResource(path).getPath();
            return FileUtils.readFileToString(new File(filePath), String.valueOf(StandardCharsets.UTF_8));
        } catch (Exception e) {
            InputStream in = Files.newInputStream(Paths.get(filePath));
//            in.getClass().getResourceAsStream(path).getClass().getPackage().getName();
            return IOUtils.toString(in, String.valueOf(StandardCharsets.UTF_8));
        }
    }

    /**
     *
     * @param path of josn file
     * @param jsonPaths json key path
     * @param values json value, can be String[] or value1, value2, vlaue3,...
     * @return json string had been edited
     * @throws Exception
     *
     */

    public static String modifyJson(String path, String[] jsonPaths, String... values) throws Exception {
        Configuration configuration = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
        DocumentContext parsed = JsonPath.using(configuration).parse(getContentsFile(path));
        for (int i = 0; i < jsonPaths.length; i++) {
            if (!ObjectUtils.isEmpty(jsonPaths[i])) {
                if (values[i].equals("{DELETE}")) {
                    parsed.delete(jsonPaths[i]);
                } else {
                    Object field = parsed.read(jsonPaths[i]);
                    if (ObjectUtils.isEmpty(field)) {
                        int index = jsonPaths[i].lastIndexOf(".");
                        parsed.put(jsonPaths[i].substring(0, index), jsonPaths[i].substring(index + 1), values[i]);
                    } else {
                        parsed.set(jsonPaths[i], values[i]);
                    }
                }
            }
        }
        return parsed.jsonString();
    }

    public static String getFilePathFromDataConf(String config) {
        return (PATH_RESOURCE + getValueOfDataConf(config)).replaceAll("\\/{2,}","/");
    }
    public static String getValueOfDataConf(String path) {
        return readValueFromXMLFile(path, "environments/datasource-" + ENVIRONMENT + ".xml");
    }

    public static String readValueFromXMLFile(String path, String fileName) {
        String[] item = path.split("\\.");
        String value = null;
        try {
            Map<String, Node> nodeSet = readNodeValueFromXMLFile(fileName, "//type[@name='" + item[0] + "']//object");
            Node node = nodeSet.get(item[1]);
            NodeList nodeList = ((Element) node).getElementsByTagName("attribute");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getAttributes().getNamedItem("name").getNodeValue().equals(item[2])) {
                    value = nNode.getAttributes().getNamedItem("value").getNodeValue();
                    break;
                }
            }
        } catch (Exception ex) {
//            throw new Exception("Cannot find item " + path + " in " + fileName + ". ", ex);
        }
        return value;
    }

    public static Map<String, Node> readNodeValueFromXMLFile(String fileName, String path) {
        Map<String, Node> nodeSet = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc1 = builder.parse(getContentAsStream("/" + fileName));
            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expression = xpath.compile(path);
            NodeList nList = (NodeList) expression.evaluate(doc1.getDocumentElement(), XPathConstants.NODESET);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                String value = nNode.getAttributes().getNamedItem("name").getNodeValue();
                nodeSet.put(value, nNode);
            }
        } catch (Exception ex) {
//            throw new Exception("Cannot find item " + path + " in " + fileName + ". ", ex);
        }
        return nodeSet;
    }

    public static InputStream getContentAsStream(String path) {
        String filePath = System.getProperty("user.dir") + ("/src/test/resources/" + path).replaceAll("\\/{2,}","/");
        try {
            return Files.newInputStream(Paths.get(filePath));
        } catch (IOException e) {
            return Utilities.class.getResourceAsStream(path);
        }
    }

}
