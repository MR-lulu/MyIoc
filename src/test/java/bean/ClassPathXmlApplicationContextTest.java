package bean;

import org.junit.Assert;
import org.junit.Test;
import pojo.Address;
import pojo.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by keruyun on 2017/10/25.
 */
public class ClassPathXmlApplicationContextTest {

    public static void ioc(){
        ArrayList<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        Collections.reverse(list);
        System.out.println(list.toString());
        String a[] = new String[list.size()];
        System.out.println(a);
        a = list.toArray(a);
        list.toArray();
        Arrays.stream(a).forEach(System.out::print);
        System.out.println(a);
    }

    public static void main(String[] args) {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("/application.xml");
        User user = (User) context.getBean("tom");
        // Assert.assertNotNull(user);
        System.out.println(user.toString());
        System.out.println(context.getBeanNumber());
        //ioc();
    }
}
