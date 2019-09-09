package org.tyaa.listviewdemo;

import java.util.Date;

public class User {
    public Long id;
    public String name;
    public String email;
    public Date birthday;

    public User(Long id, String name, String email, Date birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
