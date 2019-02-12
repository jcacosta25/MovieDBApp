package io.jcal.movies_provider.repository.mapper

import io.jcal.movies_provider.repository.api.model.DatesDTO
import io.jcal.movies_provider.repository.api.model.EpisodeDTO
import io.jcal.movies_provider.repository.api.model.MovieDTO
import io.jcal.movies_provider.repository.api.model.MoviesDTO
import io.jcal.movies_provider.repository.api.model.SeasonDTO
import io.jcal.movies_provider.repository.api.model.TvShowDTO
import io.jcal.movies_provider.repository.api.model.TvShowsDTO
import io.jcal.movies_provider.repository.db.entity.EpisodeEntity
import io.jcal.movies_provider.repository.db.entity.MovieEntity
import io.jcal.movies_provider.repository.db.entity.SeasonEntity
import io.jcal.movies_provider.repository.db.entity.TvShowEntity
import io.jcal.movies_provider.repository.mapper.model.BaseModel
import io.jcal.movies_provider.repository.mapper.model.BaseModel.Companion.BASE_ERROR_CODE
import io.jcal.movies_provider.repository.mapper.model.BaseModel.Companion.LOADING
import io.jcal.movies_provider.repository.mapper.model.BaseModel.Companion.SUCCESS
import io.jcal.movies_provider.repository.mapper.model.DatesModel
import io.jcal.movies_provider.repository.mapper.model.EpisodeModel
import io.jcal.movies_provider.repository.mapper.model.MovieModel
import io.jcal.movies_provider.repository.mapper.model.MoviesModel
import io.jcal.movies_provider.repository.mapper.model.SeasonModel
import io.jcal.movies_provider.repository.mapper.model.TvShowModel
import io.jcal.movies_provider.repository.mapper.model.TvShowsModel
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun convert(response: MovieDTO): MovieModel {
        val model = MovieModel(
            id = response.id,
            adult = response.adult,
            backdropPath = IMAGE_PATH.plus(response.backdropPath),
            budget = response.budget,
            genreIds = response.genreIds,
            homepage = response.homepage,
            imdbId = response.imdbId,
            originalLanguage = response.originalLanguage,
            originalTitle = response.originalTitle,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = IMAGE_PATH.plus(response.posterPath),
            releaseDate = response.releaseDate,
            revenue = response.revenue,
            runtime = response.runtime,
            spokenLanguageModels = response.spokenLanguageModels.map { it.name },
            status = response.status,
            tagline = response.tagline,
            title = response.title,
            video = response.video,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
        model.state = SUCCESS
        return model
    }

    fun convert(entity: MovieEntity): MovieModel {
        val model = MovieModel(
            id = entity.id,
            adult = entity.adult,
            backdropPath = IMAGE_PATH.plus(entity.backdropPath),
            budget = entity.budget,
            genreIds = entity.genreIds,
            homepage = entity.homepage,
            imdbId = entity.imdbId,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = IMAGE_PATH.plus(entity.posterPath),
            releaseDate = entity.releaseDate,
            revenue = entity.revenue,
            runtime = entity.runtime,
            spokenLanguageModels = entity.spokenLanguageModels,
            status = entity.status,
            tagline = entity.tagline,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
        model.state = SUCCESS
        return model
    }

    fun convert(model: MovieModel): MovieEntity {
        return MovieEntity(
            id = model.id,
            adult = model.adult,
            backdropPath = model.backdropPath.replace(IMAGE_PATH, EMPTY),
            budget = model.budget,
            genreIds = model.genreIds,
            homepage = model.homepage,
            imdbId = model.imdbId,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath.replace(IMAGE_PATH, EMPTY),
            releaseDate = model.releaseDate,
            revenue = model.revenue,
            runtime = model.runtime,
            spokenLanguageModels = model.spokenLanguageModels,
            status = model.status,
            tagline = model.tagline,
            title = model.title,
            video = model.video,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }

    fun convert(response: EpisodeDTO): EpisodeModel {
        val model = EpisodeModel(
            id = response.id,
            airDate = response.airDate,
            episodeNumber = response.episodeNumber,
            name = response.name,
            overview = response.overview,
            productionCode = response.productionCode,
            seasonNumber = response.seasonNumber,
            showId = response.showId,
            stillPath = IMAGE_PATH.plus(response.stillPath),
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
        model.state = SUCCESS
        return model
    }

    fun convert(entity: EpisodeEntity): EpisodeModel {
        val model = EpisodeModel(
            id = entity.id,
            airDate = entity.airDate,
            episodeNumber = entity.episodeNumber,
            name = entity.name,
            overview = entity.overview,
            productionCode = entity.productionCode,
            seasonNumber = entity.seasonNumber,
            showId = entity.showId,
            stillPath = IMAGE_PATH.plus(entity.stillPath),
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount
        )
        model.state = SUCCESS
        return model
    }

    fun convert(model: EpisodeModel): EpisodeEntity {
        return EpisodeEntity(
            id = model.id,
            airDate = model.airDate,
            episodeNumber = model.episodeNumber,
            name = model.name,
            overview = model.overview,
            productionCode = model.productionCode,
            seasonNumber = model.seasonNumber,
            showId = model.showId,
            stillPath = model.stillPath.replace(IMAGE_PATH, EMPTY),
            voteAverage = model.voteAverage,
            voteCount = model.voteCount
        )
    }

    fun convert(response: SeasonDTO, showId: Int = response._id.toInt()): SeasonModel {
        val model = SeasonModel(
            id = response.id,
            airDate = response.airDate,
            episodeCount = response.episodeCount,
            name = response.name,
            overview = response.overview,
            posterPath = IMAGE_PATH.plus(response.posterPath),
            seasonNumber = response.seasonNumber,
            episodes = response.episodes.map { convert(it) },
            showId = showId
        )
        model.state = SUCCESS
        return model
    }

    fun convert(entity: SeasonEntity): SeasonModel {
        val model = SeasonModel(
            id = entity.id,
            airDate = entity.airDate,
            episodeCount = entity.episodeCount,
            name = entity.name,
            overview = entity.overview,
            posterPath = IMAGE_PATH.plus(entity.posterPath),
            seasonNumber = entity.seasonNumber,
            episodes = entity.episodes.map { convert(it) },
            showId = entity.showId
        )
        model.state = SUCCESS
        return model
    }

    fun convert(model: SeasonModel): SeasonEntity {
        return SeasonEntity(
            id = model.id,
            airDate = model.airDate,
            episodeCount = model.episodeCount,
            name = model.name,
            overview = model.overview,
            posterPath = model.posterPath.replace(IMAGE_PATH, EMPTY),
            seasonNumber = model.seasonNumber,
            episodes = model.episodes.map { convert(it) },
            showId = model.showId
        )
    }

    fun convert(response: TvShowDTO): TvShowModel {
        val model = TvShowModel(
            id = response.id,
            airDate = response.airDate,
            episodeCount = response.episodeCount,
            seasonNumber = response.seasonNumber,
            backdropPath = IMAGE_PATH.plus(response.backdropPath),
            episodeRunTime = response.episodeRunTime,
            firstAirDate = response.firstAirDate,
            homepage = response.homepage,
            inProduction = response.inProduction,
            languages = response.languages,
            lastAirDate = response.lastAirDate,
            lastEpisodeToAir = response.lastEpisodeToAir.id,
            name = response.name,
            nextEpisodeToAir = response.nextEpisodeToAir.id,
            numberOfEpisodes = response.numberOfEpisodes,
            numberOfSeasons = response.numberOfSeasons,
            originCountry = response.originCountry,
            originalLanguage = response.originalLanguage,
            originalName = response.originalName,
            overview = response.overview,
            popularity = response.popularity,
            posterPath = IMAGE_PATH.plus(response.posterPath),
            seasons = response.seasons.map { convert(it) },
            status = response.status,
            type = response.type,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
            genreIds = response.genreIds
        )
        model.state = SUCCESS
        return model
    }

    fun convert(entity: TvShowEntity): TvShowModel {
        val model = TvShowModel(
            id = entity.id,
            airDate = entity.airDate,
            episodeCount = entity.episodeCount,
            seasonNumber = entity.seasonNumber,
            backdropPath = IMAGE_PATH.plus(entity.backdropPath),
            episodeRunTime = entity.episodeRunTime,
            firstAirDate = entity.firstAirDate,
            homepage = entity.homepage,
            inProduction = entity.inProduction,
            languages = entity.languages,
            lastAirDate = entity.lastAirDate,
            lastEpisodeToAir = entity.lastEpisodeToAir,
            name = entity.name,
            nextEpisodeToAir = entity.nextEpisodeToAir,
            numberOfEpisodes = entity.numberOfEpisodes,
            numberOfSeasons = entity.numberOfSeasons,
            originCountry = entity.originCountry,
            originalLanguage = entity.originalLanguage,
            originalName = entity.originalName,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = IMAGE_PATH.plus(entity.posterPath),
            seasons = entity.seasons.map { convert(it) },
            status = entity.status,
            type = entity.type,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            genreIds = entity.genreIds
        )
        model.state = SUCCESS
        return model
    }

    fun convert(model: TvShowModel): TvShowEntity {
        return TvShowEntity(
            id = model.id,
            airDate = model.airDate,
            episodeCount = model.episodeCount,
            seasonNumber = model.seasonNumber,
            backdropPath = model.backdropPath.replace(IMAGE_PATH, EMPTY),
            episodeRunTime = model.episodeRunTime,
            firstAirDate = model.firstAirDate,
            homepage = model.homepage,
            inProduction = model.inProduction,
            languages = model.languages,
            lastAirDate = model.lastAirDate,
            lastEpisodeToAir = model.lastEpisodeToAir,
            name = model.name,
            nextEpisodeToAir = model.nextEpisodeToAir,
            numberOfEpisodes = model.numberOfEpisodes,
            numberOfSeasons = model.numberOfSeasons,
            originCountry = model.originCountry,
            originalLanguage = model.originalLanguage,
            originalName = model.originalName,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath.replace(IMAGE_PATH, EMPTY),
            seasons = model.seasons.map { convert(it) },
            status = model.status,
            type = model.type,
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
            genreIds = model.genreIds
        )
    }

    fun convert(response: DatesDTO): DatesModel = DatesModel(response.maximum, response.minimum)

    fun convert(response: MoviesDTO): MoviesModel {
        val model = MoviesModel(
            dates = convert(response.dates),
            page = response.page,
            results = response.results.map { convert(it) },
            totalPages = response.totalPages,
            totalResult = response.totalResults
        )
        model.state = SUCCESS
        return model
    }

    fun convert(movies: List<MovieEntity>): MoviesModel {
        val model = MoviesModel(
            results = movies.map { convert(it) }
        )
        model.state = SUCCESS
        return model
    }

    fun convert(tvShows: List<TvShowEntity>): TvShowsModel {
        val model = TvShowsModel(
            results = tvShows.map { convert(it) }
        )
        model.state = SUCCESS
        return model
    }

    fun convert(response: TvShowsDTO): TvShowsModel {
        val model = TvShowsModel(
            dates = convert(response.dates),
            page = response.page,
            results = response.results.map { convert(it) },
            totalPages = response.totalPages,
            totalResult = response.totalResults
        )
        model.state = SUCCESS
        return model
    }

    fun <T : BaseModel> createDomainModel(
        errorCode: Int = 0,
        clazz: Class<T>,
        state: String = LOADING
    ): T {
        val model = clazz.newInstance()
        if (errorCode != BASE_ERROR_CODE) {
            model.setError(errorCode)
        } else {
            model.state = state
        }
        return model
    }

    /**
     * Shallow copy of base class [BaseModel]
     *
     * @param [BaseModel] base class
     * @return [T] sub class
     */
    fun <T : BaseModel> T.baseCopy(base: BaseModel): T {
        state = base.state
        error = base.error
        errorCode = base.errorCode
        return this
    }

    companion object {
        private const val EMPTY = ""
        private const val IMAGE_PATH = "https://image.tmdb.org/t/p/original"
    }
}