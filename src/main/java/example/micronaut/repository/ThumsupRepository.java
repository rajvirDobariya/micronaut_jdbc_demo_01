package example.micronaut.repository;

import example.micronaut.model.Thumsup;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JdbcRepository(dialect = Dialect.MYSQL) // <1>
public interface ThumsupRepository extends PageableRepository<Thumsup, Long> { // <2>

    Thumsup save(Thumsup thumsup);

    Thumsup update(Thumsup thumsup);
}
