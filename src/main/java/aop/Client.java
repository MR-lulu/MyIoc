package aop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blue on 2018/1/5.
 */
public class Client {

    public static void main(String[] args) {
        List<Proxy> proxyList = new ArrayList<Proxy>();
        proxyList.add(new BeforeProxy());

        proxyList.add(new AfterProxy());
        proxyList.add(new EndProxy());

        ProxyManager proxyManager = new ProxyManager(GreetingImpl.class, proxyList);
        GreetingImpl greetingProxy = proxyManager.createProxy();

        greetingProxy.sayHello("Jack");
    }
}