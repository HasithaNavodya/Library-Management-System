package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.SalaryDAO;
import lk.ijse.finalProject.dto.SalaryDTO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {

    public ArrayList<SalaryDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

        ArrayList<SalaryDTO> allSalary = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allSalary.add(new SalaryDTO(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getString(5)
            ));
        }
        return allSalary;
    }

    @Override
    public boolean save(SalaryDTO salaryDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO salary (salary_id,bonus,date,amount,employee_id) VALUES (?,?,?,?,?)", salaryDTO.getSalary_id(),salaryDTO.getBonus(),salaryDTO.getDate(),salaryDTO.getAmount(),salaryDTO.getEmployee_id());
    }

    @Override
    public boolean delete(String salary_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM salary WHERE salary_id=?", salary_id);
    }

    @Override
    public boolean update(SalaryDTO salaryDTO) throws SQLException {
        return SQLUtil.execute("UPDATE salary SET bonus=?, date=?, amount=?, employee_id=? WHERE salary_id=?", salaryDTO.getBonus(),salaryDTO.getDate(),salaryDTO.getAmount(),salaryDTO.getEmployee_id(),salaryDTO.getSalary_id());
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT salary_id FROM salary ORDER BY salary_id DESC LIMIT 1;");
        if (rst.next()) {
            String salary_id = rst.getString("salary_id");
            int newCustomerId = Integer.parseInt(salary_id.replace("SALARY-", "")) + 1;
            return String.format("SALARY-%03d", newCustomerId);
        } else {
            return "SALARY-001";
        }
    }
}
