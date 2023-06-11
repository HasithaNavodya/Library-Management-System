package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.DonateBooksDAO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.entity.DonateBooks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonateBooksDAOImpl implements DonateBooksDAO {
    @Override
    public ArrayList<DonateBooks> getAll() throws SQLException {
        String sql = "SELECT * FROM donate_books";

        ArrayList<DonateBooks> allDonateBook = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allDonateBook.add(new DonateBooks(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return allDonateBook;
    }

    @Override
    public boolean save(DonateBooks donateBooks) throws SQLException {
        return SQLUtil.execute("INSERT INTO book_donation(donation_id,donator_id,book_id) VALUES (?, ?, ?)", donateBooks.getDonation_id(), donateBooks.getDonator_id(), donateBooks.getBook_id());
    }

    @Override
    public boolean delete(String donation_id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(DonateBooks donateBooks) throws SQLException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT donation_id FROM book_donation ORDER BY donation_id DESC LIMIT 1");
        if (rst.next()) {
            String donation_id = rst.getString("donation_id");
            int newCustomerId = Integer.parseInt(donation_id.replace("D-", "")) + 1;
            return String.format("D-%03d", newCustomerId);
        } else {
            return "D-001";
        }

    }
}
