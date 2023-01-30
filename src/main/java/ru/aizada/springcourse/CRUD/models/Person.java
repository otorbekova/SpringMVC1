package ru.aizada.springcourse.CRUD.models;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min=2,max=15,message = "Name should moree charectors or less")
    private String name;

    @Email(message = "It is not email!")
    @NotEmpty(message = "Name should not be empty!")
    private String email;

    @Min(value=0,message = "More than 0")
    private int age;


    public Person(){

    }
    public Person (int id,String name,int age,String email){
        this.id=id;
        this.name=name;
        this.age=age;
        this.email=email;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
