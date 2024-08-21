package com.fullcycle.admin.catalogo.domain.genre;

import com.fullcycle.admin.catalogo.domain.pagination.Pagination;
import com.fullcycle.admin.catalogo.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface GenreGateway {

    Genre create(Genre genre);

    void deleteById(GenreID genreID);

    Optional<Genre> findById(GenreID genreID);

    Genre update(Genre genre);

    Pagination<Genre> findAll(SearchQuery searchQuery);

    List<GenreID> existsByIds(Iterable<GenreID> ids);

}
