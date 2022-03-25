package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

/**
 * @author Wafa Mekki
 */
public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("===Main Menu===");
        io.print("1. List DVDs");
        io.print("2. Add DVD");
        io.print("3. View DVD information");
        io.print("4. Remove DVD");
        io.print("5. Edit DVD");
        io.print("6. Search DVD by Title");
        io.print("7. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 7);
    }

    /*This method prompts the user for DVD title, release date, MPAA rating, director's name, studio and note, 
     gathers this information, creates a new Dvd object, and returns it to the caller.*/
    public Dvd getNewDvdInfo() {
        String dvdId = io.readString("Please enter DVD ID");
        String dvdTitle = io.readString("Please enter the DVD title");
        String releaseDate = io.readString("Please enter the release date");
        String mpaaRating = io.readString("Please enter the MPAA rating");
        String directorName = io.readString("Please enter the director's name");
        String studio = io.readString("Please enter the studio");
        String note = io.readString("Please enter your note");

        return new Dvd(dvdId, dvdTitle, releaseDate, mpaaRating, directorName, studio, note);
    }

    public void displayAddDvdBanner() {
        io.print("=== ADD DVD ===");
    }

    public void displayAddSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }

    public void displayDvdList(List<Dvd> dvdList) {
        if (dvdList.isEmpty()) {
            io.print("The DVD library is empty");
        } else {
            for (Dvd currentDvd : dvdList) {
                String dvdInfo = String.format("%s | %s | %s | %s | %s | %s | %s ",
                        currentDvd.getDvdId(),
                        currentDvd.getTitle(),
                        currentDvd.getReleaseDate(),
                        currentDvd.getMpaaRating(),
                        currentDvd.getDirectorName(),
                        currentDvd.getStudio(),
                        currentDvd.getNote());
                io.print(dvdInfo);
            }
            io.readString("Please hit enter to continue.");
        }
    }

    public void displayListAllDvdBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDvdIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director Name: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("Note: " + dvd.getNote());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(Dvd dvdRecord) {
        if (dvdRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditDvdBanner() {
        io.print("=== EDIT DVD ===");
    }

    public void displayEditSuccessBanner() {
        io.readString("DVD successfully updated.  Please hit enter to continue");
    }

    public Dvd getUpdatedDvdTitle(Dvd oldDvd) {
        String title = io.readString("Please enter the new Title");
        oldDvd.setTitle(title);
        return oldDvd;
    }

    public Dvd getUpdatedDvdReleaseDate(Dvd oldDvd) {
        String releaseDate = io.readString("Please enter the new Release Date");
        oldDvd.setReleaseDate(releaseDate);
        return oldDvd;
    }

    public Dvd getUpdatedDvdDirectorName(Dvd oldDvd) {
        String directorName = io.readString("Please enter the new director name");
        oldDvd.setDirectorName(directorName);
        return oldDvd;
    }

    public Dvd getUpdatedDvdRating(Dvd oldDvd) {
        String rating = io.readString("Please enter the new MPAA Rating");
        oldDvd.setMpaaRating(rating);
        return oldDvd;
    }

    public Dvd getUpdatedDvdStudio(Dvd oldDvd) {
        String studio = io.readString("Please enter the new Studio");
        oldDvd.setStudio(studio);
        return oldDvd;
    }

    public Dvd getUpdatedDvdNotes(Dvd oldDvd) {
        String note = io.readString("Please enter your new note");
        oldDvd.setNote(note);
        return oldDvd;
    }

    public Dvd getUpdatedDvdInfo(Dvd oldDvd) {
        String dvdTitle = io.readString("Please enter the new DVD title");
        String releaseDate = io.readString("Please enter the new release date");
        String mpaaRating = io.readString("Please enter the new MPAA rating");
        String directorName = io.readString("Please enter the new director's name");
        String studio = io.readString("Please enter the new studio");
        String note = io.readString("Please enter your note");

        oldDvd.setTitle(dvdTitle);
        oldDvd.setReleaseDate(releaseDate);
        oldDvd.setMpaaRating(mpaaRating);
        oldDvd.setDirectorName(directorName);
        oldDvd.setStudio(studio);
        oldDvd.setNote(note);

        return oldDvd;
    }

    public void displaySearchDvdBanner() {
        io.print("=== Search DVD By Title ===");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public int printEditMenuAndGetSelection(Dvd oldDvd) {
        io.print("====================Edit Menu===================");
        io.print("-------Options---------|------Current Entry-----");
        io.print("1. Edit Title          | " + oldDvd.getTitle());
        io.print("2. Edit Release Date   | " + oldDvd.getReleaseDate());
        io.print("3. Edit Director Name  | " + oldDvd.getDirectorName());
        io.print("4. Edit MPAA Rating    | " + oldDvd.getMpaaRating());
        io.print("5. Edit Studio         | " + oldDvd.getStudio());
        io.print("6. Edit Notes          | " + oldDvd.getNote());
        io.print("7. Edit All");
        io.print("8. Exit Editor");

        return io.readInt("Please select from the"
                + " above choices.", 1, 8);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
