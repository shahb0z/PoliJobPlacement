package it.polito.mobile.polijobplacement;

import java.util.LinkedList;

/**
 * Created by LeWQ on 2015/5/9 0009.
 */
public class Student1 {
    String name;
    String age;
    String gender;
    String specialty;
    LinkedList<EduBackground> eduBackGround;

    public Student1(String name, String age, String gender, String specialty, LinkedList eduBackGround)
    {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.specialty=specialty;
        this.eduBackGround=eduBackGround;
    }
}
