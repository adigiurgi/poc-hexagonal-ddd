package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web;

import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.in.web.dtos.command.NewMovieDto;
import io.undefvar.demohexagonalarchitecture.application.usecases.MovieUseCase;
import io.undefvar.demohexagonalarchitecture.application.domain.core.models.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesController {
    private final MovieUseCase movieUseCase;

    @GetMapping
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(movieUseCase.getAllMoviesWithoutRatings());
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(movieUseCase.getMovieByTitle(title));
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody NewMovieDto newMovieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieUseCase.saveMovie(newMovieDto));
    }

    @PutMapping
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieUseCase.updateMovie(movie));
    }

    //delete I leave it for you to practice lol


}
