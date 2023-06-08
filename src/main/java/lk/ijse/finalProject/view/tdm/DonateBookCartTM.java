package lk.ijse.finalProject.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DonateBookCartTM {
    private String id;
    private String name;
    private String author;
    private String category;
    private int cup_no;
    private Button removeBtn;
}
