package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.DonatorDTO;
import lk.ijse.finalProject.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO  extends SuperBO {

    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    String generateNewEmployeeID() throws SQLException, ClassNotFoundException;
}
