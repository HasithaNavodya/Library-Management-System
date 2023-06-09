package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.SignupBO;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dao.custom.impl.UserDAOImpl;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.SQLException;

public class SignupBOImpl implements SignupBO {
    UserDAO userDAO = new UserDAOImpl();

    public boolean saveUser(UserDTO userDTO) throws SQLException{
        return userDAO.save(userDTO);
    }
}
