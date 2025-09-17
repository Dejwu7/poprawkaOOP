package org.example;

import java.util.HashMap;
import java.util.*;

class Employee{
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
public class HashMapBasics {
    //mapuje klucze po wartości, dzięki temu można pobrać
    //ponownie wartość po kluczu. Jest to asocjacja, często używana w programowaniu
    //dostępne metody: clear(), clone(), containsKey(), containsValue()
    //entrySet(), get(), size(), isEmpty(), keySet(), put(),
    //putAll(), values()
    public static void main(String[] args) {
        HashMap<String, Employee> map = new HashMap<>();
        map.put("Adam", new Employee("Adam"));
        map.put("Kasia", new Employee("Kasia"));
        map.put("Ola", new Employee("Ola"));

        Employee e = map.get("Kasia");
        System.out.println(e);

        Iterator i = map.entrySet().iterator();
        while(i.hasNext()){
            Map.Entry entry= (Map.Entry) i.next();
            String key = (String) entry.getKey();
            Employee employee = (Employee) entry.getValue();
            System.out.println(key + " - " + employee);
        }
        System.out.println("\n");
        for(Map.Entry<String, Employee> entry : map.entrySet()){
            String key = entry.getKey();
            Employee employee = entry.getValue();
            System.out.println(key + " - " + employee);
        }
    }
}
