package lk.ijse.finalProject.bo;

import lk.ijse.finalProject.bo.custom.impl.BookBOImpl;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        BOOK_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case BOOK_BO:
                return (T) new BookBOImpl();
        }
           /* case ITEM_BO:
                return (T) new ItemBOImpl();
            case PURCHASE_ORDER_BO:
                return (T) new PurchaseOrderBOImpl();
            default:
                return null;*/

        return null;
    }
}
