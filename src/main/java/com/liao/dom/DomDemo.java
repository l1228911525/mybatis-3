package com.liao.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileOutputStream;

public class DomDemo {

  /**
   * 遍历xml文档
   * */
  public static void queryXml(){
    try{
      //得到DOM解析器的工厂实例
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      //从DOM工厂中获得DOM解析器
      DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
      //把要解析的xml文档读入DOM解析器
      Document doc = dbBuilder.parse("src/main/java/com/liao/dom/testDom.xml");
      System.out.println("处理该文档的DomImplementation对象  = "+ doc.getImplementation());
      //得到文档名称为Student的元素的节点列表
      NodeList nList = doc.getElementsByTagName("School");
      Element item = (Element)nList.item(0);
      nList = item.getElementsByTagName("Student");
      //遍历该集合，显示结合中的元素及其子元素的名字
      for(int i = 0; i< nList.getLength() ; i ++){
        Element node = (Element)nList.item(i);
        System.out.println("Name: "+ node.getElementsByTagName("Name").item(0).getFirstChild().getNodeValue());
        System.out.println("Num: "+ node.getElementsByTagName("Num").item(0).getFirstChild().getNodeValue());
        System.out.println("Classes: "+ node.getElementsByTagName("Classes").item(0).getFirstChild().getNodeValue());
        System.out.println("Address: "+ node.getElementsByTagName("Address").item(0).getFirstChild().getNodeValue());
        System.out.println("Tel: "+ node.getElementsByTagName("Tel").item(0).getFirstChild().getNodeValue());
      }

    }catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
  public static void main(String[] args){
    //读取
    DomDemo.queryXml();
  }
}
