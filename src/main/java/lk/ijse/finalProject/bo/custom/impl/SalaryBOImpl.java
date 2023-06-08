package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.SalaryDAO;
import lk.ijse.finalProject.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.finalProject.dto.MemberDTO;
import lk.ijse.finalProject.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl {
    SalaryDAO salaryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    public ArrayList<SalaryDTO> getAllSalary() throws SQLException {
        return salaryDAO.getAll();
    }

    public boolean saveSalary(SalaryDTO salaryDTO) throws SQLException {
        return salaryDAO.save(salaryDTO);
    }

    public boolean deleteSalary(String salary_id) throws SQLException {
        return salaryDAO.delete(salary_id);
    }

    public boolean updateSalary(SalaryDTO salaryDTO) throws SQLException {
        return salaryDAO.update(salaryDTO);
    }

    public String getNextSalaryId() throws SQLException {
        return salaryDAO.getNextId();
    }
}
