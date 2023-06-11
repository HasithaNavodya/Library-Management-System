package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.SalaryBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.SalaryDAO;
import lk.ijse.finalProject.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.finalProject.dto.InventoryDTO;
import lk.ijse.finalProject.dto.MemberDTO;
import lk.ijse.finalProject.dto.SalaryDTO;
import lk.ijse.finalProject.entity.Inventory;
import lk.ijse.finalProject.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    public ArrayList<SalaryDTO> getAllSalary() throws SQLException {
        ArrayList<Salary> allItem = salaryDAO.getAll();
        ArrayList<SalaryDTO> arrayList = new ArrayList<>();
        for (Salary s : allItem) {
            arrayList.add(new SalaryDTO(s.getSalary_id(),
                                        s.getBonus(),
                                        s.getDate(),
                                        s.getAmount(),
                                        s.getEmployee_id()));
        }
        return arrayList;    }

    public boolean saveSalary(SalaryDTO salaryDTO) throws SQLException {
        return salaryDAO.save(new Salary(salaryDTO.getSalary_id(),
                                         salaryDTO.getBonus(),
                                         salaryDTO.getDate(),
                                         salaryDTO.getAmount(),
                                         salaryDTO.getEmployee_id()));
    }

    public boolean deleteSalary(String salary_id) throws SQLException {
        return salaryDAO.delete(salary_id);
    }

    public boolean updateSalary(SalaryDTO salaryDTO) throws SQLException {
        return salaryDAO.update(new Salary(salaryDTO.getSalary_id(),
                                           salaryDTO.getBonus(),
                                           salaryDTO.getDate(),
                                           salaryDTO.getAmount(),
                                           salaryDTO.getEmployee_id()));
    }

    public String getNextSalaryId() throws SQLException {
        return salaryDAO.getNextId();
    }
}
