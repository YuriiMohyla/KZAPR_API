package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue
    private Long profile_id;

    private String name;
    private String surname;
    private String avatar;
    private Date birthday;

    @OneToMany(mappedBy = "customer")
    private List<Contract> customerContracts;

    @OneToMany(mappedBy = "owner")
    private List<Contract> ownersContracts;

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                '}';
    }
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "profile_roles",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> roles;

    @OneToMany(mappedBy = "profile")
    private List<Comment> comments;
}
