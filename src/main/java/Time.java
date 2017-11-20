import java.util.ArrayList;

/**
 * Created by blue on 2017/11/15.
 */
public class Time {
    public static void main(String[] args) {
        Long beginTime = System.currentTimeMillis();
        ArrayList<String> list = new ArrayList<>();
        int i;
        for (i =0;i<60000;i++){
            if (i%11==0){
                list.clear();
            } else {
                list.add("i");
            }
        }
        Long endTime = System.currentTimeMillis();
        Long time = endTime-beginTime;
        System.out.println(list.size());
        System.out.println("循环6万次所花的时间为："+time+"毫秒");
    }
}
