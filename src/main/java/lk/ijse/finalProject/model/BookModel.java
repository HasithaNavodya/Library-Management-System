package lk.ijse.finalProject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.finalProject.dto.BookDTO;
import lk.ijse.finalProject.dto.BorrowBooksDTO;
import lk.ijse.finalProject.dto.DonateBooksDTO;
import lk.ijse.finalProject.view.tdm.BookTM;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel {

    public static ObservableList<BookTM> getAll() throws SQLException {
        String sql = "SELECT * FROM books";

        ObservableList<BookTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            obList.add(new BookTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return obList;
    }
    public static boolean save(BookDTO book) throws SQLException {
        String sql = "INSERT INTO books(book_id,name,author,category,cupboard_no) " +
                "VALUES(?,?,?,?,?)";

        return SQLUtil.execute(
                sql,
                book.getBook_id(),
                book.getName(),
                book.getAuthor(),
                book.getCategory(),
                book.getCupboard_no()
        );
    }

    public static boolean delete(String book_id) throws SQLException {
        String sql = "DELETE FROM books WHERE book_id=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//            pstm.setString(1, id);
//
//            return pstm.executeUpdate() > 0;
//        }
        return SQLUtil.execute(sql,book_id);
    }

    public static boolean update(BookDTO book) throws SQLException {
        String sql = "UPDATE books SET name=?,author=?,category=?,cupboard_no=? WHERE book_id=?";

        return SQLUtil.execute(
                sql,
                book.getName(),
                book.getAuthor(),
                book.getCategory(),
                book.getCupboard_no(),
                book.getBook_id()
        );
    }



    public static String getNextBookId() throws SQLException {
        String sql = "SELECT book_id FROM books ORDER BY book_id DESC LIMIT 1";

        ResultSet resultSet = SQLUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if (currentId != null) {
            String[] strings = currentId.split("BOOK-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit = String.format("%03d", id);
            return "BOOK-" + digit;
        }
        return "BOOK-001";
    }

    public static boolean saveBooks(List<DonateBooksDTO> donateBooksList) throws SQLException {
        for(DonateBooksDTO donateBooksDTO : donateBooksList) {
            if(!save(donateBooksDTO)) {
                return false;
            }
        }
        return true;
    }


    private static boolean save(DonateBooksDTO donateBooks) throws SQLException {
        String sql = "INSERT INTO books(book_id,name,author,category,cupboard_no)" +
                "VALUES(?, ?, ?, ?, ?)";

        return SQLUtil.execute(
                sql,
                donateBooks.getDonation_id(),
                donateBooks.getDonator_id(),
                donateBooks.getBook_id()
        );
    }

    public static List<String> loadBookIDs() throws SQLException {
        String sql = "SELECT book_id FROM books";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static boolean deleteBooks(List<BorrowBooksDTO> borrowBooksList) throws SQLException {
        for(BorrowBooksDTO borrowBooksDTO : borrowBooksList) {
            if(!deleteBooks(borrowBooksDTO)) {
                return false;
            }
        }
        return true;
    }

    private static boolean deleteBooks(BorrowBooksDTO borrowBooks) throws SQLException {
        String sql = "DELETE FROM books WHERE book_id=?";


        return SQLUtil.execute(
                sql,
                borrowBooks.getBook_id()
        );
    }


//    public static List<Book> getAll() throws SQLException {
//         Connection con = DBConnection.getInstance().getConnection();
//        String sql = "SELECT * FROM Books";
//
//        List<Book> data = new ArrayList<>();
//
//        ResultSet resultSet = con.createStatement().executeQuery(sql);
//        while (resultSet.next()) {
//            data.add(new Book(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getInt(5)
//            ));
//        }
//        return data;
//    }
//
//    public static List<String> loadIds() throws SQLException {
//        Connection con = DBConnection.getInstance().getConnection();
//        ResultSet resultSet = con.createStatement().executeQuery("SELECT book_id FROM books");
//
//        List<String> data = new ArrayList<>();
//
//        while (resultSet.next()) {
//            data.add(resultSet.getString(1));
//        }
//        return data;
//    }
//
//    public static Book searchById(String book_id) throws SQLException {
//
//        Connection con = DBConnection.getInstance().getConnection();
//
//        PreparedStatement pstm = con.prepareStatement("SELECT * FROM books WHERE book_id = ?");
//        pstm.setString(1, book_id);
//
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()) {
//            return new Book(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getInt(5)
//            );
//        }
//        return null;
//    }
}



