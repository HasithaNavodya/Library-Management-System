package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.LoginBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dao.custom.impl.UserDAOImpl;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = new UserDAOImpl();


    public  boolean LoginAction(UserDTO logingCont) throws SQLException{
        return userDAO.update(logingCont);
    }

}
