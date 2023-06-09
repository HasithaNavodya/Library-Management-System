package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.dto.UserDTO;

import java.sql.SQLException;

public interface LoginBO {

    public  boolean LoginAction(UserDTO logingCont) throws SQLException;
}
