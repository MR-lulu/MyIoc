package mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
/**
 * Created by blue on 2017/11/20.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyclassTest {
    @Spy
    private Myclass myclass;

    @Test
    public void test(){
        //when(myclass.methodToBeTested()).thenReturn("when(...)thenReturn(...)");
        doReturn("doReturn(...)when(...)").when(myclass).methodToBeTested();

        String str = myclass.methodToBeTested();
        System.out.println(str);
    }
}
