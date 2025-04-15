package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.postgresJDBC;

import io.undefvar.demohexagonalarchitecture.application.domain.models.MovieRating;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.postgresJDBC.entities.MovieEntity;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.postgresJDBC.repositories.MovieRatingsRepository;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.postgresJDBC.repositories.MoviesRepository;
import io.undefvar.demohexagonalarchitecture.application.domain.dao.MovieDao;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command.NewMovieDto;
import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MoviesDaoAdapter implements MovieDao {
    private final MoviesRepository moviesRepository;
    private final MovieRatingsRepository movieRatingsRepository;
    @Override
    public Optional<Movie> findMovieByTitle(String title) {
        return moviesRepository.findMovieByTitle(title);

    }

    @Override
    public List<Movie> findAllMoviesWithRatings() {
        return ((List<MovieEntity>) moviesRepository.findAll())
                .stream()
                .map(movieEntity -> new Movie(
                        movieEntity.id(),
                        movieEntity.title(),
                        movieEntity.description(),
                        movieEntity.releaseDate(),
                        movieEntity.directorName(),
                        (movieRatingsRepository.findByMovieId(movieEntity.id())
                                .stream()
                                .map(movieRatingEntity -> new MovieRating(
                                        movieRatingEntity.id(),
                                        movieRatingEntity.movieId(),
                                        movieRatingEntity.comment(),
                                        movieRatingEntity.starRating()
                                )).collect(Collectors.toSet())
                        )
                )).toList();
    }

    @Override
    public List<Movie> findAllMoviesWithoutRatings() {
        return ((List<MovieEntity>) moviesRepository.findAll())
                .stream()
                .map(movieEntity -> new Movie(
                        movieEntity.id(),
                        movieEntity.title(),
                        movieEntity.description(),
                        movieEntity.releaseDate(),
                        movieEntity.directorName(),
                        Set.of()
                )).toList();
    }

    @Override
    public void saveMovie(NewMovieDto movie) {
        moviesRepository.save(new MovieEntity(
                null,
                movie.title(),
                movie.description(),
                movie.releaseDate(),
                movie.directorName(),
                null
        ));
    }

    @Override
    public void updateMovie(Movie newMovie) {
        moviesRepository.save(new MovieEntity(
                newMovie.id(),
                newMovie.title(),
                newMovie.description(),
                newMovie.releaseDate(),
                newMovie.directorName(),
                null
        ));
    }

    @Override
    public void deleteMovie(Movie oldMovie) {

    }
}
