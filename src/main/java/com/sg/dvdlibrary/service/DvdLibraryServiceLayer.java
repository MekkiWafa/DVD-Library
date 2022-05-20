package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DvdLibraryServiceLayer {

    void addDvd(Dvd dvd) throws
            DvdLibraryDuplicateIdException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException;

    List<Dvd> getAllDvds() throws
            DvdLibraryPersistenceException;

    Dvd getDvd(String dvdId) throws
            DvdLibraryPersistenceException;

    Dvd removeDvd(String dvdId) throws
            DvdLibraryPersistenceException;

    List<Dvd> getDvdByTitle(String dvdTitle) throws
            DvdLibraryPersistenceException;

    void editDvd(Dvd dvd) throws
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException;

    List<Dvd> getRecentDvds(int n) throws DvdLibraryPersistenceException;

    List<Dvd> getDvdsByMpaa(String mpaa) throws DvdLibraryPersistenceException;

    List<Dvd> getDvdsByDirector(String director) throws DvdLibraryPersistenceException;

    Map<String, List<Dvd>> getDvdsByDirectorSortedByMpaa(String director) throws DvdLibraryPersistenceException;

    List<Dvd> getDvdsByStudio(String studio) throws DvdLibraryPersistenceException;

    double getAveargeAge() throws DvdLibraryPersistenceException;

    Optional<Dvd> getNewestDvd() throws DvdLibraryPersistenceException;

    Optional<Dvd> getOldestDvd() throws DvdLibraryPersistenceException;

}
