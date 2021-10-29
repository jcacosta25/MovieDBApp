package io.jcal.movies_provider.repository.mapper.model

data class TvShowModel(
	val id: Int = 0,
	val airDate: String = "",
	val episodeCount: Int = 0,
	val seasonNumber: Int = 0,
	val backdropPath: String = "",
	val episodeRunTime: List<Int> = listOf(),
	val firstAirDate: String = "",
	val homepage: String = "",
	val inProduction: Boolean = false,
	val languages: List<String> = listOf(),
	val lastAirDate: String = "",
	val lastEpisodeToAir: Int = 0,
	val name: String = "",
	val nextEpisodeToAir: Int = 0,
	val numberOfEpisodes: Int = 0,
	val numberOfSeasons: Int = 0,
	val originCountry: List<String> = listOf(),
	val originalLanguage: String = "",
	val originalName: String = "",
	val overview: String = "",
	val popularity: Double = 0.0,
	val posterPath: String = "",
	val seasons: List<SeasonModel> = listOf(),
	val status: String = "",
	val type: String = "",
	val voteAverage: Double = 0.0,
	val voteCount: Int = 0,
	val genreIds: List<Int> = listOf()
) : BaseModel()

data class SeasonModel(
	val id: Int = 0,
	val airDate: String = "",
	val episodeCount: Int = 0,
	val name: String = "",
	val overview: String = "",
	val posterPath: String = "",
	val seasonNumber: Int = 0,
	val episodes: List<EpisodeModel> = listOf(),
	val showId: Int = 0
) : BaseModel()

data class EpisodeModel(
	val id: Int = 0,
	val airDate: String = "",
	val episodeNumber: Int = 0,
	val name: String = "",
	val overview: String = "",
	val productionCode: String = "",
	val seasonNumber: Int = 0,
	val showId: Int = 0,
	val stillPath: String = "",
	val voteAverage: Int = 0,
	val voteCount: Int = 0
) : BaseModel()

data class TvShowsModel(
	val dates: DatesModel = DatesModel(),
	val page: Int = 0,
	val results: List<TvShowModel> = listOf(),
	val totalPages: Int = 0,
	val totalResult: Int = 0
) : BaseModel()
