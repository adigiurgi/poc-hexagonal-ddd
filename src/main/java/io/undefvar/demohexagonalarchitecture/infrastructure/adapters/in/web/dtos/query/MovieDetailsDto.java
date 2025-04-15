package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.query;

import java.time.LocalDate;

public record MovieDetailsDto(String title,
                              LocalDate releaseDate,
                              String directorName) {
}
