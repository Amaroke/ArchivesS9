
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        exercice2();
    }

    public static void exercice1() {
        callWebService call = new callWebService();
        String url = "https://jsonplaceholder.typicode.com/";
        String serviceName = "posts";
        int code = 1;
        System.out.println("----------------------------");
        System.out.println("url : " + url);
        System.out.println("serviceName " + serviceName);
        System.out.println("code : " + code);
        System.out.println("----------------------------");
        call.initializeService(url);
        String response = call.callJSONPlaceholderService(serviceName, code);
        System.out.println("Response of callJSONPlaceholderService" + response);
        System.out.println("----------------------------");
        System.out.println("Parsing the JSON response");
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(response);
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("UserId : " + jsonObject.get("userId"));
            System.out.println("ID : " + jsonObject.get("id"));
            System.out.println("Titre : " + jsonObject.get("title"));
            System.out.println("Contenu : " + jsonObject.get("body"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void exercice2() {
        callWebService call = new callWebService();
        String url = "http://api.geonames.org/";
        String serviceName = "countryInfo";
        String isoCode = "FR";
        String login = "JOHN.DOE.DOE";
        System.out.println("----------------------------");
        System.out.println("url : " + url);
        System.out.println("serviceName " + serviceName);
        System.out.println("isoCode : " + isoCode);
        System.out.println("login : " + login);
        System.out.println("----------------------------");
        call.initializeService(url);
        String response = call.callCountryInfoService(serviceName, isoCode, login);
        System.out.println("Response of callCountryInfoService" + response);
        System.out.println("----------------------------");
        System.out.println("Parsing the XML response");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = null;
        try {
            document = builder.parse(new InputSource(new StringReader(response)));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Nom du pays : " + element.getElementsByTagName("countryName").item(0).getTextContent());
                System.out.println("Continent : " + element.getElementsByTagName("continent").item(0).getTextContent());
                System.out.println("Capital : " + element.getElementsByTagName("capital").item(0).getTextContent());
                System.out.println("Monnaie : " + element.getElementsByTagName("currencyCode").item(0).getTextContent());
            }
        }
    }

    public static void exo3() {
        String url = "https://labs.bible.org/";
        String serviceName = "api";
        String title = "John";
        String chapter = "3";
        String verse = "16";
        String type = "json";
        callWebService call = new callWebService();
        call.initializeService(url);
        System.out.println("----------------------------");
        System.out.println("url : " + url);
        System.out.println("serviceName : " + serviceName);
        System.out.println("title : " + title);
        System.out.println("chapter : " + chapter);
        System.out.println("verse : " + verse);
        System.out.println("type : " + type);
        System.out.println("----------------------------");
        System.out.println("Response of callBibleTagService : ");
        String response = call.callBibleTagService(serviceName, title, Integer.parseInt(chapter), Integer.parseInt(verse), type);
        // suppression des [] du début et de la fin de la réponse pour pouvoir parser le JSON
        response = response.substring(1, response.length() - 1);
        System.out.println(response);
        System.out.println("----------------------------");
        System.out.println("Parsing the JSON response");
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(response);
            System.out.println("----------------------------");
            System.out.println("Bookname : " + obj.get("bookname"));
            System.out.println("Chapter : " + obj.get("chapter"));
            System.out.println("Verse : " + obj.get("verse"));
            System.out.println("Text : " + obj.get("text"));
            System.out.println("----------------------------");
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("----------------------------");
        String[] titleTab = {"John", "Acts"};
        int[] chapterTab = {3, 1};
        int[] verseTab = {16, 12};
        type = "xml";
        System.out.println("url : " + url);
        System.out.println("serviceName : " + serviceName);
        System.out.println("title : " + "{ " + titleTab[0] + ", " +  titleTab[1] + " }");
        System.out.println("chapter : " + "{ " + chapterTab[0] + ", " +  chapterTab[1] + " }");
        System.out.println("verse : " + "{ " + verseTab[0] + ", " +  verseTab[1] + " }");
        System.out.println("type : " + type);
        System.out.println("----------------------------");
        System.out.println("Response of callBibleMutiTagService : ");
        response = call.callBibleMutiTagService(serviceName, titleTab, chapterTab, verseTab, type);
        System.out.println(response);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("item");

            System.out.println("----------------------------");
            System.out.println("Parsing the XML response");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element eElement = (Element) nList.item(temp);
                System.out.println("bookname : " + eElement.getElementsByTagName("bookname").item(0).getTextContent());
                System.out.println("chapter : " + eElement.getElementsByTagName("chapter").item(0).getTextContent());
                System.out.println("verse : " + eElement.getElementsByTagName("verse").item(0).getTextContent());
                System.out.println("text : " + eElement.getElementsByTagName("text").item(0).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}