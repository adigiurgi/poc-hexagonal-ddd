package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.postgresJDBC.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "movie_ratings")
public record MovieRatingEntity (
    @Id
    Long id,
    String comment,
    Integer starRating,
    @Column("movie_id")
    Long movieId,
    @Version
    Integer version
) {}
