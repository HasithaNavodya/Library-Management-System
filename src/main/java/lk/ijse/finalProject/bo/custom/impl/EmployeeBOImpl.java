package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.EmployeeBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.EmployeeDAO;
import lk.ijse.finalProject.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.finalProject.dto.EmployeeDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException {
        return employeeDAO.getAll();
    }

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.save(employeeDTO);
    }

    public boolean deleteEmployee(String employee_id) throws SQLException {
        return employeeDAO.delete(employee_id);
    }

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.update(employeeDTO);
    }

    public String getNextEmployeeId() throws SQLException {
        return employeeDAO.getNextId();
    }
}
