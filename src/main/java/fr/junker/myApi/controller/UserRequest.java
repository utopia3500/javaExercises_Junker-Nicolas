package fr.junker.myApi.controller;

public class UserRequest {
    private String name;
    private Integer age;

    public UserRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserRequest [name=" + name + ", age=" + age + "]";
    }

    

    

    
    
}
