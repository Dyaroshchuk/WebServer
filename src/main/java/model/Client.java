package model;

import utils.HashPassword;

import java.util.Objects;

public class Client {

    private String login;
    private String password;
    private String email;
    private Long role_id;
    private Role role;
    private String salt;

    public Client(String login, String password, String email, Role role, String salt) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.salt = salt;
    }

    public Client(String login, String password, String email, Long role_id) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role_id = role_id;
        this.salt = HashPassword.getSalt();
    }

    public Client(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Client() {
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

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(email, client.email) &&
                Objects.equals(role_id, client.role_id) &&
                role.equals(client.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, role_id, role);
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role_id=" + role_id +
                ", role=" + role +
                '}';
    }

    public enum Role {
        USER,
        ADMIN
    }
}
