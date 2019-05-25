package dao;

import model.BuyCodeConfirmation;

import java.util.Optional;

public interface CodeDao extends GenericDao<BuyCodeConfirmation> {

    Optional<BuyCodeConfirmation> getCodeByOrderId(Long orderId);
}
