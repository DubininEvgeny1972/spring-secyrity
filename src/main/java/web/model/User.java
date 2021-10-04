package web.model;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    @Column
    private String password;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();

//        public User(String login, String name, String lastName, String password, Byte age) {
//        this.login = login;
//        this.name = name;
//        this.lastName = lastName;
//        this.password = password;
//        this.age = age;
////        this.roles = roles;
//    }

    public User() {
    }

    public String toString(){
        return "Name: " + getName() + "   Last Name: " + getLastName() + "  Age: " + getAge() + "   Login: " + getLogin() + "   Password: " + getPassword() + "   Roles: " + getRoles().toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Byte getAge() {
        return age;
    }
    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
