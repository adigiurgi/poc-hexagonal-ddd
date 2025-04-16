package io.undefvar.demohexagonalarchitecture.application.domain.ports.dao;

import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command.NewMovieDto;
import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;

import java.util.List;
import java.util.Optional;

/*
* In pachetul SPI definim modul in care aplicatia noastra interactioneaza cu serviciile exterioare
* Paote fi un DAO sau un contract pentru definirea modului de lucru cu un message broker de exemplu
* Implementarea efectiva o vom gasi in afara domeniului in adaptorul de out din pachetul de infrastructura
* */
public interface MovieDaoPort {
    Optional<Movie> findMovieByTitle(String title);
    List<Movie> findAllMoviesWithRatings();
    List<Movie> findAllMoviesWithoutRatings();
    void saveMovie(NewMovieDto movie);
    void updateMovie(Movie newMovie);
    void deleteMovie(Movie oldMovie);
}
