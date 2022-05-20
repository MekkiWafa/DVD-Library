package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryDaoFileImplTest {

    DvdLibraryDao testDao;

    public DvdLibraryDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testlibrary.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DvdLibraryDaoFileImpl(testFile);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddGetDvd() throws DvdLibraryPersistenceException {
        // Create our method test inputs
        String dvdId = "0001";
        Dvd dvd = new Dvd(dvdId, "Matrix", LocalDate.parse("1999-01-01"), "PG", "Lilly Wachowski", "Warner Bros", "Action");

        //  Add the dvd to the DAO
        testDao.addDvd(dvd);
        // Get the dvd from the DAO
        Dvd retrievedDvd = testDao.getDvd(dvdId);

        // Check the data is equal
        assertEquals(dvd.getDvdId(), retrievedDvd.getDvdId(), "Checking dvd id.");
        assertEquals(dvd.getTitle(), retrievedDvd.getTitle(), "Checking dvd title.");
        assertEquals(dvd.getDirectorName(), retrievedDvd.getDirectorName(), "Checking director name.");
        assertEquals(dvd.getMpaaRating(), retrievedDvd.getMpaaRating(), "Checking MPAA");
        assertEquals(dvd.getReleaseDate(), retrievedDvd.getReleaseDate(), "Checking release date");
        assertEquals(dvd.getStudio(), retrievedDvd.getStudio(), "Checking studio");
        assertEquals(dvd.getNote(), retrievedDvd.getNote(), "Checking Note");
    }

    @Test
    public void testAddGetAllDvds() throws DvdLibraryPersistenceException {
        // Create our first dvd
        Dvd firstDvd = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"), "PG", "Lilly Wachowski", "Warner Bros", "Action");

        // Create our second dvd
        Dvd secondDvd = new Dvd("002", "Toy Story 1", LocalDate.parse("2002-02-02"), "PG", "John Lasseter", "Pixar Animation Studios", "Good film");

        // Add both our dvds to the DAO
        testDao.addDvd(firstDvd);
        testDao.addDvd(secondDvd);

        // Retrieve the list of all dvds within the DAO
        List<Dvd> allDvds = testDao.getAllDvds();

        // First check the general contents of the list
        assertNotNull(allDvds, "The list of dvds must not null");
        assertEquals(2, allDvds.size(), "List of dvds should have 2 dvds.");

        // Then the specifics
        assertTrue(testDao.getAllDvds().contains(firstDvd),
                "The list of dvds should include Matrix.");
        assertTrue(testDao.getAllDvds().contains(secondDvd),
                "The list of students should include Toy Story.");

    }

    @Test
    public void testRemoveDvd() throws DvdLibraryPersistenceException {
        // Create our first dvd
        Dvd firstDvd = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"), "PG", "Lilly Wachowski", "Warner Bros", "Action");

        // Create our second dvd
        Dvd secondDvd = new Dvd("002", "Toy Story 1", LocalDate.parse("2002-02-02"), "PG", "John Lasseter", "Pixar Animation Studios", "Good film");

        // Add both our dvds to the DAO
        testDao.addDvd(firstDvd);
        testDao.addDvd(secondDvd);

        // remove the first dvd - Matrix
        Dvd removedDvd = testDao.removeDvd(firstDvd.getDvdId());

        // Check that the correct object was removed.
        assertEquals(removedDvd, firstDvd, "The removed dvd should be Matrix.");

        // Get all the students
        List<Dvd> allDvds = testDao.getAllDvds();

        // First check the general contents of the list
        assertNotNull(allDvds, "All dvds list should be not null.");
        assertEquals(1, allDvds.size(), "All dvds should only have 1 dvd.");

        // Then the specifics
        assertFalse(allDvds.contains(firstDvd), "All dvds should NOT include Matrix.");
        assertTrue(allDvds.contains(secondDvd), "All dvd should include Toy Story.");

        // Remove the second dvd
        removedDvd = testDao.removeDvd(secondDvd.getDvdId());
        // Check that the correct object was removed.
        assertEquals(removedDvd, secondDvd, "The removed dvd should be Toy Story.");

        // retrieve all of the dvds again, and check the list.
        allDvds = testDao.getAllDvds();

        // Check the contents of the list - it should be empty
        assertTrue(allDvds.isEmpty(), "The retrieved list of dvds should be empty.");

        // Try to 'get' both students by their old id - they should be null!
        Dvd retrievedDvd = testDao.getDvd(firstDvd.getDvdId());
        assertNull(retrievedDvd, "Matrix was removed, should be null.");

        retrievedDvd = testDao.getDvd(secondDvd.getDvdId());
        assertNull(retrievedDvd, "Toy Story was removed, should be null.");

    }

    @Test
    void testEditDvd() throws DvdLibraryPersistenceException {
        // Create our  dvd
        Dvd dvd = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"), "PG", "Lilly Wachowski", "Warner Bros", "Action");

        // Add dvd to the DAO
        testDao.addDvd(dvd);
        Dvd retrievedDvd = testDao.getDvd(dvd.getDvdId());
        // assert the old title
        assertEquals("Matrix", retrievedDvd.getTitle(), "The edited dvd should be Matrix.");
        // edit the dvd - Matrix
        dvd.setTitle("Matrixx");
        testDao.editDvd(dvd);
        retrievedDvd = testDao.getDvd(dvd.getDvdId());
        assertEquals("Matrixx", retrievedDvd.getTitle(), "The edited dvd should be Matrixx.");

        // Check that the correct object was removed.
//        assertEquals(editedDvd, firstDvd, "The edited dvd should be Matrix.");

    }

}