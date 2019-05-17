package model;

import utils.HashPassword;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "role")
    private String role;

    @Column(name = "salt")
    private String salt;

    public Client() {
    }

    public Client(Long id, String login, String password, String email, String role, String salt) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.salt = salt;
    }

    public Client(String login, String password, String email, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.salt = HashPassword.getSalt();
    }

    public Client(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(email, client.email) &&
                Objects.equals(role, client.role) &&
                Objects.equals(salt, client.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, role, salt);
    }
}
