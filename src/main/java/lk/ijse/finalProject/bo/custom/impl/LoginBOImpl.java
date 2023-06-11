package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.LoginBO;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dao.custom.impl.UserDAOImpl;
import lk.ijse.finalProject.model.UserDTO;
import lk.ijse.finalProject.entity.User;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = new UserDAOImpl();


    public  boolean LoginAction(UserDTO logingCont) throws SQLException{
        return userDAO.update(new User(logingCont.getUserName(),
                                       logingCont.getPassword(),
                                       logingCont.getEmail()));
    }

}
