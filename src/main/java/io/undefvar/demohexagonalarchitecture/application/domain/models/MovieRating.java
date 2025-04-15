package io.undefvar.demohexagonalarchitecture.application.domain.models;

public record MovieRating(
        Long id,
        Long movieId,
        String comment,
        Integer starRating
) {
    public MovieRating {
        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be null or empty.");
        }
        if (!comment.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Comment cannot be anything but alpha-numeric characters.");
        }
        if (starRating == null || starRating < 1 || starRating > 5) {
            throw new IllegalArgumentException("Star rating must be between 1 and 5.");
        }
    }
}
