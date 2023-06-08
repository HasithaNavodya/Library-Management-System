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
}

