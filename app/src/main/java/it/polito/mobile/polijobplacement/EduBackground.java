package it.polito.mobile.polijobplacement;

/**
 * Created by LeWQ on 2015/5/9 0009.
 */
public class EduBackground {
    String degree;
    String university;
    String major;

    public EduBackground(String degree, String university, String major)
    {
        this.degree=degree;
        this.university=university;
        this.major=major;
    }

    public String getDegree() {
        return degree;
    }

    public String getMajor() {
        return major;
    }

    public String getUniversity(){
        return university;
    }
}
