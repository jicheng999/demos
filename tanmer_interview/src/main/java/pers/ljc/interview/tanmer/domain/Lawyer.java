package pers.ljc.interview.tanmer.domain;

import java.util.Date;

/**
 * Created by liujicheng on 2019/5/28.
 */
public class Lawyer {
    private Long id;
    private String lawyerName;
    private String status;
    private Integer barNumber;
    private String city;
    private Date admissionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLawyerName() {
        return lawyerName;
    }

    public void setLawyerName(String lawyerName) {
        this.lawyerName = lawyerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBarNumber() {
        return barNumber;
    }

    public void setBarNumber(Integer barNumber) {
        this.barNumber = barNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
}
