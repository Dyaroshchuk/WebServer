package model;

import java.util.Objects;

public class BuyCodeConfirmation {

    private String value;
    private String clientName;
    private Long productId;

    public BuyCodeConfirmation(String value, String clientName, Long productId) {
        this.value = value;
        this.clientName = clientName;
        this.productId = productId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyCodeConfirmation buyCodeConfirmation = (BuyCodeConfirmation) o;
        return Objects.equals(value, buyCodeConfirmation.value) &&
                Objects.equals(clientName, buyCodeConfirmation.clientName) &&
                Objects.equals(productId, buyCodeConfirmation.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, clientName, productId);
    }

    @Override
    public String toString() {
        return "BuyCodeConfirmation{" +
                "value='" + value + '\'' +
                ", clientName='" + clientName + '\'' +
                ", productId=" + productId +
                '}';
    }
}
