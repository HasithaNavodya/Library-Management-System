package lk.ijse.finalProject.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class User {
    private String userName;
    private String password;
    private String email;
}