package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.Employee;
import lk.ijse.finalProject.dto.tm.EmployeeTM;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee(employee_id,emplyee_name,status,address,contact_no) " +
                "VALUES(?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                employee.getEmployee_id(),
                employee.getEmplyee_name(),
                employee.getStatus(),
                employee.getAddress(),
                employee.getContact()
        );
    }


    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET emplyee_name=?,status=?,address=?,contact_no=? WHERE employee_id=?";

        return CrudUtil.execute(
                sql,
                employee.getEmplyee_name(),
                employee.getStatus(),
                employee.getAddress(),
                employee.getContact(),
                employee.getEmployee_id()
        );
    }

    public static ObservableList<EmployeeTM> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            obList.add(new EmployeeTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return obList;
    }

    public static boolean delete(String emp_id) throws SQLException {
        String sql = "DELETE FROM employee WHERE employee_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, id);
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,emp_id);
    }
}

