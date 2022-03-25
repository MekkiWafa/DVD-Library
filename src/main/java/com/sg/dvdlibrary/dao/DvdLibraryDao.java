package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

/**
 * @author Wafa Mekki
 */
public interface DvdLibraryDao {

    /**
     * Adds the given DVD to the library and associates it with the given
     * dvd id. If there is already a dvd associated with the given
     * dvd id it will return that dvd object, otherwise it will
     * return null.
     *
     * @param dvdId id with which student is to be associated
     * @param dvd   student to be added to the roster
     * @return the DVD object previously associated with the given
     * DVD id if it exists, null otherwise
     */
    Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException;

    /**
     * Returns a list of all DVDs in the library.
     *
     * @return list containing all DVDs in the library.
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;

    /**
     * Returns the dvd object associated with the given dvd id.
     * Returns null if no such dvd exists
     *
     * @param dvdId ID of the dvd to retrieve
     * @return the DVD object associated with the given dvd id,
     * null if no such dvd exists
     */
    Dvd getDvd(String dvdId) throws DvdLibraryDaoException;

    /**
     * Returns the collection of DVD object associated with the given title.
     * Returns null if no such DVD exists
     *
     * @param dvdTitle title of the DVD to retrieve
     * @return the DVD object associated with the given title,
     * null if no such DVD exists
     */
    List<Dvd> getDvdByTitle(String dvdTitle) throws DvdLibraryDaoException;

    /**
     * Removes from the library the dvd associated with the given id.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId id of dvd to be removed
     * @return Dvd object that was removed or null if no dvd
     * was associated with the given dvd id
     */
    Dvd removeDvd(String dvdId) throws DvdLibraryDaoException;

    /**
     * Returns the updated dvd object.
     * Returns null if no such dvd exists
     *
     * @param editedDvd the edited dvd
     * @return the updated DVD object,
     * null if no such dvd exists
     */
    Dvd editDvd(Dvd editedDvd) throws DvdLibraryDaoException;
}

