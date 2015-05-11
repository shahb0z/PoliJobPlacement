package it.polito.mobile.polijobplacement.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LeWQ on 2015/5/9 0009.
 */
public class Student_2 {
    public String name;
    public String Surname;
    public Date BirthDate;
    public String age;
    public String gender;
   public String specialty;
    LinkedList<EduBackground> eduBackGround;
    List<Languages> LanguageSkills;
    public String PhoneNumber;

    public Student_2(String name, String age, String gender, String specialty, LinkedList eduBackGround)
    {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.specialty=specialty;
        this.eduBackGround=eduBackGround;
    }
}
