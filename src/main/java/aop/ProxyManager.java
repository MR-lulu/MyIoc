package aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by blue on 2018/1/5.
 */
public class ProxyManager {

    private Class<?> targetClass;
    private List<Proxy> proxyList;

    public ProxyManager(Class<?> targetClass, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.proxyList = proxyList;
    }

    @SuppressWarnings("unchecked")
    public <T> T createProxy() {
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                ProxyChain proxyChain = new ProxyChain(targetClass, target, method, args, proxy, proxyList);
                proxyChain.doProxyChain();
                return proxyChain.getMethodResult();
            }
        });
    }
}