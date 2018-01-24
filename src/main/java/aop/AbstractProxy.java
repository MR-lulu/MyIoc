package aop;

import java.lang.reflect.Method;

/**
 * Created by blue on 2018/1/5.
 */
public abstract class AbstractProxy implements Proxy {

    @Override
    public final void doProxy(ProxyChain proxyChain) {
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (filter(cls, method, params)) {
                before(cls, method, params);
                proxyChain.doProxyChain();
                after(cls, method, params);
            } else {
                proxyChain.doProxyChain();
            }
        } catch (Throwable e) {
            error(cls, method, params, e);
        } finally {
            end();
        }
    }

    public void begin() {
    }

    public boolean filter(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    public void before(Class<?> cls, Method method, Object[] params) {
    }

    public void after(Class<?> cls, Method method, Object[] params) {
    }

    public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
    }

    public void end() {
    }
}
