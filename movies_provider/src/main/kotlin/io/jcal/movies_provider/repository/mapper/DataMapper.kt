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
import io.jcal.movies_provider.repository.db.entity.SeasonEpisodes
import io.jcal.movies_provider.repository.db.entity.TvShowEntity
import io.jcal.movies_provider.repository.db.entity.TvShowSeasons
import io.jcal.movies_provider.repository.mapper.model.BaseModel
import io.jcal.movies_provider.repository.mapper.model.BaseModel.Companion.BASE_ERROR_CODE
import io.jcal.movies_provider.repository.mapper.model.BaseModel.Companion.LOADING
import io.jcal.movies_provider.repository.mapper.model.BaseModel.Companion.NOT_EXISTING_VALUE
import io.jcal.movies_provider.repository.mapper.model.BaseModel.Companion.PARSING_ERROR
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

    fun convert(response: MovieDTO?): MovieModel =
        MovieModel(
            id = emptyInt(response?.id),
            adult = emptyBoolean(response?.adult),
            backdropPath = emptyString(response?.backdropPath?.let { IMAGE_PATH.plus(it) }),
            budget = emptyInt(response?.budget),
            genreIds = emptyList(response?.genreIds ?: response?.genres?.map { it.id }),
            homepage = emptyString(response?.homepage),
            imdbId = emptyString(response?.imdbId),
            originalLanguage = emptyString(response?.originalLanguage),
            originalTitle = emptyString(response?.originalTitle),
            overview = emptyString(response?.overview),
            popularity = emptyDouble(response?.popularity),
            posterPath = emptyString(response?.posterPath?.let { IMAGE_PATH.plus(it) }),
            releaseDate = emptyString(response?.releaseDate),
            revenue = emptyInt(response?.revenue),
            runtime = emptyInt(response?.runtime),
            // spokenLanguageModels = response?.spokenLanguageModels.map { it.name },
            status = emptyString(response?.status),
            tagline = emptyString(response?.tagline),
            title = emptyString(response?.title),
            video = emptyBoolean(response?.video),
            voteAverage = emptyDouble(response?.voteAverage),
            voteCount = emptyInt(response?.voteCount)
        ).apply {
            if (response == null) {
                setError(PARSING_ERROR)
            } else {
                setSuccess()
            }
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
            backdropPath = model.backdropPath.replace(IMAGE_PATH, EMPTY_STRING),
            budget = model.budget,
            genreIds = model.genreIds,
            homepage = model.homepage,
            imdbId = model.imdbId,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            popularity = model.popularity,
            posterPath = model.posterPath.replace(IMAGE_PATH, EMPTY_STRING),
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
            airDate = emptyString(response.airDate),
            episodeNumber = response.episodeNumber,
            name = emptyString(response.name),
            overview = emptyString(response.overview),
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

    fun convert(model: EpisodeModel, seasonId: Int): EpisodeEntity {
        return EpisodeEntity(
            id = model.id,
            airDate = model.airDate,
            episodeNumber = model.episodeNumber,
            name = model.name,
            overview = model.overview,
            productionCode = model.productionCode,
            seasonNumber = model.seasonNumber,
            showId = model.showId,
            stillPath = model.stillPath.replace(IMAGE_PATH, EMPTY_STRING),
            voteAverage = model.voteAverage,
            voteCount = model.voteCount,
            seasonId = seasonId
        )
    }

    fun convert(response: SeasonDTO, showId: Int = response._id.toInt()): SeasonModel {
        val model = SeasonModel(
            id = response.id,
            airDate = emptyString(response.airDate),
            episodeCount = response.episodeCount,
            name = emptyString(response.name),
            overview = emptyString(response.overview),
            posterPath = IMAGE_PATH.plus(response.posterPath),
            seasonNumber = response.seasonNumber,
            episodes = if (response.episodes == null) listOf() else response.episodes.map {
                convert(
                    it
                )
            },
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
            showId = entity.showId
        )
        model.state = SUCCESS
        return model
    }

    fun convert(entity: SeasonEpisodes): SeasonModel {
        val model = SeasonModel(
            id = entity.season.id,
            airDate = entity.season.airDate,
            episodeCount = entity.season.episodeCount,
            name = entity.season.name,
            overview = entity.season.overview,
            posterPath = IMAGE_PATH.plus(entity.season.posterPath),
            seasonNumber = entity.season.seasonNumber,
            episodes = entity.episodes.map { convert(it) },
            showId = entity.season.showId
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
            posterPath = model.posterPath.replace(IMAGE_PATH, EMPTY_STRING),
            seasonNumber = model.seasonNumber,
            showId = model.showId
        )
    }

    fun convertModel(model: SeasonModel): SeasonEpisodes {
        return SeasonEpisodes(
            season = SeasonEntity(
                id = model.id,
                airDate = model.airDate,
                episodeCount = model.episodeCount,
                name = model.name,
                overview = model.overview,
                posterPath = model.posterPath.replace(IMAGE_PATH, EMPTY_STRING),
                seasonNumber = model.seasonNumber,
                showId = model.showId
            ),
            episodes = model.episodes.map { convert(it, model.id) }
        )
    }

    fun convert(response: TvShowDTO?): TvShowModel =
        TvShowModel(
            id = emptyInt(response?.id),
            airDate = emptyString(response?.airDate),
            episodeCount = emptyInt(response?.episodeCount),
            seasonNumber = emptyInt(response?.seasonNumber),
            backdropPath = emptyString(response?.backdropPath?.let { IMAGE_PATH.plus(it) }),
            episodeRunTime = emptyList(response?.episodeRunTime),
            firstAirDate = emptyString(response?.firstAirDate),
            homepage = emptyString(response?.homepage),
            inProduction = emptyBoolean(response?.inProduction),
            languages = emptyList(response?.languages),
            lastAirDate = emptyString(response?.lastAirDate),
            lastEpisodeToAir = emptyInt(response?.lastEpisodeToAir?.id),
            nextEpisodeToAir = emptyInt(response?.nextEpisodeToAir?.id),
            numberOfEpisodes = emptyInt(response?.numberOfEpisodes),
            name = emptyString(response?.name),
            numberOfSeasons = emptyInt(response?.numberOfSeasons),
            originCountry = emptyList(response?.originCountry),
            originalLanguage = emptyString(response?.originalLanguage),
            originalName = emptyString(response?.originalName),
            overview = emptyString(response?.overview),
            popularity = emptyDouble(response?.popularity),
            posterPath = emptyString(response?.posterPath?.let { IMAGE_PATH.plus(it) }),
            seasons = emptyList(response?.seasons?.map { convert(it, response.id) }),
            status = emptyString(response?.status),
            type = emptyString(response?.type),
            voteAverage = emptyDouble(response?.voteAverage),
            voteCount = emptyInt(response?.voteCount),
            genreIds = emptyList(response?.genreIds)
        ).apply {
            if (response == null) {
                setError(PARSING_ERROR)
            } else {
                setSuccess()
            }
        }

    fun convert(entity: TvShowSeasons): TvShowModel {
        val model = TvShowModel(
            id = entity.tvShowEntity.id,
            airDate = entity.tvShowEntity.airDate,
            episodeCount = entity.tvShowEntity.episodeCount,
            seasonNumber = entity.tvShowEntity.seasonNumber,
            backdropPath = IMAGE_PATH.plus(entity.tvShowEntity.backdropPath),
            episodeRunTime = entity.tvShowEntity.episodeRunTime,
            firstAirDate = entity.tvShowEntity.firstAirDate,
            homepage = entity.tvShowEntity.homepage,
            inProduction = entity.tvShowEntity.inProduction,
            languages = entity.tvShowEntity.languages,
            lastAirDate = entity.tvShowEntity.lastAirDate,
            lastEpisodeToAir = entity.tvShowEntity.lastEpisodeToAir,
            name = entity.tvShowEntity.name,
            nextEpisodeToAir = entity.tvShowEntity.nextEpisodeToAir,
            numberOfEpisodes = entity.tvShowEntity.numberOfEpisodes,
            numberOfSeasons = entity.tvShowEntity.numberOfSeasons,
            originCountry = entity.tvShowEntity.originCountry,
            originalLanguage = entity.tvShowEntity.originalLanguage,
            originalName = entity.tvShowEntity.originalName,
            overview = entity.tvShowEntity.overview,
            popularity = entity.tvShowEntity.popularity,
            posterPath = IMAGE_PATH.plus(entity.tvShowEntity.posterPath),
            seasons = entity.seasons.map { convert(it) },
            status = entity.tvShowEntity.status,
            type = entity.tvShowEntity.type,
            voteAverage = entity.tvShowEntity.voteAverage,
            voteCount = entity.tvShowEntity.voteCount,
            genreIds = entity.tvShowEntity.genreIds
        )
        model.state = SUCCESS
        return model
    }

    fun convert(model: TvShowModel): TvShowSeasons {
        return TvShowSeasons(
            tvShowEntity = TvShowEntity(
                id = model.id,
                airDate = model.airDate,
                episodeCount = model.episodeCount,
                seasonNumber = model.seasonNumber,
                backdropPath = model.backdropPath.replace(IMAGE_PATH, EMPTY_STRING),
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
                posterPath = model.posterPath.replace(IMAGE_PATH, EMPTY_STRING),
                status = model.status,
                type = model.type,
                voteAverage = model.voteAverage,
                voteCount = model.voteCount,
                genreIds = model.genreIds
            ),
            seasons = model.seasons.map { convert(it) }
        )
    }

    fun convert(response: DatesDTO?): DatesModel =
        DatesModel(emptyString(response?.maximum), emptyString(response?.minimum))

    fun convert(response: MoviesDTO?): MoviesModel {
        if (response == null) {
            return MoviesModel().apply { setError(PARSING_ERROR) }
        } else {
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
    }

    fun convert(movies: List<MovieEntity>?): MoviesModel =
        MoviesModel(
            results = movies?.map { convert(it) } ?: listOf()
        ).apply {
            if (movies == null) {
                setError(NOT_EXISTING_VALUE)
            } else {
                setSuccess()
            }
        }

    fun convert(tvShows: List<TvShowSeasons>): TvShowsModel {
        val model = TvShowsModel(
            results = tvShows.map { convert(it) }
        )
        model.state = SUCCESS
        return model
    }

    fun convert(entity: List<TvShowModel>): List<TvShowSeasons> =
        entity.map { convert(it) }

    fun convert(response: TvShowsDTO?): TvShowsModel =
        TvShowsModel(
            dates = convert(response?.dates),
            page = emptyInt(response?.page),
            results = emptyList(response?.results?.map { convert(it) }),
            totalPages = emptyInt(response?.totalPages),
            totalResult = emptyInt(response?.totalResults)
        ).apply {
            if (response == null) {
                setError(PARSING_ERROR)
            } else {
                setSuccess()
            }
        }

    fun <T : BaseModel> createDomainModel(
        errorCode: Int? = BASE_ERROR_CODE,
        clazz: Class<T>,
        state: String = LOADING
    ): T {
        val model = clazz.newInstance()
        if (errorCode != BASE_ERROR_CODE) {
            model.setError(errorCode ?: BASE_ERROR_CODE)
        } else {
            model.state = state
        }
        return model
    }

    /**
     * Shallow copy of base class [BaseModel]
     *
     * @param [base] base class type [BaseModel]
     * @return [T] sub class
     */
    fun <T : BaseModel> T.baseCopy(base: BaseModel): T {
        state = base.state
        error = base.error
        errorCode = base.errorCode
        return this
    }

    private fun emptyString(string: String?): String = string ?: EMPTY_STRING

    private fun <T> emptyList(list: List<T>?): List<T> = list ?: listOf()

    private fun emptyInt(int: Int?): Int = int ?: EMPTY_INT

    private fun emptyBoolean(boolean: Boolean?): Boolean = boolean ?: EMPTY_BOOLEAN

    private fun emptyDouble(double: Double?): Double = double ?: EMPTY_DOUBLE

    companion object {
        private const val EMPTY_STRING = ""
        private const val EMPTY_INT = 0
        private const val EMPTY_BOOLEAN = false
        private const val EMPTY_DOUBLE = 0.0
        private const val IMAGE_PATH = "https://image.tmdb.org/t/p/original"
    }
}
