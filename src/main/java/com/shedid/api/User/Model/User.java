package com.shedid.api.User.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shedid.api.Gender.Model.Gender;
import com.shedid.api.Role.Model.Role;
import com.shedid.api.User.Group.UsersValidation;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class User implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "serial")
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(max = 100)
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Email
    @Size(max = 50)
    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    @NotNull(groups = UsersValidation.UserValidation.class, message = "● Please provide user gender.")
    private Gender gender;

    @NotNull
    @Column(name = "password")
    @Length(groups = UsersValidation.UserPasswordValidation.class, min = 5, message = "● Your password must have at least 5 characters")
    @NotEmpty(groups = UsersValidation.UserPasswordValidation.class, message = "● Please provide your password")
    // @JsonIgnore
    private String password;

    @Valid
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserPhone> phones;

    @NotNull
    @Column(name = "enabled")
    private boolean enabled;

    @NotNull
    @Column(name = "trashed")
    private boolean trashed;

    @Size(max = 100)
    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @NotNull(groups = UsersValidation.UserValidation.class, message = "● Please provide user roles.")
    @ManyToMany()
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition="integer", name = "created_by", nullable = true)
    @JsonIgnore
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition="integer", name = "modified_by", nullable = true)
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

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Gender getGender()
    {
        return this.gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public List<UserPhone> getPhones()
    {
        return this.phones;
    }

    public void setPhones(List<UserPhone> phones)
    {
        this.phones = phones;
    }

    public boolean isEnabled()
    {
        return this.enabled;
    }

    public boolean getEnabled()
    {
        return this.enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isTrashed()
    {
        return this.trashed;
    }

    public boolean getTrashed()
    {
        return this.trashed;
    }

    public void setTrashed(boolean trashed)
    {
        this.trashed = trashed;
    }

    public String getImageUrl()
    {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public Set<Role> getRoles()
    {
        return this.roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
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