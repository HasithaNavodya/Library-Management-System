package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.UserDAO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<UserDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM user";

        ArrayList<UserDTO> allUser = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allUser.add(new UserDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return allUser;
    }

    @Override
    public boolean save(UserDTO userDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO user (username, password, email) VALUES (?,?,?)", userDTO.getUserName(),userDTO.getPassword(),userDTO.getPassword());
    }

    @Override
    public boolean delete(String username) throws SQLException {
        return SQLUtil.execute("DELETE INTO user WHERE username = ? ", username);
    }

    @Override
    public boolean update(UserDTO logingCont) throws SQLException {
        ResultSet execute = SQLUtil.execute("SELECT * From user WHERE userName=? AND password =?", logingCont.getUserName(), logingCont.getPassword());
        if (execute.next()) {
            return true;
        } else {
            return false;
        }    }

    @Override
    public String getNextId() throws SQLException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

}
