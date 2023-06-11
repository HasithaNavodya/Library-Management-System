package lk.ijse.finalProject.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Inventory {
    private String Item_id;
    private String Item_name;
    private String category;
    private int quantity;

    public Inventory(String item_name, String category, int quantity, String item_id) {
        this.Item_name = item_name;
        this.category = category;
        this.quantity = quantity;
        this.Item_id = item_id;
    }
}

