package io.jcal.movies_provider.repository.mapper.model

data class MovieModel(
    val id: Int = 0,
    val adult: Boolean = false,
    val backdropPath: String = "",
    val budget: Int = 0,
    val genreIds: List<Int> = listOf(),
    val homepage: String = "",
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spokenLanguageModels: List<String> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) : BaseModel()

data class MoviesModel(
    val dates: DatesModel = DatesModel(),
    val page: Int = 0,
    val results: List<MovieModel> = listOf(),
    val totalPages: Int = 0,
    val totalResult: Int = 0
) : BaseModel()

data class DatesModel(
    val maximum: String = "",
    val minimum: String = ""
) : BaseModel()
