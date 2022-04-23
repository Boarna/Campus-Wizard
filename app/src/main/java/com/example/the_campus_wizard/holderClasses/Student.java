package com.example.the_campus_wizard.holderClasses;

public class Student {

    public String student_id, first_name, surname, email;

    public Student() {


    }

    public Student(String student_id, String first_name, String surname, String email) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.surname = surname;
        this.email = email;

    }

    public String getStudent_id() {
        return student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
}
