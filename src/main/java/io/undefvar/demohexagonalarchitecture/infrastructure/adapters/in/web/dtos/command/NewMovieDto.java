package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command;

import java.time.LocalDate;

public record NewMovieDto(
        String title,
        String description,
        LocalDate releaseDate,
        String directorName
) {
}
