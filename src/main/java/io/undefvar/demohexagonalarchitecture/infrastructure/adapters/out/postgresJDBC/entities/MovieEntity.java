package io.undefvar.demohexagonalarchitecture.infrastructure.adapters.out.postgresJDBC.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Table(name = "movies")
public record MovieEntity(
        @Id
        Long id,
        String title,
        String description,
        LocalDate releaseDate,
        String directorName,
        @Version
        Integer version
) {
}
