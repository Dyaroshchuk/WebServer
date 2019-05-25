package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private RoleType name;

    @OneToOne(mappedBy = "role")
    private Client client;

    public Role() {
    }

    public Role(RoleType name) {
        this.name = name;
    }

    public Role(RoleType name, Client client) {
        this.name = name;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                name == role.name &&
                Objects.equals(client, role.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, client);
    }

    public enum RoleType {
        ADMIN,
        USER
    }
}
