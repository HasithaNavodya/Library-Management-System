package lk.ijse.finalProject.dto;

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
    private Double fee;
    private String contact;
}
