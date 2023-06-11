package lk.ijse.finalProject.bo.custom.impl;

import lk.ijse.finalProject.bo.custom.DonatorBO;
import lk.ijse.finalProject.dao.DAOFactory;
import lk.ijse.finalProject.dao.custom.DonatorDAO;
import lk.ijse.finalProject.dto.BookDTO;
import lk.ijse.finalProject.dto.DonatorDTO;
import lk.ijse.finalProject.entity.Book;
import lk.ijse.finalProject.entity.Donator;

import java.sql.SQLException;
import java.util.ArrayList;

public class DonatorBOImpl implements DonatorBO {
    DonatorDAO donatorDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DONATOR);

    public ArrayList<DonatorDTO> getAllDonator() throws SQLException {
        ArrayList<Donator> allDonator = donatorDAO.getAll();
        ArrayList<DonatorDTO> arrayList = new ArrayList<>();
        for (Donator d : allDonator) {
            arrayList.add(new DonatorDTO(d.getDonator_id(),
                                         d.getName(),
                                         d.getContact(),
                                         d.getDate(),
                                         d.getUsername()));
        }
        return arrayList;
    }

    public boolean saveDonator(DonatorDTO donatorDTO) throws SQLException {
        return donatorDAO.save(new Donator(donatorDTO.getDonator_id(),
                                           donatorDTO.getName(),
                                           donatorDTO.getContact(),
                                           donatorDTO.getDate(),
                                           donatorDTO.getUsername()));
    }

    public boolean deleteDonator(String donator_id) throws SQLException {
        return donatorDAO.delete(donator_id);
    }

    public boolean updateDonator(DonatorDTO donatorDTO) throws SQLException {
        return donatorDAO.update(new Donator(donatorDTO.getDonator_id(),
                                             donatorDTO.getName(),
                                             donatorDTO.getContact(),
                                             donatorDTO.getDate(),
                                             donatorDTO.getUsername()));
    }

    public String getNextDonatorId() throws SQLException {
        return donatorDAO.getNextId();
    }
}
