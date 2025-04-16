package io.undefvar.demohexagonalarchitecture.application.domain.service.api;

import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;

public interface MovieIntegrityValidationApi {
     void validateIntegrity(Movie movie);
}
