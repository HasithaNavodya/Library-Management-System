package lk.ijse.finalProject.model;

import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.DonateBooks;
import lk.ijse.finalProject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DonateBooksModel {

    public static String getNextDonationId() throws SQLException {
        String sql = "SELECT donation_id FROM book_donation ORDER BY donation_id DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("DNTN-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "DNTN-" + digit;
        }
        return "DNTN-001";
    }

    public static boolean placeDonation(String donation_id, String donator_id, List<DonateBooks> donateBooksList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isDonationSaved = saveDonation(donation_id,donator_id,donateBooksList);
            if (isDonationSaved) {
                System.out.println(isDonationSaved);
                boolean isBookSaved = BookModel.saveBooks(donateBooksList);
                if (isBookSaved) {
                    System.out.println(isBookSaved);
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private static boolean saveDonation(String donation_id, String donator_id, List<DonateBooks> donateBooksList) throws SQLException {
        for(DonateBooks donateBooks : donateBooksList) {
            if(!save(donation_id,donator_id, donateBooks)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String donation_id, String donator_id, DonateBooks donateBooks) throws SQLException {
        String sql = "INSERT INTO book_donation(donation_id,donater_id,book_id)" +
                "VALUES(?, ?, ?)";

        return CrudUtil.execute(
                sql,
                donation_id,
                donator_id,
                donateBooks.getId()
        );
    }
}
