package com.automation.pojos;

/*
   /*
    {
    "studentId": 11693,
    "firstName": "Ainura",
    "lastName": "Nora",
    "batch": 15,
    "joinDate": "01/01/2020",
    "birthDate": "01/01/2020",
    "password": "1234",
    "subject": "Java",
    "gender": "Female",
    "admissionNo": "8888",
    "major": "CS",
    "section": "101010",
    "contact": {
        "contactId": 11713,
        "phone": "240-123-1231",
        "emailAddress": "meme@gmail.com",
        "premanentAddress": "7925 Jones Branch Dr"
    },
    "company": {
        "companyId": 11633,
        "companyName": "Cybertek",
        "title": "SDET",
        "startDate": "02/02/2020",
        "address": {
            "addressId": 11653,
            "street": "7777 NO NAME  Dr",
            "city": "Boston",
            "state": "Massachusetts",
            "zipCode": 22102
        }
    }
}
Student id: 11693

Student has Company and Company has address --? this is nested Json payload
 */



public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private int batch;
    private String joinDate;
    private String birthDate;
    private String password;
    private String subject;
    private String gender;
    private String admissionNo;
    private String majorNo;
    private String section;
    private Contact contact;
    private Company company;



    public int getStudentId() {
        return studentId;
    }

//    public void setStudentId(int studentId) {
//        this.studentId = studentId;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", batch=" + batch +
                ", joinFate='" + joinDate + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                ", gender='" + gender + '\'' +
                ", admissionNo='" + admissionNo + '\'' +
                ", majorNo='" + majorNo + '\'' +
                ", section='" + section + '\'' +
                ", contact=" + contact +
                ", company=" + company +
                '}';
    }



}
