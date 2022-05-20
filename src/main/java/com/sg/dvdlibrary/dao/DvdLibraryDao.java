package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
     * @param dvd   student to be added to the roster
     * @return the DVD object previously associated with the given
     * DVD id if it exists, null otherwise
     */
    Dvd addDvd(Dvd dvd) throws DvdLibraryPersistenceException;

    /**
     * Returns a list of all DVDs in the library.
     *
     * @return list containing all DVDs in the library.
     */
    List<Dvd> getAllDvds() throws DvdLibraryPersistenceException;

    /**
     * Returns the dvd object associated with the given dvd id.
     * Returns null if no such dvd exists
     *
     * @param dvdId ID of the dvd to retrieve
     * @return the DVD object associated with the given dvd id,
     * null if no such dvd exists
     */
    Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException;

    /**
     * Returns the collection of DVD object associated with the given title.
     * Returns null if no such DVD exists
     *
     * @param dvdTitle title of the DVD to retrieve
     * @return the DVD object associated with the given title,
     * null if no such DVD exists
     */
    List<Dvd> getDvdByTitle(String dvdTitle) throws DvdLibraryPersistenceException;

    /**
     * Removes from the library the dvd associated with the given id.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given id
     *
     * @param dvdId id of dvd to be removed
     * @return Dvd object that was removed or null if no dvd
     * was associated with the given dvd id
     */
    Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException;

    /**
     * Returns the updated dvd object.
     * Returns null if no such dvd exists
     *
     * @param editedDvd the edited dvd
     * @return the updated DVD object,
     * null if no such dvd exists
     */
    Dvd editDvd(Dvd editedDvd) throws DvdLibraryPersistenceException;

    /**
     * Returns the movies released in the last N years.
     * Returns null if no such dvd exists
     *
     * @param n number of last years
     * @return the the movies released in the last N years,
     * null if no such dvd exists
     */
    List<Dvd> getRecentDvds(int n) throws DvdLibraryPersistenceException;

    /**
     * Returns all the movies with a given MPAA rating.
     * Returns null if no such dvd exists
     *
     * @param mpaa MPAA rating
     * @return the movies with a given MPAA rating,
     * null if no such dvd exists
     */
    List<Dvd> getDvdsByMpaa(String mpaa) throws DvdLibraryPersistenceException;

    /**
     * Returns all the movies by a given director.
     * Returns null if no such dvd exists
     *
     * @param director director name
     * @return the movies by a given director,
     * null if no such dvd exists
     */
    List<Dvd> getDvdsByDirector(String director) throws DvdLibraryPersistenceException;

    /**
     * Returns all the movies by a given director and sorted into separate data structures by MPAA rating
     * Returns null if no such dvd exists
     *
     * @param director director name
     * @return the movies by a given director and sorted into separate data structures by MPAA rating,
     * null if no such dvd exists
     */
    Map<String, List<Dvd>> getDvdsByDirectorSortedByMpaa(String director) throws DvdLibraryPersistenceException;

    /**
     * Returns all the movies released by a particular studio
     * Returns null if no such dvd exists
     *
     * @param studio studio
     * @return all the movies released by a particular studio,
     * null if no such dvd exists
     */
    List<Dvd> getDvdsByStudio(String studio) throws DvdLibraryPersistenceException;

    /**
     * Returns the average age of the movies in the collection
     *
     * @return all the movies released by a particular studio,
     */
    double getAveargeAge() throws DvdLibraryPersistenceException;

    /**
     * Returns the newest movie in your collection.
     *
     * @return the newest movie in your collection
     */
    Optional<Dvd> getNewestDvd() throws DvdLibraryPersistenceException;

    /**
     * Returns the oldest movie in your collection.
     *
     * @return the oldest movie in your collection
     */
    Optional<Dvd> getOldestDvd() throws DvdLibraryPersistenceException;

}

