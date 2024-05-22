package com.hlopezg.data_repository.repository

import com.hlopezg.data_repository.data_source.local.LocalGenreDataSource
import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow

class GenreRepositoryImpl(
    private val localGenreDataSource: LocalGenreDataSource,
) : GenreRepository {
    override fun getGenreList(): Flow<List<Genre>> =
        localGenreDataSource.getGenres()

    override fun getGenre(id: Long): Flow<Genre> = localGenreDataSource.getGenre(id)
}