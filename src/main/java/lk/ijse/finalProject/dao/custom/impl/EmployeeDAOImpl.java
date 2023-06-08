package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.EmployeeDAO;
import lk.ijse.finalProject.dto.EmployeeDTO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<EmployeeDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";

        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allEmployee.add(new EmployeeDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return allEmployee;
    }

    public boolean save(EmployeeDTO employeeDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee (employee_id,employee_name,status,address,contact_no) VALUES (?,?,?,?,?)", employeeDTO.getEmployee_id(),employeeDTO.getEmployee_name(),employeeDTO.getStatus(),employeeDTO.getAddress(),employeeDTO.getContact_no());

    }

    public boolean delete(String employee_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE employee_id=?", employee_id);
    }


    public boolean update(EmployeeDTO employee) throws SQLException {
        //return SQLUtil.execute("UPDATE employee SET employee_name=?,status=?,address=?,contact_no=? WHERE employee_id=? (?,?,?,?,?)", employeeDTO.getEmployee_name(),employeeDTO.getStatus(),employeeDTO.getAddress(),employeeDTO.getContact_no(),employeeDTO.getEmployee_id());


        return SQLUtil.execute("UPDATE employee SET employee_name=?,status=?,address=?,contact_no=? WHERE employee_id=?", employee.getEmployee_name(), employee.getStatus(), employee.getAddress(), employee.getContact_no(), employee.getEmployee_id());
    }

    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT employee_id FROM employee ORDER BY employee_id DESC LIMIT 1;");
        if (rst.next()) {
            String employee_id = rst.getString("employee_id");
            int newCustomerId = Integer.parseInt(employee_id.replace("EMPLOYEE-", "")) + 1;
            return String.format("EMPLOYEE-%03d", newCustomerId);
        } else {
            return "EMPLOYEE-001";
        }
    }
}
