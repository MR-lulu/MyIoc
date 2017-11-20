package pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keruyun on 2017/10/25.
 * 使用lombok@Data注解自动生成get，set方法
 */
@Data
public class Bean {
    private String id; //bean的id
    private String className; //bean的className
    private List<Property> properties = new ArrayList<>(); //bean节点下可以有多个property节点
}
