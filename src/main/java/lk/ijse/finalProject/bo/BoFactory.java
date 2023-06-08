package lk.ijse.finalProject.bo;


import lk.ijse.finalProject.bo.custom.impl.*;
import lk.ijse.finalProject.dto.DonateBooksDTO;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        BOOK_BO,BORROW_BOOKS_BO,DONATE_BOOKS_BO,DONATOR_BO,EMPLOYEE_BO,FINE_BO,INVENTORY_BO,MEMBER_BO,SALARY_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case BOOK_BO:
                return (T) new BookBOImpl();

            case BORROW_BOOKS_BO:
                return (T) new BorrowBooksBOImpl();

            case DONATE_BOOKS_BO:
                return (T) new DonateBooksBOImpl();

            case DONATOR_BO:
                return (T) new DonatorBOImpl();

            case EMPLOYEE_BO:
                return (T) new EmployeeBOImpl();

            case FINE_BO:
                return (T) new FineBOImpl();

            case INVENTORY_BO:
                return (T) new InventoryBOImpl();

            case MEMBER_BO:
                return (T) new MemberBOImpl();

            case SALARY_BO:
                return (T) new SalaryBOImpl();

            default:
                return null;
        }
    }


}
