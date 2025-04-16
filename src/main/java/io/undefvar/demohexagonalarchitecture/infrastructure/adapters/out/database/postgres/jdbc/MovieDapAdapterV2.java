package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.database.postgres.jdbc;

import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;
import io.undefvar.demohexagonalarchitecture.application.domain.ports.dao.MovieDaoPort;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command.NewMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service("implementation_v2")
@RequiredArgsConstructor
public class MovieDapAdapterV2 implements MovieDaoPort {
    @Override
    public Optional<Movie> findMovieByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public List<Movie> findAllMoviesWithRatings() {
        return null;
    }

    @Override
    public List<Movie> findAllMoviesWithoutRatings() {
        return null;
    }

    @Override
    public void saveMovie(NewMovieDto movie) {

    }

    @Override
    public void updateMovie(Movie newMovie) {

    }

    @Override
    public void deleteMovie(Movie oldMovie) {

    }
}
