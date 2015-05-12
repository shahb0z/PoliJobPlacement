package it.polito.mobile.polijobplacement.Data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by user on 5/10/2015.
 */
@ParseClassName("JobOffers")
public class JobOffers extends ParseObject {

    private String Title;
    private String Description;
    private Date publishDate;
    private String Category; // health, engineering, education...
    private String EmploymentType; // fulltime, parttime...

    public String getEmploymentType() {
        return EmploymentType;
    }

    public void setEmploymentType(String employmentType) {
        EmploymentType = employmentType;
    }

    public String getCategory() {

        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
