package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO  extends SuperBO {

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException;

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException;

    public boolean deleteEmployee(String employee_id) throws SQLException;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException;

    public String getNextEmployeeId() throws SQLException;
}
