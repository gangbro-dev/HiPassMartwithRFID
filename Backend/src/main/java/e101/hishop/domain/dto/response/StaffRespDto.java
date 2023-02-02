package e101.hishop.domain.dto.response;

import e101.hishop.domain.entity.Pay;
import e101.hishop.domain.entity.Staff;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StaffRespDto {

    private Long id;

    private String name;

    private String position;

    private String part;

    private String staffLoginId;

    @Builder
    public StaffRespDto(Long id, String name, String position, String part, String staffLoginId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.part = part;
        this.staffLoginId = staffLoginId;
    }

    public static StaffRespDto of(Staff staff) {
        return StaffRespDto.builder()
                .id(staff.getId())
                .name(staff.getName())
                .position(staff.getPosition())
                .part(staff.getPart())
                .staffLoginId(staff.getStaffLoginId())
                .build();
    }
}
