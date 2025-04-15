package io.undefvar.demohexagonalarchitecture.application.domain.models;

import java.time.LocalDate;
import java.util.Set;
public record Movie(
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        String directorName,
        Set<MovieRating> movieRatings
) {
    public Movie {
        if(title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if(!title.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Title cannot be anything but alpha-numeric characters.");
        }
        if(description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        if(!description.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Description cannot be anything but alpha-numeric characters.");
        }
        if(directorName == null || directorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Director name cannot be null or empty.");
        }
        if(!directorName.matches("^[a-zA-Z ]+$")) {
            throw new IllegalArgumentException("Director name cannot be anything but alpha characters.");
        }
    }
}
