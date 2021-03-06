
package edu.cth.dzenkyair.backend.core;

import edu.cth.dzenkyair.backend.util.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Dženan
 */
@Entity
@Table(name="USERS")
public class User extends AbstractEntity {
    
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "USERS_GROUPS", 
            joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Groups> groups = new ArrayList<>();
    
    public User() {
        ;
    }
    
    public User(String email, String password, Groups group) {
        this.email = email;
        this.password = password;
        groups.add(group);
    }
    public User(Long id, String email, String password, Groups group) {
        super(id);
        this.email = email;
        this.password = password;
        groups.add(group);
    }
    
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public List<Groups> getGroups() {
        return groups;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + getId() + ", password=" + password + ", email=" + email + '}';
    }
}
