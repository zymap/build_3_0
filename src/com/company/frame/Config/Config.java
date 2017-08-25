package com.company.frame.Config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;

/**
 * Created by zy on 17-8-18.
 */
public class Config {
    public static void main(String[] args) {
        try {
            File f = new File("./Logger/XML/config.xml");
//            FileInputStream inputStream = new FileInputStream(f);
//            BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream));
//
//            while (reader1.read()>0){
//                System.out.println(reader1.readLine());
//            }
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
//            System.out.println(root.elementText("VALUE"));
            Element foo;
            for (Iterator i = root.elementIterator("LOG"); i.hasNext();) {
                foo = (Element) i.next();
                ConfigInit.logFile = foo.elementText("FILE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
