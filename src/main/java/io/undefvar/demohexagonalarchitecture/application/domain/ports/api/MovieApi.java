package io.undefvar.demohexagonalarchitecture.application.domain.ports.api;

import io.undefvar.demohexagonalarchitecture.application.domain.core.models.Movie;

/*
 * In pachetul API definim modul in care actori externi(utilizatori sau alte sisteme) pot interactiona cu aplicatia noastra
 * Mai exact definim ce se poate face in aplicatie
 * */
public interface MovieApi {
    void saveMovie(Movie newMovie);
}
