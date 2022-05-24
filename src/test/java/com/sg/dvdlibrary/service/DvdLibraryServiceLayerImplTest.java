package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.Dvd;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DvdLibraryServiceLayerImplTest {
    private DvdLibraryServiceLayer service;

    public DvdLibraryServiceLayerImplTest() {
        // wire the Service Layer with stub implementations of the Dao and
        // Audit Dao
       /* DvdLibraryDao dao = new DvdLibraryDaoStubImpl();
        DvdLibraryAuditDao auditDao = new DvdLibraryAuditDaoStubImpl();

        service = new DvdLibraryServiceLayerImpl(dao, auditDao);*/
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
                ctx.getBean("serviceLayer", DvdLibraryServiceLayer.class);

    }

    @Test
    public void testCreateValidDvd() {
        // ARRANGE
        Dvd dvd = new Dvd("002", "Toy Story 1", LocalDate.parse("2002-02-02"), "PG", "John Lasseter", "Pixar Animation Studios", "Good film");

        // ACT
        try {
            service.addDvd(dvd);
        } catch (DvdLibraryDataValidationException
                | DvdLibraryDuplicateIdException
                | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("Dvd was valid. No exception should have been thrown.");
        }
    }

    @Test
    public void testCreateDuplicateDvd() {
        // ARRANGE
        Dvd dvd = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"),
                "PG", "Lilly Wachowski", "Warner Bros", "Action");
        // ACT
        try {
            service.addDvd(dvd);
            fail("Expected DupeId Exception was not thrown.");
        } catch (DvdLibraryDataValidationException
                | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDuplicateIdException e) {
            // ASSERT
            return;
        }

    }

    @Test
    public void testCreateDvdInvalidData() {
        // ARRANGE
        Dvd dvd = new Dvd("0002", "", LocalDate.parse("1999-01-01"),
                "PG", "Lilly Wachowski", "Warner Bros", "Action");
        // ACT
        try {
            service.addDvd(dvd);
            fail("Expected Data validation Exception was not thrown.");
        } catch (DvdLibraryDuplicateIdException
                | DvdLibraryPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (DvdLibraryDataValidationException e) {
            // ASSERT
            return;
        }

    }

    @Test
    void getAllDvds() throws DvdLibraryPersistenceException {
        // ARRANGE
        Dvd testClone = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"),
                "PG", "Lilly Wachowski", "Warner Bros", "Action");

        // ACT & ASSERT
        assertEquals(1, service.getAllDvds().size(),
                "Should only have one dvd.");
        assertTrue(service.getAllDvds().contains(testClone),
                "The one dvd should be Matrix.");
    }

    @Test
    void getDvd() throws DvdLibraryPersistenceException {
        // ARRANGE
        Dvd dvd = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"),
                "PG", "Lilly Wachowski", "Warner Bros", "Action");

        // ACT & ASSERT
        Dvd shouldBeMatrix = service.getDvd("0001");
        assertNotNull(shouldBeMatrix, "Getting 0001 should be not null.");
        assertEquals(dvd, shouldBeMatrix,
                "dvd stored under 0001 should be Matrix.");

        Dvd shouldBeNull = service.getDvd("0002");
        assertNull(shouldBeNull, "Getting 0002 should be null.");

    }

    @Test
    void removeDvd() throws DvdLibraryPersistenceException {
        // ARRANGE
        Dvd dvd = new Dvd("0001", "Matrix", LocalDate.parse("1999-01-01"),
                "PG", "Lilly Wachowski", "Warner Bros", "Action");

        // ACT & ASSERT
        Dvd shouldBeMatrix = service.removeDvd("0001");
        assertNotNull(shouldBeMatrix, "Getting 0001 should be not null.");
        assertEquals(dvd, shouldBeMatrix,
                "dvd removed from 0001 should be Matrix.");

        Dvd shouldBeNull = service.removeDvd("0002");
        assertNull(shouldBeNull, "Removing 0002 should be null.");

    }

}