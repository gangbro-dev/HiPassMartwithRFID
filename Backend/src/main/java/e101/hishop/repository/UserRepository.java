package e101.hishop.repository;

import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;

public interface UserRepository {

    Users findUserById(Long id);

    Users findUserByLoginId(String userId);

    Users findUserByEmail(String email);

    Long saveCard(Payment payment);

    Users getUserInfo(Long userPK);
}
