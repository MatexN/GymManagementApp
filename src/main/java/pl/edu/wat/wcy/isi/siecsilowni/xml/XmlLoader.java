package pl.edu.wat.wcy.isi.siecsilowni.xml;

import com.thoughtworks.xstream.XStream;

import java.util.Properties;

public class XmlLoader {

    public static Program load() {
        Properties properties = new Properties();
        XStream xstream = new XStream();
        xstream.alias("author", Author.class);
        xstream.alias("program", Program.class);
        return (Program) xstream.fromXML(XmlLoader.class.getClassLoader().getResourceAsStream("data.xml"));
    }
}
