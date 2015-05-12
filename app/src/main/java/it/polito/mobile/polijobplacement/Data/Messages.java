package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by user on 5/13/2015.
 */
@ParseClassName("Messages")
public class Messages extends ParseObject {
    private Company from_forStudent;
    private Student from_forCompany;
    private String title;
    private String content;

    public Company getFrom_forStudent() {
        return from_forStudent;
    }

    public void setFrom_forStudent(Company from_forStudent) {
        this.from_forStudent = from_forStudent;
    }

    public Student getFrom_forCompany() {
        return from_forCompany;
    }

    public void setFrom_forCompany(Student from_forCompany) {
        this.from_forCompany = from_forCompany;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

