package lk.ijse.finalProject.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class InventoryDTO {
    private String Item_id;
    private String Item_name;
    private String category;
    private int quantity;
}
