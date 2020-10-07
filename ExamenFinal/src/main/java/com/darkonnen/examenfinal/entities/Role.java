package com.darkonnen.examenfinal.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    
    // RELACIONES
    
    // AGREGAR A WORKBENCH
    
//    INSERT INTO `posts`.`roles` ('id', 'name') VALUES (1, 'ROLE_USER');
//    INSERT INTO `posts`.`roles` ('id', 'name') VALUES (2, 'ROLE_ADMIN')
    
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    
    public Role() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}