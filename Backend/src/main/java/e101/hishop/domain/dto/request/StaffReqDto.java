package e101.hishop.domain.dto.request;

import e101.hishop.domain.entity.Product;
import e101.hishop.domain.entity.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class StaffReqDto {
    private Long branchId;
    private String name;
    private String position;
    private String part;
    private String staffLoginId;

    public Staff toStaffEntity(){
        return Staff.builder()
                .name(name)
                .position(position)
                .part(part)
                .staffLoginId(staffLoginId)
                .build();
    }
}
