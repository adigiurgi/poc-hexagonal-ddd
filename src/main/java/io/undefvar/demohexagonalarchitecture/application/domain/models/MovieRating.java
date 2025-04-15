package io.undefvar.demohexagonalarchitecture.application.domain.models;

public record MovieRating(
        Long id,
        Long movieId,
        String comment,
        Integer starRating
) {
}
