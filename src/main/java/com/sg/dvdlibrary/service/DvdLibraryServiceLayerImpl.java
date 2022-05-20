package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DvdLibraryServiceLayerImpl implements DvdLibraryServiceLayer {

    private DvdLibraryDao dao;
    private DvdLibraryAuditDao auditDao;

    public DvdLibraryServiceLayerImpl(DvdLibraryDao dao, DvdLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addDvd(Dvd dvd) throws
            DvdLibraryDuplicateIdException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException {

        // First check to see if there is alreay a dvd
        // associated with the given dvd's id
        // If so, we're all done here -
        // throw a DvdLibraryDuplicateIdException
        if (dao.getDvd(dvd.getDvdId()) != null) {
            throw new DvdLibraryDuplicateIdException(
                    "ERROR: Could not create dvd.  DVD Id "
                            + dvd.getDvdId()
                            + " already exists");
        }

        // Now validate all the fields on the given DVD object.
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateDvdData(dvd);

        // We passed all our business rules checks so go ahead
        // and persist the Student object
        dao.addDvd(dvd);
        // The DVD was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "DVD " + dvd.getDvdId() + " CREATED.");

    }

    @Override
    public List<Dvd> getAllDvds() throws
            DvdLibraryPersistenceException {
        return dao.getAllDvds();
    }

    @Override
    public Dvd getDvd(String dvdId) throws
            DvdLibraryPersistenceException {
        return dao.getDvd(dvdId);
    }

    @Override
    public Dvd removeDvd(String dvdId) throws
            DvdLibraryPersistenceException {
        Dvd removedDvd = dao.removeDvd(dvdId);
        auditDao.writeAuditEntry("DVD " + dvdId + " REMOVED.");
        return removedDvd;
    }

    @Override
    public void editDvd(Dvd dvd) throws
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException {
        validateDvdData(dvd);
        dao.editDvd(dvd);
        auditDao.writeAuditEntry("DVD " + dvd.getDvdId() + " UPDATED.");
    }

    @Override
    public List<Dvd> getRecentDvds(int n) throws DvdLibraryPersistenceException {
        return dao.getRecentDvds(n);
    }

    @Override
    public List<Dvd> getDvdsByMpaa(String mpaa) throws DvdLibraryPersistenceException {
        return dao.getDvdsByMpaa(mpaa);
    }

    @Override
    public List<Dvd> getDvdsByDirector(String director) throws DvdLibraryPersistenceException {
        return dao.getDvdsByDirector(director);
    }

    @Override
    public Map<String, List<Dvd>> getDvdsByDirectorSortedByMpaa(String director) throws DvdLibraryPersistenceException {
        return dao.getDvdsByDirectorSortedByMpaa(director);
    }

    @Override
    public List<Dvd> getDvdsByStudio(String studio) throws DvdLibraryPersistenceException {
        return dao.getDvdsByStudio(studio);
    }

    @Override
    public double getAveargeAge() throws DvdLibraryPersistenceException {
        return dao.getAveargeAge();
    }

    @Override
    public Optional<Dvd> getNewestDvd() throws DvdLibraryPersistenceException {
        return dao.getNewestDvd();
    }

    @Override
    public Optional<Dvd> getOldestDvd() throws DvdLibraryPersistenceException {
        return dao.getOldestDvd();
    }


    @Override
    public List<Dvd> getDvdByTitle(String dvdTitle) throws
            DvdLibraryPersistenceException {
        return dao.getDvdByTitle(dvdTitle);
    }

    private void validateDvdData(Dvd dvd) throws
            DvdLibraryDataValidationException {
        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getReleaseDate().toString().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getNote() == null
                || dvd.getNote().trim().length() == 0) {

            throw new DvdLibraryDataValidationException(
                    "ERROR: All fields [Title, Release Date, MPAA Rating, Director Name, Studio, Notes] are required.");
        }
    }
}
