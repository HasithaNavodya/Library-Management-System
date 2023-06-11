package lk.ijse.finalProject.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class MemberDTO {
    private String member_id;
    private String name;
    private String address;
    private String grade;
    private Double member_fee;
    private String contact_no;
}
