package io.undefvar.demohexagonalarchitecture.application.domain.core.service;

import io.undefvar.demohexagonalarchitecture.application.domain.core.models.Movie;
import io.undefvar.demohexagonalarchitecture.application.domain.ports.api.MovieApi;
import org.springframework.stereotype.Component;

@Component
public class MovieService implements MovieApi {
    @Override
    public void saveMovie(Movie newMovie) {
        //TODO
    }
}
