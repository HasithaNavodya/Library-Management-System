package lk.ijse.finalProject.dao.custom.impl;

import lk.ijse.finalProject.dao.custom.BookDAO;
import lk.ijse.finalProject.dto.BookDTO;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    
    @Override
    public ArrayList<BookDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM books";

        ArrayList<BookDTO> allBooks = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute(sql);
        while (resultSet.next()) {
            allBooks.add(new BookDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return allBooks;
    }

    public List<String> loadBookIDs() throws SQLException {
        String sql = "SELECT book_id FROM books";
        ResultSet resultSet = SQLUtil.execute(sql);

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    @Override
    public List<String> getNextBookId() throws SQLException {
        return null;
    }

    @Override
    public boolean save(BookDTO bookDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO books (book_id,name,author,category,cupboard_no) VALUES (?,?,?,?,?)", bookDTO.getBook_id(),bookDTO.getName(),bookDTO.getAuthor(),bookDTO.getCategory(),bookDTO.getCupboard_no());
    }

    @Override
    public boolean delete(String book_id) throws SQLException {
        return SQLUtil.execute("DELETE FROM books WHERE book_id=?", book_id);
    }

    @Override
    public boolean update(BookDTO bookDTO) throws SQLException {
        return SQLUtil.execute("UPDATE books SET name=?, author=?, category=?, cupboard_no=? WHERE book_id=?", bookDTO.getName(), bookDTO.getAuthor(), bookDTO.getCategory(),bookDTO.getCupboard_no(),bookDTO.getBook_id());
    }

    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT book_id FROM books ORDER BY book_id DESC LIMIT 1;");
        if (rst.next()) {
            String book_id = rst.getString("book_id");
            int newCustomerId = Integer.parseInt(book_id.replace("BOOK-", "")) + 1;
            return String.format("BOOK-%03d", newCustomerId);
        } else {
            return "BOOK-001";
        }
    }
}
