package io.undefvar.demohexagonalarchitecture.application.usecases;

import io.undefvar.demohexagonalarchitecture.application.domain.ports.dao.MovieDaoPort;
import io.undefvar.demohexagonalarchitecture.application.domain.service.MovieIntegrityValidationService;
import io.undefvar.demohexagonalarchitecture.application.domain.service.OtherBussinesLogicUsefullAcrossUseCases;
import io.undefvar.demohexagonalarchitecture.application.domain.service.api.MovieIntegrityValidationApi;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command.NewMovieDto;
import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;
import io.undefvar.demohexagonalarchitecture.application.domain.exceptions.MovieAlreadyExistsException;
import io.undefvar.demohexagonalarchitecture.application.domain.exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/*
Clasele de acest tip din pachetul usecases sunt o transpunere a cerintelor functionale rezultate din documentul de analiza de bussines
Sunt singurul tip de clasa din pachetul application in care (in mod conventional) putem folosi dependinte externe (adnotari specifice framework-ului Spring, Lombok, etc.)
Un alt scopul al acestor tipuri de clase este si acela de a decupla codul din pachetul de domain de dependinte externe.
 */

@Service
@Slf4j
public class MovieUseCase {

    MovieDaoPort movieDaoPort;

    public MovieUseCase(@Qualifier("implementation_v1") MovieDaoPort movieDaoPort) {
        this.movieDaoPort = movieDaoPort;
    }

    public String saveMovie(NewMovieDto newMovieDto) throws MovieAlreadyExistsException {
        //convert DTO to domain model to enforce entity integrity checks
        var movie = new Movie(
                null,
                newMovieDto.title(),
                newMovieDto.description(),
                newMovieDto.releaseDate(),
                newMovieDto.directorName(),
                Set.of()
        );
        //check if movie is already in DB
        var isPresent = movieDaoPort.findMovieByTitle(newMovieDto.title()).isPresent();
        if (isPresent) {
            throw new MovieAlreadyExistsException("Movies already exist");
        }

        //check aggregate integrity

        MovieIntegrityValidationApi movieIntegrityValidationApi = new MovieIntegrityValidationService();
        movieIntegrityValidationApi.validateIntegrity(movie);

        //check if other business logic is valid
        if(OtherBussinesLogicUsefullAcrossUseCases.someOtherBussinesLogicChecks(movie)) {
            log.info("Some other business logic checks checked out");
            OtherBussinesLogicUsefullAcrossUseCases.someOtherBussinesLogic(movie);
        }

        // continue to save movie
        movieDaoPort.saveMovie(newMovieDto);

        return "Movie Saved Successfully";
    }

    public List<Movie> getAllMoviesWithoutRatings() {
        return movieDaoPort.findAllMoviesWithoutRatings();
    }

    public String updateMovie(Movie movie) throws MovieNotFoundException {
        //check if movie is already in DB
        var isPresent = movieDaoPort.findMovieByTitle(movie.title()).isPresent();
        if (!isPresent) {
            throw new MovieNotFoundException("This movie does not exist");
        }
        movieDaoPort.updateMovie(movie);
        return "Movie Successfully updated";
    }

    public Movie getMovieByTitle(String movieTitle) {
        return movieDaoPort.findMovieByTitle(movieTitle).orElseThrow(
                () -> new MovieNotFoundException("This movie does not exist")
        );
    }


}
