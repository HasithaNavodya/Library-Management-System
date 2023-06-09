package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.DonatorBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.DonatorDAO;
import lk.ijse.finalProject.dto.DonatorDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonatorBOImpl implements DonatorBO {
    DonatorDAO donatorDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DONATOR);

    public ArrayList<DonatorDTO> getAllDonator() throws SQLException {
        return donatorDAO.getAll();
    }

    public boolean saveDonator(DonatorDTO donatorDTO) throws SQLException {
        return donatorDAO.save(donatorDTO);
    }

    public boolean deleteDonator(String donator_id) throws SQLException {
        return donatorDAO.delete(donator_id);
    }

    public boolean updateDonator(DonatorDTO donatorDTO) throws SQLException {
        return donatorDAO.update(donatorDTO);
    }

    public String getNextDonatorId() throws SQLException {
        return donatorDAO.getNextId();
    }
}
