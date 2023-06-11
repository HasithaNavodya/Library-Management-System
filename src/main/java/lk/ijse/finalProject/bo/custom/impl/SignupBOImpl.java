package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.SignupBO;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dao.custom.impl.UserDAOImpl;
import lk.ijse.finalProject.model.UserDTO;
import lk.ijse.finalProject.entity.User;

import java.sql.SQLException;

public class SignupBOImpl implements SignupBO {
    UserDAO userDAO = new UserDAOImpl();

    public boolean saveUser(UserDTO userDTO) throws SQLException{
        return userDAO.save(new User(userDTO.getUserName(),
                                     userDTO.getPassword(),
                                     userDTO.getEmail()));
    }
}
