package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DvdLibraryDaoStubImpl implements DvdLibraryDao {

    public Dvd onlyDvd;

    public DvdLibraryDaoStubImpl() {
        onlyDvd = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"),
                "PG", "Lilly Wachowski", "Warner Bros", "Action");
    }

    public DvdLibraryDaoStubImpl(Dvd testDvd) {
        this.onlyDvd = testDvd;
    }

    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        if (dvd.getDvdId().equals(onlyDvd.getDvdId())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        List<Dvd> dvdList = new ArrayList<>();
        dvdList.add(onlyDvd);
        return dvdList;
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getDvdId())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getDvdId())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public List<Dvd> getDvdByTitle(String dvdTitle) throws DvdLibraryPersistenceException {
        return null;
    }


    @Override
    public Dvd editDvd(Dvd editedDvd) throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public List<Dvd> getRecentDvds(int n) throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public List<Dvd> getDvdsByMpaa(String mpaa) throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public List<Dvd> getDvdsByDirector(String director) throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public Map<String, List<Dvd>> getDvdsByDirectorSortedByMpaa(String director) throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public List<Dvd> getDvdsByStudio(String studio) throws DvdLibraryPersistenceException {
        return null;
    }

    @Override
    public double getAveargeAge() throws DvdLibraryPersistenceException {
        return 0;
    }

    @Override
    public Optional<Dvd> getNewestDvd() throws DvdLibraryPersistenceException {
        return Optional.empty();
    }

    @Override
    public Optional<Dvd> getOldestDvd() throws DvdLibraryPersistenceException {
        return Optional.empty();
    }
}
