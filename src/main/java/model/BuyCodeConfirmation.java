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

    @Column(name = "product_id")
    private Long productId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    public BuyCodeConfirmation() {
    }

    public BuyCodeConfirmation(String value, String clientName, Long productId) {
        this.value = value;
        this.clientName = clientName;
        this.productId = productId;
    }

    public BuyCodeConfirmation(String value, String clientName, Long productId, Date creationDate) {
        this.value = value;
        this.clientName = clientName;
        this.productId = productId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, clientName, productId);
    }

    @Override
    public String toString() {
        return "BuyCodeConfirmation{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", clientName='" + clientName + '\'' +
                ", productId=" + productId +
                '}';
    }
}
