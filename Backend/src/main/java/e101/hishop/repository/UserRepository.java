package e101.hishop.repository;

import e101.hishop.domain.dto.request.EditNameReqDto;
import e101.hishop.domain.entity.Card;

import java.util.List;

public interface UserRepository {

    Long saveCard(Card cards);
    Card findCardById(Long id);

    Boolean deleteCard(Long cardId);

    List<Card> getCardInfo(Long userId);

    Boolean editName(Long cardId, EditNameReqDto dto);
}
