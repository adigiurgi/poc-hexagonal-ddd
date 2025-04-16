package io.undefvar.demohexagonalarchitecture.application.domain.service;

import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;
import io.undefvar.demohexagonalarchitecture.application.domain.service.api.MovieIntegrityValidationApi;

public class MovieIntegrityValidationService implements MovieIntegrityValidationApi {

    public void validateIntegrity(Movie movie){
        // Some aggregate integrity validation logic
    }
}
