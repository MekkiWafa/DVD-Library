package com.sg.dvdlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Wafa Mekki
 */
@Data
@AllArgsConstructor
public class Dvd {
    private String dvdId;
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String note;
}
