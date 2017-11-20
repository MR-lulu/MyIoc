package pojo;

import lombok.Data;

/**
 * Created by keruyun on 2017/10/25.
 */
@Data
public class Property {
    private String name;

    //既可以使用value来指定注入字面量，也可以使用ref来指定注入对象引用
    private String value;
    private String ref;
}
