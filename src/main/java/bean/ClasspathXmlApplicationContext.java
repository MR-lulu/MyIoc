package bean;

import pojo.Bean;
import pojo.Property;
import util.BeanUtil;
import util.XmlConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by keruyun on 2017/10/25.
 * Bug（注入时依赖于配置文件中bean的先后顺序例如User类中依赖Address类，那么Address必须在User类之前声明）
 * 此Bug已经被修复
 */
public class ClasspathXmlApplicationContext implements BeanFactory{
    private Map<String, Object> ioc; //存储依赖注入后的bean(已实例化）
    private Map<String, Bean> config; //存储bean的配置信息（未实例化）

    //初始化IOC容器
    public ClasspathXmlApplicationContext(String path){
        config = new HashMap<>();
        ioc = new HashMap<>();

        XmlConfig.parseContext(path,ioc,config);
        injectBean();
    }

    //根据配置依赖信息进行注入
    private void  injectBean(){
        if (config.size()!=0){
            config.forEach((id,bean)->{
                injectBean(bean);
            });
        }
    }

    private void injectBean(Bean bean){
        Object object = ioc.get(bean.getId());

        //将依赖注入到对象中
        if (!bean.getProperties().isEmpty() &&
                bean.getProperties()!=null){
            for (Property p : bean.getProperties()){
                //情况一：使用Value属性注入
                if (p.getValue() !=null){
                    //获取属性对应的setter方法
                    Method method = BeanUtil.getSetterMethod(object,p.getName());
                    try {
                        method.invoke(object,p.getValue());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                //情况二: 使用ref属性注入
                if (p.getRef() != null){
                    //获取属性对应的setter方法
                    Method method = BeanUtil.getSetterMethod(object,p.getName());
                    //从容器中找到依赖的对象
                    Object obj = ioc.get(p.getRef());
                    if(obj == null){
                        throw new RuntimeException("没有找到依赖的对象："+p.getRef());
                    }else{
                        //调用set方法注入
                        try {
                            method.invoke(object, obj);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    @Override
    public Object getBean(String name) {
        return ioc.get(name);
    }
    @Override
    public int getBeanNumber(){return ioc.size();}
}
