package com.sg.dvdlibrary.controller;

/*
  @author Wafa Mekki
 */

import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.dao.DvdLibraryDao;

import java.util.*;

public class DvdLibraryController {
    private DvdLibraryView view;
    private DvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryView view, DvdLibraryDao dao) {
        this.view = view;
        this.dao = dao;
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
                        // continueAdding();
                        break;
                    case 3:
                        viewDvd();
                        // displayDvdByTitle();
                        break;
                    case 4:
                        removeDvd();
                        //  continueRemoving();
                        break;
                    case 5:
                        editDvd();
                        //  continueEditing();
                        break;
                    case 6:
                        searchDvdByTitle();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDvd() throws DvdLibraryDaoException {
        view.displayAddDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getDvdId(), newDvd);
        view.displayAddSuccessBanner();
    }

    private void listDvds() throws DvdLibraryDaoException {
        view.displayListAllDvdBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }


    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd dvd = dao.getDvd(dvdId);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdIdChoice();
        Dvd removedDvd = dao.removeDvd(dvdId);
        view.displayRemoveResult(removedDvd);
    }

    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        String oldDvdId = view.getDvdIdChoice();
        Dvd oldDvd = dao.getDvd(oldDvdId);
        boolean keepGoing = true;
        Dvd editedDvd = null;
        while (keepGoing) {
            int editMenuSelection = getEditMenuSelection(oldDvd);
            switch (editMenuSelection) {
                case 1:
                    editedDvd = editDvdTitle(oldDvd);
                    break;
                case 2:
                    editedDvd = editReleaseDate(oldDvd);
                    break;
                case 3:
                    editedDvd = editDirectorName(oldDvd);
                    break;
                case 4:
                    editedDvd = editRating(oldDvd);
                    break;
                case 5:
                    editedDvd = editStudio(oldDvd);
                    break;
                case 6:
                    editedDvd = editNotes(oldDvd);
                    break;
                case 7:
                    editedDvd = editAllData(oldDvd);
                    break;
                case 8:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        dao.editDvd(editedDvd);
        view.displayEditSuccessBanner();
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

    private void searchDvdByTitle() throws DvdLibraryDaoException {
        view.displaySearchDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        List<Dvd> dvdList = dao.getDvdByTitle(dvdTitle);
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