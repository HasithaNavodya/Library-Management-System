package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.model.UserDTO;

import java.sql.SQLException;

public interface SignupBO {
    public boolean saveUser(UserDTO userDTO) throws SQLException;
}
