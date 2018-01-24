package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by blue on 2018/1/15.
 */
public class SimpleList<T> {
    private Object[] elementData;
    private int size=0;
    private static final int DEFAULT_CAPACITY = 10;

    public int size() {
        return size;
    }

    public SimpleList(){
        this(DEFAULT_CAPACITY);
    }

    public SimpleList(int initialCapacity){
        if (initialCapacity > 0 && initialCapacity < Integer.MAX_VALUE-8){
            elementData = new Object[initialCapacity];
        } else {
            throw new RuntimeException();
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    public boolean add(T e) {
        int temp = size + 1;
        if (temp > elementData.length){
            System.out.println("扩容："+e);
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            newCapacity = Math.max(DEFAULT_CAPACITY,newCapacity);
            if (newCapacity - MAX_ARRAY_SIZE > 0){

            }
            elementData = Arrays.copyOf(elementData,newCapacity);
        }

        elementData[size++] = e;
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    remove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    remove(index);
                    return true;
                }
        }
        return false;
    }

    public T remove(int index) {
        rangeCheck(index);

        T oldValue = (T) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);

        return (T) elementData[index];
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        System.out.println(list.size());

        SimpleList<String> list1 = new SimpleList<>(1);
        for (int i =0 ;i<1000;i++){
            list1.add(i+"");
        }
        System.out.println(list1.size);
        for (int i =0 ;i<100;i++){
            System.out.println(list1.get(i));
        }
        for (int i =0 ;i<1000;i++){
            System.out.println("删除："+list1.remove(0));
        }
        System.out.println(list1.size);
    }
}
