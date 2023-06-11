package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.DonatorDAO;
import lk.ijse.finalProject.dto.DonatorDTO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.entity.Donator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonatorDAOImpl implements DonatorDAO {

    @Override
    public ArrayList<Donator> getAll() throws SQLException {
        String sql = "SELECT * FROM donators";

        ArrayList<Donator> allDonator = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allDonator.add(new Donator(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return allDonator;
    }

    @Override
    public boolean save(Donator donator) throws SQLException {
        return SQLUtil.execute("INSERT INTO donators (donator_id,name,contact,date,username) VALUES (?,?,?,?,?)", donator.getDonator_id(),donator.getName(),donator.getContact(),donator.getDate(),donator.getUsername());
    }

    @Override
    public boolean delete(String donator_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM donators WHERE donator_id=?", donator_id);
    }

    @Override
    public boolean update(Donator donator) throws SQLException {
        return SQLUtil.execute("UPDATE donators SET name=?, contact=?, date=?, username=? WHERE donator_id=?", donator.getName(), donator.getContact(), donator.getDate(),donator.getUsername(),donator.getDonator_id());
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT donator_id FROM donators ORDER BY donator_id DESC LIMIT 1;");
        if (rst.next()) {
            String donator_id = rst.getString("donator_id");
            int newCustomerId = Integer.parseInt(donator_id.replace("DONATOR-", "")) + 1;
            return String.format("DONATOR-%03d", newCustomerId);
        } else {
            return "DONATOR-001";
        }
    }

    @Override
    public List<String> loadDonatorIds() throws SQLException {
        String sql = "SELECT donator_id FROM donators";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
