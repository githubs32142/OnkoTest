package projekt.Class;
public class Person {
    private String name;
    private String surName;
    private Double weight;
    private Double age;
    private String sex;
    private Double bmi;
    private Double height;

    public Person(String name, String surName, Double weight, Double age, String sex,  Double height) {
        this.name = name;
        this.surName = surName;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        bmi= weight/((height/100)*(height/100));
        this.height = height;
    }
    
    public Double getAge() {
        return age;
    }

    public Double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

}
