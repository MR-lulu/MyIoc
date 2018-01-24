package aop;

/**
 * Created by blue on 2018/1/5.
 */
public class EndProxy extends AbstractProxy{
    @Override
    public void end() {
        System.out.println("end");
    }
}
