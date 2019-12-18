package com.shedid.api.User.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shedid.api.User.Group.UsersValidation;

import org.hibernate.validator.constraints.Length;

/**
 * UserPhone
 */
@Entity
@Table(name = "user_phones")
public class UserPhone
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "type")
    @Length(groups = UsersValidation.UserValidation.class, min = 2, message = "* Phone Type must have at least 2 characters.")
    @Length(groups = UsersValidation.UserValidation.class, max = 50, message = "* Phone Type must be less than 50 characters.")
    @NotNull(groups = UsersValidation.UserValidation.class, message = "● Please provide user phone type.")
    private String type;

    @Column(name = "area_code")
    @Length(groups = UsersValidation.UserValidation.class, min = 3, message = "* Phone area code must have minimum 3 digits.")
    @Length(groups = UsersValidation.UserValidation.class, max = 3, message = "* Phone area code must have maximum 3 digits.")
    @NotNull(groups = UsersValidation.UserValidation.class, message = "● Please provide user phone area code.")
    private String areaCode;

    @Column(name = "number")
    @Length(groups = UsersValidation.UserValidation.class, min = 8, message = "* Phone Number must have minimum 8 digits.")
    @Length(groups = UsersValidation.UserValidation.class, max = 8, message = "* Phone Number must have maximum 8 digits.")
    @NotNull(groups = UsersValidation.UserValidation.class, message = "● Please provide user phone number.")
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @NotNull()
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnore
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    @JsonIgnore
    private User modifiedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAreaCode()
    {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getNumber()
    {
        return this.number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public User getOwner()
    {
        return this.owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    public User getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy(User createdBy)
    {
        this.createdBy = createdBy;
    }

    public User getModifiedBy()
    {
        return this.modifiedBy;
    }

    public void setModifiedBy(User modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedAt()
    {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

}