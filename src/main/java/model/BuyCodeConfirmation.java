package model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "buy_codes")
public class BuyCodeConfirmation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "clientName")
    private String clientName;

    @Column(name = "order_id")
    private Long orderId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    public BuyCodeConfirmation() {
    }

    public BuyCodeConfirmation(String value, String clientName, Long orderId) {
        this.value = value;
        this.clientName = clientName;
        this.orderId = orderId;
    }

    public BuyCodeConfirmation(String value, String clientName, Long orderId, Date creationDate) {
        this.value = value;
        this.clientName = clientName;
        this.orderId = orderId;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyCodeConfirmation that = (BuyCodeConfirmation) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(clientName, that.clientName) &&
                Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, clientName, orderId);
    }

    @Override
    public String toString() {
        return "BuyCodeConfirmation{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", clientName='" + clientName + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
