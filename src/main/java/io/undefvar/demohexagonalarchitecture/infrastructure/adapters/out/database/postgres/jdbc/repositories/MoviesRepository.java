package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.database.postgres.jdbc.repositories;

import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.database.postgres.jdbc.entities.MovieEntity;
import io.undefvar.demohexagonalarchitecture.application.domain.models.Movie;
import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.database.postgres.jdbc.entities.MovieRatingEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MoviesRepository extends CrudRepository<MovieEntity, Long> {

    @Query("select * from movies where title =:title")
    Optional<Movie> findMovieByTitle(@Param("title") String title);

}
