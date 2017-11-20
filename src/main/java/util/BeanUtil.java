package util;

import java.lang.reflect.Method;

/**
 * Created by keruyun on 2017/10/25.
 */
public class BeanUtil {
    //获取对象name属性的setter方法
    public static Method getSetterMethod(Object object, String name) {
        Method method = null;
        //拼接setter方法名
        name = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        //获取setter方法
        try {
            Method methods[] = object.getClass().getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method m = methods[i];
                if (m.getName().equals(name)) {
                    method = object.getClass().getMethod(name, m.getParameterTypes());
                    break;
                }
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("没有找到setter方法");
        }
        return method;
    }
}
