package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.FineDAO;
import lk.ijse.finalProject.dto.FineDTO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.entity.Fine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FineDAOImpl implements FineDAO {

    @Override
    public ArrayList<Fine> getAll() throws SQLException {
        String sql = "SELECT * FROM fines";

        ArrayList<Fine> allFine = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allFine.add(new Fine(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return allFine;
    }

    @Override
    public boolean save(Fine fine) throws SQLException {
        //return SQLUtil.execute("INSERT INTO fines (fine_id,amount,date,description,member_id) VALUES (?,?,?,?,?)", fineDTO.getFine_id(),fineDTO.getAmount(),fineDTO.getDate(),fineDTO.getDescription(),fineDTO.getMember_id());
        String sql ="INSERT  INTO fines Values(?,?,?,?,?)";
        return SQLUtil.execute(sql,
                fine.getFine_id(),
                fine.getAmount(),
                fine.getDate(),
                fine.getDescription(),
                fine.getMember_id());

    }

    @Override
    public boolean delete(String fine_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM fines WHERE fine_id=?", fine_id);
    }

    @Override
    public boolean update(Fine fine) throws SQLException {
        return SQLUtil.execute("UPDATE fines SET amount=?, date=?, description=?, member_id=? WHERE fine_id=?", fine.getAmount(), fine.getDate(), fine.getDescription(), fine.getMember_id(),fine.getFine_id());
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT fine_id FROM fines ORDER BY fine_id DESC LIMIT 1;");
        if (rst.next()) {
            String fine_id = rst.getString("fine_id");
            int newCustomerId = Integer.parseInt(fine_id.replace("FINE-", "")) + 1;
            return String.format("FINE-%03d", newCustomerId);
        } else {
            return "FINE-001";
        }
    }
}
