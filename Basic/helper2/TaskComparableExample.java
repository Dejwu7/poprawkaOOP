package org.example;

import java.util.PriorityQueue;

class Task implements Comparable<Task>{
    private int priority;
    private String name;

    public Task(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Task o) {
        int a = this.priority;
        int b = o.priority;
        if(a == b) return 0;
        if(a > b) return 1; //rosnąco
        else return -1;
    }

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", name='" + name + '\'' +
                '}';
    }
}
public class TaskComparableExample {
    public static void main(String[] args) {
        PriorityQueue<Task> test = new PriorityQueue<>();
        test.add(new Task(7, "Zakupy"));
        test.add(new Task(1, "Praca"));
        test.add(new Task(10, "Film"));
        test.add(new Task(4, "nauka"));

        while (!test.isEmpty()){
            System.out.println(test.poll()); //posortowane ze względu na priority
        }
    }
}
