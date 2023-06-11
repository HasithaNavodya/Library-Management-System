package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {

    public  boolean LoginAction(UserDTO userDTO) throws SQLException;
}
