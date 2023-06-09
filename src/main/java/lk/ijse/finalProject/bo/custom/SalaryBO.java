package lk.ijse.finalProject.bo.custom;

import lk.ijse.finalProject.bo.SuperBO;
import lk.ijse.finalProject.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {

    public ArrayList<SalaryDTO> getAllSalary() throws SQLException;

    public boolean saveSalary(SalaryDTO salaryDTO) throws SQLException;;

    public boolean deleteSalary(String salary_id) throws SQLException;

    public boolean updateSalary(SalaryDTO salaryDTO) throws SQLException;

    public String getNextSalaryId() throws SQLException;
}
