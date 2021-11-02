package io.jcal.theMovie.presentation.mapper

import io.jcal.provider.repository.mapper.model.*
import io.jcal.theMovie.presentation.mapper.model.*
import javax.inject.Inject

class PresentationDataMapper @Inject constructor() {
	
	fun convert(model: MovieModel): MovieUIModel {
		val uiModel = MovieUIModel(
			id = model.id,
			adult = model.adult,
			backdropPath = model.backdropPath,
			budget = model.budget,
			genreIds = model.genreIds,
			homepage = model.homepage,
			imdbId = model.imdbId,
			originalLanguage = model.originalLanguage,
			originalTitle = model.originalTitle,
			overview = model.overview,
			popularity = model.popularity,
			posterPath = model.posterPath,
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
		uiModel.state = model.state
		return uiModel
	}
	
	fun convert(model: DatesModel): DatesUIModel {
		val uiModel = DatesUIModel(
			maximum = model.maximum,
			minimum = model.minimum
		)
		uiModel.state = model.state
		return uiModel
	}
	
	fun convert(model: MoviesModel): MovieUIModelList {
		val uiModel = MovieUIModelList(
			dates = convert(model.dates),
			page = model.page,
			results = model.results.map { convert(it) },
			totalPages = model.totalPages,
			totalResult = model.totalResult
		)
		uiModel.state = model.state
		return uiModel
	}
	
	fun convert(model: EpisodeModel): EpisodeUIModel {
		val uiModel = EpisodeUIModel(
			id = model.id,
			airDate = model.airDate,
			episodeNumber = model.episodeNumber,
			name = model.name,
			overview = model.overview,
			productionCode = model.productionCode,
			seasonNumber = model.seasonNumber,
			showId = model.showId,
			stillPath = model.stillPath,
			voteAverage = model.voteAverage,
			voteCount = model.voteCount
		)
		uiModel.state = model.state
		return uiModel
	}
	
	fun convert(model: SeasonModel): SeasonUIModel {
		val uiModel = SeasonUIModel(
			id = model.id,
			airDate = model.airDate,
			episodeCount = model.episodeCount,
			name = model.name,
			overview = model.overview,
			posterPath = model.posterPath,
			seasonNumber = model.seasonNumber,
			episodes = model.episodes.map { convert(it) },
			showId = model.showId
		)
		uiModel.state = model.state
		return uiModel
	}
	
	fun convert(model: TvShowModel): TvShowUIModel {
		val uiModel = TvShowUIModel(
			id = model.id,
			airDate = model.airDate,
			episodeCount = model.episodeCount,
			seasonNumber = model.seasonNumber,
			backdropPath = model.backdropPath,
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
			posterPath = model.posterPath,
			seasons = model.seasons.map { convert(it) },
			status = model.status,
			type = model.type,
			voteAverage = model.voteAverage,
			voteCount = model.voteCount,
			genreIds = model.genreIds
		)
		uiModel.state = model.state
		return uiModel
	}
	
	fun getErrorMessage(errorCode: Int): Int = errorCode
	
	fun convert(model: TvShowsModel): TvShowUIList = TvShowUIList(
		dates = convert(model.dates),
		page = model.page,
		results = model.results.map { convert(it) },
		totalPages = model.totalPages,
		totalResult = model.totalResult
	).apply {
		this.error = model.error
		this.state = model.state
	}
}
