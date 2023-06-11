package lk.ijse.finalProject.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DonatorDTO {
    private String donator_id;
    private String name;
    private String contact;
    private String date;
    private String username;
}
