package lk.ijse.finalProject.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Member {
    private String member_id ;
    private String name ;
    private String address;
    private String grade ;
    private Double member_fee;
    private String contact_no;
}
