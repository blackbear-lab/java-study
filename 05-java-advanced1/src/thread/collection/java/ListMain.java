package thread.collection.java;

import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list = " + list);
    }
}
