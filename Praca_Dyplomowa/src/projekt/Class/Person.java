package projekt.Class;
public class Person {
    private String name;
    private String surName;
    private Double weight;
    private Double age;
    private String sex;
    private Double bmi;
    private Double height;
    private String email;

    public Person(String name, String surName, Double weight, Double age, String sex,  Double height) {
        this.name = name;
        this.surName = surName;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        bmi= weight/(height*height);
        this.height = height;
        email="";
    }
        public Person( String email, Double weight, Double age, String sex,  Double height) {
        this.name = "";
        this.surName = "";
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        bmi= weight/((height/100)*(height/100));
        this.height = height;
        this.email = email;
    }
    public Person() {
    }

    public String getSurName() {
        return surName;
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

    @Override
    public String toString() {
        return name.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
