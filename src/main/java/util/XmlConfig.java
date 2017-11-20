package util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pojo.Bean;
import pojo.Property;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by keruyun on 2017/10/25.
 * 读取解析xml配置文件
 */
public class XmlConfig {
    public static void parseContext(String path,Map<String, Object> iocMap,Map<String, Bean> configMap){
        //读取xml文件
        Document doc = null;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(XmlConfig.class.getResourceAsStream(path));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("无法读取到xml文件："+path);
        }
        //定义xpath,取出所有的bean
        String xpath = "//bean";
        List<Element> list = doc.selectNodes(xpath);
        if (list!=null){
            for (Element element : list){
                Bean bean = new Bean();
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                bean.setId(id);
                bean.setClassName(className);

                //获取property节点
                List<Element> proList = element.elements("property");
                if (proList != null){
                    for (Element proEle : proList){
                        Property property = new Property();
                        String propName = proEle.attributeValue("name");
                        String propValue = proEle.attributeValue("value");
                        String propRef = proEle.attributeValue("ref");

                        property.setName(propName);
                        property.setValue(propValue);
                        property.setRef(propRef);

                        bean.getProperties().add(property);
                    }
                }

                //id不能重复
                if (configMap.containsKey(id)){
                    throw new RuntimeException("bean节点id重复");
                }
                configMap.put(id,bean);

                //初始化对象
                Object obj = createBean(bean);
                iocMap.put(id,obj);
            }
        }
    }

    private static Object createBean(Bean bean){
        Object object = null;
        try {
            object = Class.forName(bean.getClassName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
