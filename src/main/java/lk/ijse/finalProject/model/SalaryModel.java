package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.Salary;
import lk.ijse.finalProject.dto.tm.BookTM;
import lk.ijse.finalProject.dto.tm.SalaryTM;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {

    public static boolean save(Salary salary) throws SQLException {
        String sql = "INSERT INTO salary(salary_id,bonus,date,amount) " +
                "VALUES(?,?,?,?)";

        return CrudUtil.execute(
                sql,
                salary.getSalary_id(),
                salary.getBonus(),
                salary.getDate(),
                salary.getAmount()
        );
    }

    public static boolean delete(String salary_id) throws SQLException {
        String sql = "DELETE FROM salary WHERE salary_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, id);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,salary_id);
    }

    public static boolean update(Salary salary) throws SQLException {
        String sql = "UPDATE salary SET bonus=?,date=?,amount=? WHERE salary_id=?";

        return CrudUtil.execute(
                sql,
                salary.getBonus(),
                salary.getDate(),
                salary.getAmount(),
                salary.getSalary_id()
        );
    }


    public static ObservableList<SalaryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

        ObservableList<SalaryTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            obList.add(new SalaryTM(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return obList;
    }
}
