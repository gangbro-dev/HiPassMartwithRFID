package e101.hishop.global.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남"),
    FEMALE("여");

    public String kor;
}
