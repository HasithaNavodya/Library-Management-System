package lk.ijse.finalProject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Inventory {
    private String item_id;
    private String item_name;
    private String catagory;
    private int quantity;
}
