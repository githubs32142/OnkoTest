package projekt.Class;
public class Person {
    private String name;
    private String surName;
    private Double weight;
    private int age;
    private String sex;
    private Double bmi;
    private int height;
    private String email;

    public Person(String name, String surName, Double weight, int age, String sex,  int height) {
        this.name = name;
        this.surName = surName;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        double tmp = height;
        bmi= weight/(((tmp/100)*(tmp/100)));
        this.height = height;
        email="";
    }
        public Person( String email, Double weight, int age, String sex,  int height) {
        this.name = "";
        this.surName = "";
        this.weight = weight;
        this.age = age;
        this.sex = sex;
        this.height = height;
        double tmp = height;
        bmi= weight/(((tmp/100)*(tmp/100)));
        this.email = email;
    }
    public Person() {
    }

    public String getSurName() {
        return surName;
    }
    
    public int getAge() {
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
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
