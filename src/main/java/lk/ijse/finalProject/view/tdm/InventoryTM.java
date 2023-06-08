package lk.ijse.finalProject.view.tdm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class InventoryTM {
    private String item_id;
    private String item_name;
    private String category;
    private int quantity;
}
