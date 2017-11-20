import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by blue on 2017/11/13.
 */
public class App {
    public void testException(int a) throws Exception{
        if (a==1){
            throw new RuntimeException("抛出runtime异常");
        }
    }
    public void function()throws Exception{
        testException(1);
        System.out.println("asdasdd");
    }
    public void readFile() throws Exception{
        FileInputStream in = new FileInputStream("mmm.txt");
    }
    public static void main(String[] args) throws Exception{
        App app = new App();
        //app.function();
        try {
            app.readFile();
        } catch (FileNotFoundException e){
            System.err.println("找不到该文件");
        }
        System.out.println("asdasdd");
    }
}
