package e101.hishop.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class RfidReqDto {
    @NotNull
    private Long kioskId;

    @NotBlank
    private List<String> rfid;

}
