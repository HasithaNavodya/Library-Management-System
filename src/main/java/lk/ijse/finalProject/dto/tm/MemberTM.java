package lk.ijse.finalProject.dto.tm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberTM {
   private String member_id ;
   private String name ;
   private String address;
   private String grade ;
   private Double fee;
   private String contact;
}
