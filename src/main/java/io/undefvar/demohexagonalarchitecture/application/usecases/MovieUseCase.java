package io.undefvar.demohexagonalarchitecture.application.usecases;

import io.undefvar.demohexagonalarchitecture.application.domain.ports.api.MovieApi;
import io.undefvar.demohexagonalarchitecture.application.domain.ports.spi.MovieDao;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command.NewMovieDto;
import io.undefvar.demohexagonalarchitecture.application.domain.core.models.Movie;
import io.undefvar.demohexagonalarchitecture.application.domain.exceptions.MovieAlreadyExistsException;
import io.undefvar.demohexagonalarchitecture.application.domain.exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/*
Clasele de acest tip din pachetul usecases sunt o transpunere a cerintelor functionale rezultate din documentul de analiza de bussines
Sunt singurul tip de clasa din pachetul application in care (in mod conventional) putem folosi dependinte externe (adnotari specifice framework-ului Spring, lombok, etc.)
Scopul principal al acestor tipuri de clase este acela de a decupla codul din pachetul de domain de dependinte externe
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class MovieUseCase {
    private final MovieDao movieDAO;
    private final MovieApi movieApi;

    public String saveMovie(NewMovieDto newMovieDto) throws MovieAlreadyExistsException {
        //check if movie is already in DB
        var isPresent = movieDAO.findMovieByTitle(newMovieDto.title()).isPresent();
        if (isPresent) {
            throw new MovieAlreadyExistsException("Movies already exist");
        }
        Movie movie = new Movie(null, newMovieDto.title(),
                newMovieDto.description(), newMovieDto.releaseDate(),
                newMovieDto.directorName(), Set.of());
        movieApi.saveMovie(movie);

        // continue to save movie
        movieDAO.saveMovie(newMovieDto);

        return "Movie Saved Successfully";
    }

    public List<Movie> getAllMoviesWithoutRatings() {
        return movieDAO.findAllMoviesWithoutRatings();
    }

    public String updateMovie(Movie movie) throws MovieNotFoundException {
        //check if movie is already in DB
        var isPresent = movieDAO.findMovieByTitle(movie.title()).isPresent();
        if (!isPresent) {
            throw new MovieNotFoundException("This movie does not exist");
        }
        movieDAO.updateMovie(movie);
        return "Movie Successfully updated";
    }

    public Movie getMovieByTitle(String movieTitle) {
        return movieDAO.findMovieByTitle(movieTitle).orElseThrow(
                () -> new MovieNotFoundException("This movie does not exist")
        );
    }


}
