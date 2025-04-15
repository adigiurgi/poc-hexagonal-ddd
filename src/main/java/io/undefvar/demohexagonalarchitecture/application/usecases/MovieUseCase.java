package io.undefvar.demohexagonalarchitecture.application.usecases;

import io.undefvar.demohexagonalarchitecture.application.domain.dao.MovieDao;
import io.undefvar.demohexagonalarchitecture.application.domain.service.MovieIntegrityValidationService;
import io.undefvar.demohexagonalarchitecture.application.domain.service.OtherBussinesLogicUsefullAcrossUseCases;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command.NewMovieDto;
import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;
import io.undefvar.demohexagonalarchitecture.application.domain.exceptions.MovieAlreadyExistsException;
import io.undefvar.demohexagonalarchitecture.application.domain.exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


/*
Clasele de acest tip din pachetul usecases sunt o transpunere a cerintelor functionale rezultate din documentul de analiza de bussines
Sunt singurul tip de clasa din pachetul application in care (in mod conventional) putem folosi dependinte externe (adnotari specifice framework-ului Spring, Lombok, etc.)
Un alt scopul al acestor tipuri de clase este si acela de a decupla codul din pachetul de domain de dependinte externe.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class MovieUseCase {
    private final MovieDao movieDAO;

    public String saveMovie(NewMovieDto newMovieDto) throws MovieAlreadyExistsException {

        //check if movie is already in DB
        var isPresent = movieDAO.findMovieByTitle(newMovieDto.title()).isPresent();
        if (isPresent) {
            throw new MovieAlreadyExistsException("Movies already exist");
        }

        //instantiam un obiect de tip Movie (domeniu) pentru a forta constrangerile de business
        Movie movie = new Movie(null, newMovieDto.title(),
                newMovieDto.description(), newMovieDto.releaseDate(),
                newMovieDto.directorName(), Set.of());

        //check aggregate integrity
        MovieIntegrityValidationService.validateIntegrity(movie);

        //check if other business logic is valid
        OtherBussinesLogicUsefullAcrossUseCases.someBussinesLogic(movie);

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
