package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.FineBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.FineDAO;
import lk.ijse.finalProject.dao.custom.impl.FineDAOImpl;
import lk.ijse.finalProject.dto.FineDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineBOImpl implements FineBO {
    FineDAO fineDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.FINE);


    public ArrayList<FineDTO> getAllFine() throws SQLException {
        return fineDAO.getAll();
    }

    public boolean saveFine(FineDTO fineDTO) throws SQLException {
        return fineDAO.save(fineDTO);
    }

    public boolean deleteFineId(String fine_id) throws SQLException {
        return fineDAO.delete(fine_id);
    }

    public boolean updateFine(FineDTO fineDTO) throws SQLException {
        return fineDAO.update(fineDTO);
    }

    public String getNextFineId() throws SQLException {
        return fineDAO.getNextId();
    }
}
