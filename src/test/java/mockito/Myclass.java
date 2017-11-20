package mockito;

/**
 * Created by blue on 2017/11/20.
 */

public class Myclass {
    public String methodToBeTested(){
        return function();
    }

    public String function(){
        throw  new NullPointerException();
    }
}
