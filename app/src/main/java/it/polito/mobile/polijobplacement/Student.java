package it.polito.mobile.polijobplacement;

import java.util.LinkedList;

/**
 * Created by LeWQ on 2015/5/9 0009.
 */
public class Student {
    String name;
    String age;
    String gender;
    String specialty;
    LinkedList<EduBackground> eduBackGround;

    public Student(String name,String age,String gender,String specialty,LinkedList eduBackGround)
    {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.specialty=specialty;
        this.eduBackGround=eduBackGround;
    }
}
