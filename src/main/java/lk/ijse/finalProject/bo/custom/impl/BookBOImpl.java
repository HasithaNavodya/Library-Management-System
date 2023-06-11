package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.BookBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.BookDAO;
import lk.ijse.finalProject.dao.custom.impl.BookDAOImpl;
import lk.ijse.finalProject.dao.custom.impl.util.SQLUtil;
import lk.ijse.finalProject.dto.BookDTO;
import lk.ijse.finalProject.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    BookDAO bookDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.BOOK);

    public ArrayList<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException {
        ArrayList<Book> allBooks = bookDAO.getAll();
        ArrayList<BookDTO> arrayList = new ArrayList<>();
        for (Book b : allBooks) {
            arrayList.add(new BookDTO(b.getBook_id(),
                                      b.getName(),
                                      b.getAuthor(),
                                      b.getCategory(),
                                      b.getCupboard_no()));
        }
        return arrayList;
    }

    public boolean saveBook(BookDTO bookDTO) throws SQLException {
       return bookDAO.save(new Book(bookDTO.getBook_id(),
                                    bookDTO.getName(),
                                    bookDTO.getAuthor(),
                                    bookDTO.getCategory(),
                                    bookDTO.getCupboard_no()));
    }

    public boolean deleteBook(String book_id) throws SQLException {
        return bookDAO.delete(book_id);
    }

    public boolean updateBook(BookDTO bookDTO) throws SQLException {
        return bookDAO.update(new Book(bookDTO.getBook_id(),
                                       bookDTO.getName(),
                                       bookDTO.getAuthor(),
                                       bookDTO.getCategory(),
                                       bookDTO.getCupboard_no()));
    }

    public String getNextBookId() throws SQLException {
        return bookDAO.getNextId();
    }

}
