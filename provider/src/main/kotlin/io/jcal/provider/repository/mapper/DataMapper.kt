package io.jcal.provider.repository.mapper

import io.jcal.provider.repository.api.model.*
import io.jcal.provider.repository.db.entity.*
import io.jcal.provider.repository.mapper.model.*
import io.jcal.provider.repository.mapper.model.BaseModel.Companion.BASE_ERROR_CODE
import io.jcal.provider.repository.mapper.model.BaseModel.Companion.LOADING
import io.jcal.provider.repository.mapper.model.BaseModel.Companion.NOT_EXISTING_VALUE
import io.jcal.provider.repository.mapper.model.BaseModel.Companion.PARSING_ERROR
import io.jcal.provider.repository.mapper.model.BaseModel.Companion.SUCCESS
import javax.inject.Inject

class DataMapper @Inject constructor() {
	
	fun convert(response: MovieDTO?): MovieModel =
		MovieModel(
			id = response?.id.orEmpty(),
			adult = response?.adult.orFalse(),
			backdropPath = response?.backdropPath?.let { IMAGE_PATH.plus(it) }.orEmpty(),
			budget = response?.budget.orEmpty(),
			genreIds = response?.genreIds ?: response?.genres?.map { it.id }.orEmpty(),
			homepage = response?.homepage.orEmpty(),
			imdbId = response?.imdbId.orEmpty(),
			originalLanguage = response?.originalLanguage.orEmpty(),
			originalTitle = response?.originalTitle.orEmpty(),
			overview = response?.overview.orEmpty(),
			popularity = response?.popularity.orEmpty(),
			posterPath = response?.posterPath?.let { IMAGE_PATH.plus(it) }.orEmpty(),
			releaseDate = response?.releaseDate.orEmpty(),
			revenue = response?.revenue.orEmpty(),
			runtime = response?.runtime.orEmpty(),
			spokenLanguageModels = response?.spokenLanguageModels?.map { it.name }.orEmpty(),
			status = response?.status.orEmpty(),
			tagline = response?.tagline.orEmpty(),
			title = response?.title.orEmpty(),
			video = response?.video.orFalse(),
			voteAverage = response?.voteAverage.orEmpty(),
			voteCount = response?.voteCount.orEmpty()
		).apply {
			if (response == null) {
				setError(PARSING_ERROR)
			} else {
				setSuccess()
			}
		}
	
	fun convert(entity: MovieEntity?): MovieModel = MovieModel(
		id = entity?.id.orEmpty(),
		adult = entity?.adult.orFalse(),
		backdropPath = entity?.backdropPath?.let { IMAGE_PATH.plus(it) }.orEmpty(),
		budget = entity?.budget.orEmpty(),
		genreIds = entity?.genreIds.orEmpty(),
		homepage = entity?.homepage.orEmpty(),
		imdbId = entity?.imdbId.orEmpty(),
		originalLanguage = entity?.originalLanguage.orEmpty(),
		originalTitle = entity?.originalTitle.orEmpty(),
		overview = entity?.overview.orEmpty(),
		popularity = entity?.popularity.orEmpty(),
		posterPath = entity?.posterPath?.let { IMAGE_PATH.plus(entity.posterPath) }.orEmpty(),
		releaseDate = entity?.releaseDate.orEmpty(),
		revenue = entity?.revenue.orEmpty(),
		runtime = entity?.runtime.orEmpty(),
		spokenLanguageModels = entity?.spokenLanguageModels.orEmpty(),
		status = entity?.status.orEmpty(),
		tagline = entity?.tagline.orEmpty(),
		title = entity?.title.orEmpty(),
		video = entity?.video.orFalse(),
		voteAverage = entity?.voteAverage.orEmpty(),
		voteCount = entity?.voteCount.orEmpty()
	).apply {
		if (entity == null) {
			setError(PARSING_ERROR)
		} else {
			setSuccess()
		}
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
			airDate = entity.airDate.orEmpty(),
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
	
	fun convert(response: SeasonDTO?, showId: Int = response?._id.orEmpty().toInt()): SeasonModel {
		val model = SeasonModel(
			id = response?.id.orEmpty(),
			airDate = response?.airDate.orEmpty(),
			episodeCount = response?.episodeCount.orEmpty(),
			name = response?.name.orEmpty(),
			overview = response?.overview.orEmpty(),
			posterPath = response?.posterPath?.let { IMAGE_PATH.plus(it) }.orEmpty(),
			seasonNumber = response?.seasonNumber.orEmpty(),
			episodes = response?.episodes?.map { convert(it) }.orEmpty(),
			showId = showId
		)
		model.state = SUCCESS
		return model
	}
	
	fun convert(entity: SeasonEntity): SeasonModel {
		val model = SeasonModel(
			id = entity.id.orEmpty(),
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
			id = entity.season.id.orEmpty(),
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
			id = response?.id.orEmpty(),
			airDate = response?.airDate.orEmpty(),
			episodeCount = response?.episodeCount.orEmpty(),
			seasonNumber = response?.seasonNumber.orEmpty(),
			backdropPath = response?.backdropPath?.let { IMAGE_PATH.plus(it) }.orEmpty(),
			episodeRunTime = response?.episodeRunTime.orEmpty(),
			firstAirDate = response?.firstAirDate.orEmpty(),
			homepage = response?.homepage.orEmpty(),
			inProduction = response?.inProduction.orFalse(),
			languages = response?.languages.orEmpty(),
			lastAirDate = response?.lastAirDate.orEmpty(),
			lastEpisodeToAir = response?.lastEpisodeToAir?.id.orEmpty(),
			nextEpisodeToAir = response?.nextEpisodeToAir?.id.orEmpty(),
			numberOfEpisodes = response?.numberOfEpisodes.orEmpty(),
			name = response?.name.orEmpty(),
			numberOfSeasons = response?.numberOfSeasons.orEmpty(),
			originCountry = response?.originCountry.orEmpty(),
			originalLanguage = response?.originalLanguage.orEmpty(),
			originalName = response?.originalName.orEmpty(),
			overview = response?.overview.orEmpty(),
			popularity = response?.popularity.orEmpty(),
			posterPath = response?.posterPath?.let { IMAGE_PATH.plus(it) }.orEmpty(),
			seasons = response?.seasons?.map { convert(it, response.id) }.orEmpty(),
			status = response?.status.orEmpty(),
			type = response?.type.orEmpty(),
			voteAverage = response?.voteAverage.orEmpty(),
			voteCount = response?.voteCount.orEmpty(),
			genreIds = response?.genreIds.orEmpty()
		).apply {
			if (response == null) {
				setError(PARSING_ERROR)
			} else {
				setSuccess()
			}
		}
	
	fun convert(entity: TvShowSeasons?): TvShowModel {
		val model = TvShowModel(
			id = entity?.tvShowEntity?.id.orEmpty(),
			airDate = entity?.tvShowEntity?.airDate.orEmpty(),
			episodeCount = entity?.tvShowEntity?.episodeCount.orEmpty(),
			seasonNumber = entity?.tvShowEntity?.seasonNumber.orEmpty(),
			backdropPath = entity?.tvShowEntity?.backdropPath?.let { IMAGE_PATH.plus(it) }
				.orEmpty(),
			episodeRunTime = entity?.tvShowEntity?.episodeRunTime.orEmpty(),
			firstAirDate = entity?.tvShowEntity?.firstAirDate.orEmpty(),
			homepage = entity?.tvShowEntity?.homepage.orEmpty(),
			inProduction = entity?.tvShowEntity?.inProduction.orFalse(),
			languages = entity?.tvShowEntity?.languages.orEmpty(),
			lastAirDate = entity?.tvShowEntity?.lastAirDate.orEmpty(),
			lastEpisodeToAir = entity?.tvShowEntity?.lastEpisodeToAir.orEmpty(),
			name = entity?.tvShowEntity?.name.orEmpty(),
			nextEpisodeToAir = entity?.tvShowEntity?.nextEpisodeToAir.orEmpty(),
			numberOfEpisodes = entity?.tvShowEntity?.numberOfEpisodes.orEmpty(),
			numberOfSeasons = entity?.tvShowEntity?.numberOfSeasons.orEmpty(),
			originCountry = entity?.tvShowEntity?.originCountry.orEmpty(),
			originalLanguage = entity?.tvShowEntity?.originalLanguage.orEmpty(),
			originalName = entity?.tvShowEntity?.originalName.orEmpty(),
			overview = entity?.tvShowEntity?.overview.orEmpty(),
			popularity = entity?.tvShowEntity?.popularity.orEmpty(),
			posterPath = entity?.tvShowEntity?.posterPath?.let { IMAGE_PATH.plus(it) }.orEmpty(),
			seasons = entity?.seasons?.map { convert(it) }.orEmpty(),
			status = entity?.tvShowEntity?.status.orEmpty(),
			type = entity?.tvShowEntity?.type.orEmpty(),
			voteAverage = entity?.tvShowEntity?.voteAverage.orEmpty(),
			voteCount = entity?.tvShowEntity?.voteCount.orEmpty(),
			genreIds = entity?.tvShowEntity?.genreIds.orEmpty()
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
		DatesModel(response?.maximum.orEmpty(), response?.minimum.orEmpty())
	
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
			page = response?.page.orEmpty(),
			results = response?.results?.map { convert(it) }.orEmpty(),
			totalPages = response?.totalPages.orEmpty(),
			totalResult = response?.totalResults.orEmpty()
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
	
	companion object {
		private const val EMPTY_STRING = ""
		const val EMPTY_INT = 0
		private const val EMPTY_BOOLEAN = false
		const val EMPTY_DOUBLE = 0.0
		private const val IMAGE_PATH = "https://image.tmdb.org/t/p/original"
	}
}

fun Int?.orEmpty(): Int = this ?: DataMapper.EMPTY_INT

fun Float?.orEmpty(): Float = this ?: DataMapper.EMPTY_INT.toFloat()

fun Long?.orEmpty(): Long = this ?: DataMapper.EMPTY_INT.toLong()

fun Double?.orEmpty(): Double = this ?: DataMapper.EMPTY_DOUBLE

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean?.orTrue(): Boolean = this ?: true

fun Int?.orDefault(def: Int): Int = this ?: def
