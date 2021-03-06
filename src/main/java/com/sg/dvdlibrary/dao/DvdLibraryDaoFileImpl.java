package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wafa Mekki
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    private final String LIBRARY_FILE;
    private static final String DELIMITER = ";";
    private Map<String, Dvd> dvds = new HashMap<>();

    public DvdLibraryDaoFileImpl() {
        LIBRARY_FILE = "library.txt";
    }

    public DvdLibraryDaoFileImpl(String LibraryTextFile) {
        LIBRARY_FILE = LibraryTextFile;
    }
    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryPersistenceException {
        loadLibrary();
        Dvd newDvd = dvds.put(dvd.getDvdId(), dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdId) throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public List<Dvd> getDvdByTitle(String dvdTitle) throws DvdLibraryPersistenceException {
        loadLibrary();
        List<Dvd> dvdList = new ArrayList<>();
        for (Dvd dvd : dvds.values()) {
            if (dvd.getTitle().toLowerCase().contains(dvdTitle.toLowerCase()))
                dvdList.add(dvd);
        }
        return dvdList;
    }

    @Override
    public Dvd removeDvd(String dvdId) throws DvdLibraryPersistenceException {
        loadLibrary();
        Dvd removedDvd = dvds.remove(dvdId);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public Dvd editDvd(Dvd editedDvd) throws DvdLibraryPersistenceException {
        loadLibrary();
        Dvd updatedDvd = dvds.replace(editedDvd.getDvdId(), editedDvd);
        writeLibrary();
        return updatedDvd;
    }


    private Dvd unmarshallDvd(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // create a new Dvd object to satisfy
        // the requirements of the Dvd constructor.

        return new Dvd(dvdTokens[0], dvdTokens[1], LocalDate.parse(dvdTokens[2]), dvdTokens[3],
                dvdTokens[4], dvdTokens[5], dvdTokens[6]);
    }

    private void loadLibrary() throws DvdLibraryPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryPersistenceException(
                    "-_- Could not load dvd library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        // Go through LIBRARY_FILE line by line, decoding each line into a
        // Dvd object by calling the unMarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd
            currentDvd = unmarshallDvd(currentLine);
            // We are going to use the dvd id as the map key for our dvd object.
            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getDvdId(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd) {
        // We need to turn a Dvd object into a line of text for our file.
        // Get out each property, and concatenate with our DELIMITER as a kind of spacer.

        // Turn a DVD to text! Return it!
        return aDvd.getDvdId() + DELIMITER
                + aDvd.getTitle() + DELIMITER
                + aDvd.getReleaseDate() + DELIMITER
                + aDvd.getMpaaRating() + DELIMITER
                + aDvd.getDirectorName() + DELIMITER
                + aDvd.getStudio() + DELIMITER
                + aDvd.getNote();
    }

    /**
     * Writes all dvds in the library out to a LIBRARY_FILE.  See loadLibrary
     * for file format.
     *
     * @throws DvdLibraryPersistenceException if an error occurs writing to the file
     */
    private void writeLibrary() throws DvdLibraryPersistenceException {

        try (PrintWriter out = new PrintWriter(new FileWriter(LIBRARY_FILE))) {
            String dvdAsText;
            List<Dvd> dvdList = this.getAllDvds();
            for (Dvd currentDvd : dvdList) {
                // turn a DVD into a String
                dvdAsText = marshallDvd(currentDvd);
                // write the DVD object to the file
                out.println(dvdAsText);
                // force PrintWriter to write line to the file
                out.flush();
            }
        } catch (IOException e) {
            throw new DvdLibraryPersistenceException(
                    "Could not save DVD data.", e);
        }
        // Clean up
    }

    @Override
    public List<Dvd> getRecentDvds(int n) throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .sorted(Comparator.comparing(Dvd::getReleaseDate).reversed())
                .collect(Collectors.toList())
                .subList(0, n);
    }

    @Override
    public List<Dvd> getDvdsByMpaa(String mpaa) throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .filter((dvd) -> dvd.getMpaaRating().toLowerCase().equals(mpaa.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> getDvdsByDirector(String director) throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .filter((dvd) -> dvd.getDirectorName().toLowerCase().contains(director.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Dvd>> getDvdsByDirectorSortedByMpaa(String director) throws DvdLibraryPersistenceException {
        loadLibrary();
        Map<String, List<Dvd>> dvdMpaa = dvds.values().stream()
                .filter((dvd) -> dvd.getDirectorName().toLowerCase().contains(director.toLowerCase()))
                .collect(Collectors.groupingBy((dvd) -> dvd.getMpaaRating()));
        return dvdMpaa;
    }

    @Override
    public List<Dvd> getDvdsByStudio(String studio) throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .filter((dvd) -> dvd.getStudio().toLowerCase().contains(studio.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public double getAveargeAge() throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .mapToDouble(dvd -> Period.between(LocalDate.now(), dvd.getReleaseDate()).get(ChronoUnit.YEARS))
                .average()
                .getAsDouble();
    }

    @Override
    public Optional<Dvd> getNewestDvd() throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .sorted(Comparator.comparing(Dvd::getReleaseDate).reversed())
                .findFirst();
    }

    @Override
    public Optional<Dvd> getOldestDvd() throws DvdLibraryPersistenceException {
        loadLibrary();
        return dvds.values().stream()
                .sorted(Comparator.comparing(Dvd::getReleaseDate))
                .findFirst();
    }

}
