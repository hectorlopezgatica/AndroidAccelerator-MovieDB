package com.hlopezg.data_local.source

import com.hlopezg.data_local.genre.GenreDao
import com.hlopezg.data_local.mapper.toGenre
import com.hlopezg.data_local.mapper.toGenreEntity
import com.hlopezg.data_local.mapper.toTv
import com.hlopezg.data_local.tv.TvDao
import com.hlopezg.data_repository.data_source.local.LocalGenreDataSource
import com.hlopezg.data_repository.data_source.local.LocalTvDataSource
import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Tv
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalGenreDataSourceImpl @Inject constructor(
    private val genreDao: GenreDao,
): LocalGenreDataSource {
    override fun getGenres(): Flow<List<Genre>> = genreDao.getGenres().map { tvListEntity ->
        tvListEntity.map { tvEntity ->
            tvEntity.toGenre()
        }
    }

    override fun getGenre(id: Long): Flow<Genre> = genreDao.getGenre(id).map { genreEntity ->
        genreEntity.toGenre()
    }

    override suspend fun save(genres: List<Genre>, movieId: Long) = genreDao.insertGenres(genres.map { it.toGenreEntity(movieId) })


}