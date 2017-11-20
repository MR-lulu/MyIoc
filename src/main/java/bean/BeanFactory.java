package bean;

/**
 * Created by keruyun on 2017/10/25.
 */
public interface BeanFactory {
    public Object getBean(String name);
    public int getBeanNumber();
}
