package lk.ijse.finalProject.model;

import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.User;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {


    public static boolean SingUp(User user) throws SQLException {
        String sql = "INSERT INTO user(userName,password,email)" +
                "VALUES (?,?,?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, user.getUserName());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getEmail());


            int affectedRows = pstm.executeUpdate();
            return affectedRows > 0;
        }

    }

    public static boolean LogingAction(User logingCont) throws SQLException {
        ResultSet execute = CrudUtil.execute("SELECT * From user WHERE userName=? AND password =?", logingCont.getUserName(), logingCont.getPassword());
        if (execute.next()) {
            return true;
        } else {
            return false;
        }
    }

}
