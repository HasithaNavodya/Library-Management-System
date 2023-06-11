package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.DonateBooksBO;
import lk.ijse.finalProject.dao.custom.BookDAO;
import lk.ijse.finalProject.dao.custom.DonateBooksDAO;
import lk.ijse.finalProject.dao.custom.DonatorDAO;
import lk.ijse.finalProject.dao.custom.impl.BookDAOImpl;
import lk.ijse.finalProject.dao.custom.impl.DonateBooksDAOImpl;
import lk.ijse.finalProject.dao.custom.impl.DonatorDAOImpl;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.model.DonateBooksDTO;
import lk.ijse.finalProject.entity.Book;
import lk.ijse.finalProject.entity.DonateBooks;
import lk.ijse.finalProject.model.BookModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DonateBooksBOImpl implements DonateBooksBO {


    DonateBooksDAO donateBooksDAO = new DonateBooksDAOImpl();
    BookDAO bookDAO = new BookDAOImpl();
    DonatorDAO donatorDAO = new DonatorDAOImpl();


    public String getNextDonationId() throws SQLException {
        return donateBooksDAO.getNextId();
    }

    public String getNextBookId() throws SQLException {
        return bookDAO.getNextId();

    }

    public  List<String> loadDonatorIds() throws SQLException {
        return donatorDAO.loadDonatorIds();
    }

    public  boolean placeDonation(DonateBooksDTO dto) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

//            boolean isDonationSaved = saveDonation(donation_id,donator_id,donateBooksList);
//            if (isDonationSaved) {
//                System.out.println(isDonationSaved);
//                boolean isBookSaved = BookModel.saveBooks(donateBooksList);
//                if (isBookSaved) {
//                    System.out.println(isBookSaved);
//                    con.commit();
//                    return true;
//                }
//            }
//            return false;

            for (DonateBooksDTO d : dto.getDonateBooksList()) {
                boolean isDonationSaved = donateBooksDAO.save(new DonateBooks(dto.getDonation_id(),
                                                                              dto.getDonator_id(), d.getBook_id()));
                bookDAO.save(new Book(d.getBook_id(),d.getName(),d.getAuthor(),d.getCategory(),d.getCup_no()));
            }
            con.commit();
            return true;
        } catch (SQLException er) {
            System.out.println(er);
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }
}


