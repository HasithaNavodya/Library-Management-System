package lk.ijse.finalProject.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class UserDTO {
    private String userName;
    private String password;
    private String email;


    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
