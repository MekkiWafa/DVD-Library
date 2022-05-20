package com.sg.dvdlibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Wafa Mekki
 */
@Data
@AllArgsConstructor
public class Dvd {
    private String dvdId;
    private String title;
    private LocalDate releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return Objects.equals(dvdId, dvd.dvdId) &&
                Objects.equals(title, dvd.title) &&
                Objects.equals(releaseDate, dvd.releaseDate) &&
                Objects.equals(mpaaRating, dvd.mpaaRating) &&
                Objects.equals(directorName, dvd.directorName) &&
                Objects.equals(studio, dvd.studio) &&
                Objects.equals(note, dvd.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dvdId, title, releaseDate, mpaaRating, directorName, studio, note);
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "dvdId='" + dvdId + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", mpaaRating='" + mpaaRating + '\'' +
                ", directorName='" + directorName + '\'' +
                ", studio='" + studio + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
