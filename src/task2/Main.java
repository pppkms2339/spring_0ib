package task2;

import task1.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        for (int i = 0; i < 100; i++) {
            intList.add(i);
        }
        for (int i = 0; i < intList.size(); i++) {
            System.out.println(intList.get(i));
        }
        for (Integer element : intList) {
            System.out.println(element);
        }

        System.out.println();

        HashSet<Double> doubleSet = new HashSet<>();
        doubleSet.add(3.14);
        doubleSet.add(2.0);
        doubleSet.add(3.14);
        for (Double element : doubleSet) {
            System.out.println(element);
        }

        System.out.println();

        HashMap<String, Double> map = new HashMap<>();
        map.put("a", 0.000012);
        map.put("b", 0.00000016);
        for (Map.Entry entry :  map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        HashMap<Person, Integer> map2 = new HashMap<>();

    }

}
