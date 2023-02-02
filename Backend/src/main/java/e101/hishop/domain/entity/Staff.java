package e101.hishop.domain.entity;

import e101.hishop.domain.dto.request.StaffReqDto;
import e101.hishop.domain.dto.request.UserInfoReqDto;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Staff {

    @Id
    @GeneratedValue
    @Column(name = "staff_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String position;

    private String part;

    private String staffLoginId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public void setBranchAndStaff(Branch branch) {
        this.branch = branch;
        branch.getStaff().add(this);
    }

    public Staff updateStaff(StaffReqDto dto) {
        name = StringUtils.hasText(dto.getName()) ? dto.getName() : name;
        position = StringUtils.hasText(dto.getPosition()) ? dto.getPosition() : position;
        part = StringUtils.hasText(dto.getPart()) ? dto.getPart() : part;
        staffLoginId = StringUtils.hasText(dto.getStaffLoginId()) ? dto.getStaffLoginId() : staffLoginId;
        return this;
    }

}
