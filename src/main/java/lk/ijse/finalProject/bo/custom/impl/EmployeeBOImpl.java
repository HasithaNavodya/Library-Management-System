package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.EmployeeBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.EmployeeDAO;
import lk.ijse.finalProject.model.EmployeeDTO;
import lk.ijse.finalProject.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException {
        ArrayList<Employee> allEmployee = employeeDAO.getAll();
        ArrayList<EmployeeDTO> arrayList = new ArrayList<>();
        for (Employee e : allEmployee) {
            arrayList.add(new EmployeeDTO(e.getEmployee_id(),e.getEmployee_name(),e.getStatus(),e.getAddress(),
                                          e.getContact_no())
            );
        }
        return arrayList;
    }

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployee_id(),
                                             employeeDTO.getEmployee_name(),
                                             employeeDTO.getStatus(),
                                             employeeDTO.getAddress(),
                                             employeeDTO.getContact_no()));
    }

    public boolean deleteEmployee(String employee_id) throws SQLException {
        return employeeDAO.delete(employee_id);
    }

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployee_id(),
                                               employeeDTO.getEmployee_name(),
                                               employeeDTO.getStatus(),
                                               employeeDTO.getAddress(),
                                               employeeDTO.getContact_no()));
    }

    public String getNextEmployeeId() throws SQLException {
        return employeeDAO.getNextId();
    }
}
