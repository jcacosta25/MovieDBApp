package io.jcal.movies_provider.repository.api.model

import com.squareup.moshi.Json

data class TvShowsDTO(
    @Json(name = "dates")
    val dates: DatesDTO,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<TvShowDTO>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

data class DatesDTO(
    @Json(name = "maximum")
    val maximum: String,
    @Json(name = "minimum")
    val minimum: String
)

data class MoviesDTO(
    @Json(name = "dates")
    val dates: DatesDTO,
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieDTO>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

data class MovieDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any,
    @Json(name = "budget")
    val budget: Int,
    @Json(name = "genres")
    val genres: List<GenreDTO>,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "production_companies")
    val productionCompanyModels: List<ProductionCompanyDTO>,
    @Json(name = "production_countries")
    val productionCountryModels: List<ProductionCountryDTO>,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "revenue")
    val revenue: Int,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "spoken_languages")
    val spokenLanguageModels: List<SpokenLanguageDTO>,
    @Json(name = "status")
    val status: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

data class SpokenLanguageDTO(
    @Json(name = "iso_639_1")
    val iso6391: String,
    @Json(name = "name")
    val name: String
)

data class GenreDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)

data class ProductionCompanyDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String
)

data class ProductionCountryDTO(
    @Json(name = "iso_3166_1")
    val iso31661: String,
    @Json(name = "name")
    val name: String
)

data class TvShowDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode_count")
    val episodeCount: Int,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "created_by")
    val createdBy: List<CreatorDTO>,
    @Json(name = "episode_run_time")
    val episodeRunTime: List<Int>,
    @Json(name = "first_air_date")
    val firstAirDate: String,
    @Json(name = "genres")
    val genres: List<GenreDTO>,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "in_production")
    val inProduction: Boolean,
    @Json(name = "languages")
    val languages: List<String>,
    @Json(name = "last_air_date")
    val lastAirDate: String,
    @Json(name = "last_episode_to_air")
    val lastEpisodeToAir: EpisodeDTO,
    @Json(name = "name")
    val name: String,
    @Json(name = "networks")
    val networks: List<TvNetworkDTO>,
    @Json(name = "next_episode_to_air")
    val nextEpisodeToAir: EpisodeDTO,
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int,
    @Json(name = "origin_country")
    val originCountry: List<String>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompanyDTO>,
    @Json(name = "seasons")
    val seasons: List<SeasonDTO>,
    @Json(name = "status")
    val status: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "genre_ids")
    val genreIds: List<Int>
)

data class SeasonDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "_id")
    val _id: String,
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode_count")
    val episodeCount: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "episodes")
    val episodes: List<EpisodeDTO>
)

data class EpisodeDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode_number")
    val episodeNumber: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "production_code")
    val productionCode: String,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "show_id")
    val showId: Int,
    @Json(name = "still_path")
    val stillPath: Any,
    @Json(name = "vote_average")
    val voteAverage: Int,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "crew")
    val crew: List<CrewDTO>,
    @Json(name = "guest_stars")
    val guestStars: List<GuestStarDTO>
)

data class TvNetworkDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: String
)

data class CreatorDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "profile_path")
    val profilePath: String
)

data class GuestStarDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "character")
    val character: String,
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "profile_path")
    val profilePath: String
)

data class CrewDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "credit_id")
    val creditId: String,
    @Json(name = "department")
    val department: String,
    @Json(name = "job")
    val job: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "profile_path")
    val profilePath: String
)

data class PeopleDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "also_known_as")
    val alsoKnownAs: List<String>,
    @Json(name = "biography")
    val biography: String,
    @Json(name = "birthday")
    val birthday: String,
    @Json(name = "deathday")
    val deathday: Any,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "homepage")
    val homepage: Any,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "place_of_birth")
    val placeOfBirth: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String
)
