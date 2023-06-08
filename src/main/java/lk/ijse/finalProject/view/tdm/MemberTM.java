package lk.ijse.finalProject.view.tdm;
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
   private Double member_fee;
   private String contact_no;
}
