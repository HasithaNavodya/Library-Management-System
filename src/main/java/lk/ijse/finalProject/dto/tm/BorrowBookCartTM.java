package lk.ijse.finalProject.dto.tm;

import javafx.scene.control.Button;
import lombok.*;
import org.apache.commons.digester.annotations.rules.SetRoot;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BorrowBookCartTM {
    private String issue_id;
    private String member_id;
    private String book_id;
    private String due_date;
    private Button removeBtn;
}
