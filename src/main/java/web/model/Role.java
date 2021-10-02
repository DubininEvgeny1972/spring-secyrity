package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    @ManyToMany
    @JoinTable(name = "roles_users",
            //foreign key for CarsEntity in employee_car table
            joinColumns = @JoinColumn(name = "role_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "user_id"))

//    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    public Set<User> getUsers() {
        return users;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }



    @Override
    public String getAuthority() {
        return role;
    }
}
