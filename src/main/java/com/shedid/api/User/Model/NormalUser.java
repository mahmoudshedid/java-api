package com.shedid.api.User.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.*;

/**
 * NormalUser
 */
@Entity
@Table(name = "normal_user")
public class NormalUser implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",columnDefinition = "serial")
    private long id;

    @Column
    private String username;

    @Column
    // @JsonIgnore
    private String password;

    @Column
    private long salary;

    @Column
    private int age;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public long getSalary()
    {
        return salary;
    }

    public void setSalary(long salary)
    {
        this.salary = salary;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

}