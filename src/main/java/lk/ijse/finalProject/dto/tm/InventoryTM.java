package lk.ijse.finalProject.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class InventoryTM {
    private String item_id;
    private String item_name;
    private String catagory;
    private int quantity;
}
