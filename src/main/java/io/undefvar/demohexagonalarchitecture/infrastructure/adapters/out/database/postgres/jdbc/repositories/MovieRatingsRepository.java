package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.database.postgres.jdbc.repositories;

import io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.database.postgres.jdbc.entities.MovieRatingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface MovieRatingsRepository extends CrudRepository<MovieRatingEntity, Long> {
    Set<MovieRatingEntity> findByMovieId(Long movieId);

}
