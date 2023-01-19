package e101.hishop.repository;

import e101.hishop.domain.dto.request.EditNameReqDto;
import e101.hishop.domain.entity.Payment;
import e101.hishop.domain.entity.Users;

import java.util.List;

public interface UserRepository {

    Users findUserById(Long id);

    Users findUserByLoginId(String userId);

    Users findUserByEmail(String email);

    Long saveCard(Payment payment);

    Boolean deleteCard(Long cardId);

    List<Payment> getCardInfo(Long userId);

    Users getUserInfo(Long userPK);

    Boolean editName(Long cardId, EditNameReqDto dto);
}
