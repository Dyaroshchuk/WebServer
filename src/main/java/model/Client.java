package model;

import java.util.Objects;

public class Client {

    private String login;
    private String password;
    private Long role_id;
    private Role role;

    public Client(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Client(String login, String password, Long role_id) {
        this.login = login;
        this.password = password;
        this.role_id = role_id;
    }

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(role_id, client.role_id) &&
                Objects.equals(role, client.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role_id, role);
    }

    public enum Role {
        USER,
        ADMIN
    }
}
