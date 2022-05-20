package com.sg.dvdlibrary.controller;

/*
  @author Wafa Mekki
 */

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.service.DvdLibraryDataValidationException;
import com.sg.dvdlibrary.service.DvdLibraryDuplicateIdException;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DvdLibraryView;

import java.util.List;

public class DvdLibraryController {
    private DvdLibraryView view;
    private DvdLibraryServiceLayer service;

    public DvdLibraryController(DvdLibraryView view, DvdLibraryServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        searchDvdByTitle();
                        break;
                    case 7:
                        searchDvdByDirector();
                        break;
                    case 8:
                        searchDvdByMppa();
                        break;
                    case 9:
                        searchDvdByStudio();
                        break;
                    case 10:
                        getRecentDvds();
                        break;
                    case 11:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DvdLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDvd() throws DvdLibraryPersistenceException {
        view.displayAddDvdBanner();
        boolean hasErrors = false;
        do {
            Dvd newDvd = view.getNewDvdInfo();
            try {
                service.addDvd(newDvd);
                view.displayAddSuccessBanner();
                hasErrors = false;
            } catch (DvdLibraryDataValidationException | DvdLibraryDuplicateIdException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listDvds() throws DvdLibraryPersistenceException {
        view.displayListAllDvdBanner();
        List<Dvd> dvdList = service.getAllDvds();
        view.displayDvdList(dvdList);
    }


    private void viewDvd() throws DvdLibraryPersistenceException {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = service.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DvdLibraryPersistenceException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = service.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void editDvd() throws DvdLibraryPersistenceException {
        view.displayEditDvdBanner();
        String oldDvdId = view.getDvdIdChoice();
        Dvd oldDvd = service.getDvd(oldDvdId);
        boolean keepGoing = true;
        Dvd editedDvd = null;

        boolean hasErrors = false;
        do {
            try {
                while (keepGoing) {
                    int editMenuSelection = getEditMenuSelection(oldDvd);
                    switch (editMenuSelection) {
                        case 1:
                            editedDvd = editDvdTitle(oldDvd);
                            service.editDvd(editedDvd);
                            break;
                        case 2:
                            editedDvd = editReleaseDate(oldDvd);
                            service.editDvd(editedDvd);
                            break;
                        case 3:
                            editedDvd = editDirectorName(oldDvd);
                            service.editDvd(editedDvd);
                            break;
                        case 4:
                            editedDvd = editRating(oldDvd);
                            service.editDvd(editedDvd);
                            break;
                        case 5:
                            editedDvd = editStudio(oldDvd);
                            service.editDvd(editedDvd);
                            break;
                        case 6:
                            editedDvd = editNotes(oldDvd);
                            service.editDvd(editedDvd);
                            break;
                        case 7:
                            editedDvd = editAllData(oldDvd);
                            service.editDvd(editedDvd);
                            break;
                        case 8:
                            service.editDvd(editedDvd);
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                    }
                }
                view.displayEditSuccessBanner();
                hasErrors = false;
            } catch (DvdLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private Dvd editDvdTitle(Dvd oldDvd) {
        return view.getUpdatedDvdTitle(oldDvd);
    }

    private Dvd editReleaseDate(Dvd oldDvd) {
        return view.getUpdatedDvdReleaseDate(oldDvd);
    }

    private Dvd editDirectorName(Dvd oldDvd) {
        return view.getUpdatedDvdDirectorName(oldDvd);
    }

    private Dvd editRating(Dvd oldDvd) {
        return view.getUpdatedDvdRating(oldDvd);
    }

    private Dvd editStudio(Dvd oldDvd) {
        return view.getUpdatedDvdStudio(oldDvd);
    }

    private Dvd editNotes(Dvd oldDvd) {
        return view.getUpdatedDvdNotes(oldDvd);
    }

    private Dvd editAllData(Dvd oldDvd) {
        return view.getUpdatedDvdInfo(oldDvd);
    }

    private void searchDvdByTitle() throws DvdLibraryPersistenceException {
        view.displaySearchDvdByTitleBanner();
        String dvdTitle = view.getDvdTitleChoice();
        List<Dvd> dvdList = service.getDvdByTitle(dvdTitle);
        view.displayDvdList(dvdList);

    }

    private void searchDvdByDirector() throws DvdLibraryPersistenceException {
        view.displaySearchDvdByDirectorBanner();
        String dvdDirector = view.getDvdDirectorChoice();
        List<Dvd> dvdList = service.getDvdsByDirector(dvdDirector);
        view.displayDvdList(dvdList);

    }

    private void searchDvdByMppa() throws DvdLibraryPersistenceException {
        view.displaySearchDvdByMpaaBanner();
        String dvdMpaa = view.getDvdMPAAChoice();
        List<Dvd> dvdList = service.getDvdsByMpaa(dvdMpaa);
        view.displayDvdList(dvdList);

    }

    private void searchDvdByStudio() throws DvdLibraryPersistenceException {
        view.displaySearchDvdByStudioBanner();
        String dvdStudio = view.getDvdStudioChoice();
        List<Dvd> dvdList = service.getDvdsByStudio(dvdStudio);
        view.displayDvdList(dvdList);

    }

    private void getRecentDvds() throws DvdLibraryPersistenceException {
        view.displayRecentDvdBanner();
        int n = view.getDvdListSize();
        List<Dvd> dvdList = service.getRecentDvds(n);
        view.displayDvdList(dvdList);
    }

    private int getEditMenuSelection(Dvd oldDvd) {
        return view.printEditMenuAndGetSelection(oldDvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}