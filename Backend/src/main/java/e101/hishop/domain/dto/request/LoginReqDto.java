package e101.hishop.domain.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class LoginReqDto {
    private String userId;
    private String password;
}
