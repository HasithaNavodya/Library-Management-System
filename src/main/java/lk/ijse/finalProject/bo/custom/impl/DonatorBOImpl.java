package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.DonatorDAO;
import lk.ijse.finalProject.dto.DonatorDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonatorBOImpl {
    DonatorDAO donatorDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DONATOR);

    public ArrayList<DonatorDTO> getAllDonator() throws SQLException {
        return donatorDAO.getAll();
    }

    public boolean save(DonatorDTO donatorDTO) throws SQLException {
        return donatorDAO.save(donatorDTO);
    }

    public boolean delete(String donator_id) throws SQLException {
        return donatorDAO.delete(donator_id);
    }


    public boolean update(DonatorDTO donatorDTO) throws SQLException {
        return donatorDAO.update(donatorDTO);
    }


    public String getNextDonatorId() throws SQLException {
        return donatorDAO.getNextId();
    }
}
