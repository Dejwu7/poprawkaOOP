package org.example;

import java.util.ArrayList;
import java.util.Iterator;

class Person{
    private String name;

    public Person(String name) {
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
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Employee2 extends Person {
    private String jobTitle;

    public Employee2(String name, String jobTitle) {
        super(name);
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "EmployeE{" +
                "name: " + this.getName() +
                "jobTitle='" + jobTitle + '\'' +
                '}';
    }
}

class Contractor extends Employee2 {
    public Contractor(String name, String jobTitle) {
        super(name, jobTitle);
    }

    @Override
    public String toString() {
        return "Contractor{ " + super.toString() + "}";
    }
}

class Organisation <T extends Person>{
    private ArrayList<T> participans;

    public Organisation() {
        participans = new ArrayList<T>();
    }

    public void addParticipants(T p){
        participans.add(p);
    }

    public T getParticipant(int index){
        return participans.get(index);
    }

    public void printParticipants(){
        Iterator<T> iter = participans.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
public class GenercisExample {
    public static void main(String[] args) {
        Organisation<Employee2> organisation = new Organisation<>();
        organisation.addParticipants(new Employee2("Artur", "Programista"));
        organisation.addParticipants(new Employee2("Kasia", "lekarka"));
        organisation.addParticipants(new Contractor("Daniel", "programista"));
        organisation.printParticipants();;
    }
}
