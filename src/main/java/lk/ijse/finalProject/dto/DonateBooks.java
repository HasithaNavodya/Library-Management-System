package lk.ijse.finalProject.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DonateBooks {
    private String id;
    private String name;
    private String author;
    private String category;
    private String cupNo;
}
