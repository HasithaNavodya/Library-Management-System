package lk.ijse.finalProject.dao;

import lk.ijse.finalProject.bo.custom.impl.BookBOImpl;
import lk.ijse.finalProject.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.finalProject.dao.custom.FineDAO;
import lk.ijse.finalProject.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        BOOK, BORROW_BOOKS, DONATE_BOOKS, DONATOR,EMPLOYEE,FINE,INVENTORY,MEMBER,SALARY,QUERY_DAO,USER
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case BOOK:
                return (T) new BookDAOImpl();

            case BORROW_BOOKS:
                return (T) new BorrowBooksDAOImpl();

            case DONATE_BOOKS:
                return (T) new DonateBooksDAOImpl();

            case DONATOR:
                return (T) new DonatorDAOImpl();

            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();

            case FINE:
                return (T) new FineDAOImpl();

            case INVENTORY:
                return (T) new InventoryDAOImpl();

            case MEMBER:
                return (T) new MemberDAOImpl();

            case SALARY:
                return (T) new SalaryDAOImpl();

            case QUERY_DAO:
                return (T) new QueryDAOImpl();

            case USER:
                return (T) new UserDAOImpl();

            default:
                return null;
        }
    }

}
