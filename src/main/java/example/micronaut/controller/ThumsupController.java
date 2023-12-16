package example.micronaut.controller;

import example.micronaut.model.Genre;
import example.micronaut.model.Thumsup;
import example.micronaut.repository.GenreRepository;
import example.micronaut.repository.ThumsupRepository;
import io.micronaut.data.exceptions.DataAccessException;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/thumsups")
public class ThumsupController {

    protected final ThumsupRepository thumsupRepository;

    public ThumsupController(ThumsupRepository thumsupRepository) {
        this.thumsupRepository = thumsupRepository;
    }

    @Get("/list")
    public List<Thumsup> list(Pageable pageable) {
        return thumsupRepository.findAll(pageable).getContent();
    }

    @Get("/{id}")
    public Optional<Thumsup> show(Long id) {
        return thumsupRepository.findById(id);
    }

    @Post
    public HttpResponse<Thumsup> save(@Body Thumsup command) {
        Thumsup genre = thumsupRepository.save(command);

        return HttpResponse
                .created(genre)
                .headers(headers -> headers.location(location(genre.getId())));
    }

    @Put
    public HttpResponse update(@Body Thumsup command) {
        thumsupRepository.update(command);
        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void delete(Long id) {
        thumsupRepository.deleteById(id);
    }

    protected URI location(Long id) {
        return URI.create("/genres/" + id);
    }

    protected URI location(Genre genre) {
        return location(genre.getId());
    }
}
