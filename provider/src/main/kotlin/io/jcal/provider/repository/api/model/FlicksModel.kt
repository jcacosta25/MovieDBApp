package io.jcal.provider.repository.api.model

import com.google.gson.annotations.SerializedName

data class TvShowsDTO(
    @SerializedName(value = "dates")   
	   val dates: DatesDTO,
    @SerializedName("page")   
	   val page: Int,
    @SerializedName(value = "results")   
	   val results: List<TvShowDTO>,
    @SerializedName(value = "total_pages")   
	   val totalPages: Int,
    @SerializedName(value = "total_results")   
	   val totalResults: Int
)

data class DatesDTO(
    @SerializedName(value = "maximum")   
	   val maximum: String,
    @SerializedName(value = "minimum")   
	   val minimum: String
)

data class MoviesDTO(
    @SerializedName(value = "dates")   
	   val dates: DatesDTO,
    @SerializedName(value = "page")   
	   val page: Int,
    @SerializedName(value = "results")   
	   val results: List<MovieDTO>,
    @SerializedName(value = "total_pages")   
	   val totalPages: Int,
    @SerializedName(value = "total_results")   
	   val totalResults: Int
)

data class MovieDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "adult")   
	   val adult: Boolean,
    @SerializedName(value = "backdrop_path")   
	   val backdropPath: String,
    @SerializedName(value = "belongs_to_collection")   
	   val belongsToCollection: Any,
    @SerializedName(value = "budget")   
	   val budget: Int,
    @SerializedName(value = "genres")   
	   val genres: List<GenreDTO>,
    @SerializedName(value = "genre_ids")   
	   val genreIds: List<Int>?,
    @SerializedName(value = "homepage")   
	   val homepage: String,
    @SerializedName(value = "imdb_id")   
	   val imdbId: String,
    @SerializedName(value = "original_language")   
	   val originalLanguage: String,
    @SerializedName(value = "original_title")   
	   val originalTitle: String,
    @SerializedName(value = "overview")   
	   val overview: String,
    @SerializedName(value = "popularity")   
	   val popularity: Double,
    @SerializedName(value = "poster_path")   
	   val posterPath: String,
    @SerializedName(value = "production_companies")   
	   val productionCompanyModels: List<ProductionCompanyDTO>,
    @SerializedName(value = "production_countries")   
	   val productionCountryModels: List<ProductionCountryDTO>,
    @SerializedName(value = "release_date")   
	   val releaseDate: String,
    @SerializedName(value = "revenue")   
	   val revenue: Int,
    @SerializedName(value = "runtime")   
	   val runtime: Int,
    @SerializedName(value = "spoken_languages")   
	   val spokenLanguageModels: List<SpokenLanguageDTO>,
    @SerializedName(value = "status")   
	   val status: String,
    @SerializedName(value = "tagline")   
	   val tagline: String,
    @SerializedName(value = "title")   
	   val title: String,
    @SerializedName(value = "video")   
	   val video: Boolean,
    @SerializedName(value = "vote_average")   
	   val voteAverage: Double,
    @SerializedName(value = "vote_count")   
	   val voteCount: Int
)

data class SpokenLanguageDTO(
    @SerializedName(value = "iso_639_1")   
	   val iso6391: String,
    @SerializedName(value = "name")   
	   val name: String
)

data class GenreDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "name")   
	   val name: String
)

data class ProductionCompanyDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "logo_path")   
	   val logoPath: String,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "origin_country")   
	   val originCountry: String
)

data class ProductionCountryDTO(
    @SerializedName(value = "iso_3166_1")   
	   val iso31661: String,
    @SerializedName(value = "name")   
	   val name: String
)

data class TvShowDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "air_date")   
	   val airDate: String,
    @SerializedName(value = "episode_count")   
	   val episodeCount: Int,
    @SerializedName(value = "season_number")   
	   val seasonNumber: Int,
    @SerializedName(value = "backdrop_path")   
	   val backdropPath: String,
    @SerializedName(value = "created_by")   
	   val createdBy: List<CreatorDTO>,
    @SerializedName(value = "episode_run_time")   
	   val episodeRunTime: List<Int>,
    @SerializedName(value = "first_air_date")   
	   val firstAirDate: String,
    @SerializedName(value = "genres")   
	   val genres: List<GenreDTO>,
    @SerializedName(value = "homepage")   
	   val homepage: String,
    @SerializedName(value = "in_production")   
	   val inProduction: Boolean,
    @SerializedName(value = "languages")   
	   val languages: List<String>,
    @SerializedName(value = "last_air_date")   
	   val lastAirDate: String,
    @SerializedName(value = "last_episode_to_air")   
	   val lastEpisodeToAir: EpisodeDTO?,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "networks")   
	   val networks: List<TvNetworkDTO>,
    @SerializedName(value = "next_episode_to_air")   
	   val nextEpisodeToAir: EpisodeDTO?,
    @SerializedName(value = "number_of_episodes")   
	   val numberOfEpisodes: Int,
    @SerializedName(value = "number_of_seasons")   
	   val numberOfSeasons: Int,
    @SerializedName(value = "origin_country")   
	   val originCountry: List<String>,
    @SerializedName(value = "original_language")   
	   val originalLanguage: String,
    @SerializedName(value = "original_name")   
	   val originalName: String,
    @SerializedName(value = "overview")   
	   val overview: String,
    @SerializedName(value = "popularity")   
	   val popularity: Double,
    @SerializedName(value = "poster_path")   
	   val posterPath: String,
    @SerializedName(value = "production_companies")   
	   val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName(value = "seasons")   
	   val seasons: List<SeasonDTO>?,
    @SerializedName(value = "status")   
	   val status: String,
    @SerializedName(value = "type")   
	   val type: String,
    @SerializedName(value = "vote_average")   
	   val voteAverage: Double,
    @SerializedName(value = "vote_count")   
	   val voteCount: Int,
    @SerializedName(value = "genre_ids")   
	   val genreIds: List<Int>
)

data class SeasonDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "_id")   
	   val _id: String,
    @SerializedName(value = "air_date")   
	   val airDate: String,
    @SerializedName(value = "episode_count")   
	   val episodeCount: Int,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "overview")   
	   val overview: String,
    @SerializedName(value = "poster_path")   
	   val posterPath: String,
    @SerializedName(value = "season_number")   
	   val seasonNumber: Int,
    @SerializedName(value = "episodes")   
	   val episodes: List<EpisodeDTO>?
)

data class EpisodeDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "air_date")   
	   val airDate: String,
    @SerializedName(value = "episode_number")   
	   val episodeNumber: Int,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "overview")   
	   val overview: String,
    @SerializedName(value = "production_code")   
	   val productionCode: String,
    @SerializedName(value = "season_number")   
	   val seasonNumber: Int,
    @SerializedName(value = "show_id")   
	   val showId: Int,
    @SerializedName(value = "still_path")   
	   val stillPath: Any,
    @SerializedName(value = "vote_average")   
	   val voteAverage: Int,
    @SerializedName(value = "vote_count")   
	   val voteCount: Int,
    @SerializedName(value = "crew")   
	   val crew: List<CrewDTO>,
    @SerializedName(value = "guest_stars")   
	   val guestStars: List<GuestStarDTO>
)

data class TvNetworkDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "logo_path")   
	   val logoPath: String,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "origin_country")   
	   val originCountry: String
)

data class CreatorDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "credit_id")   
	   val creditId: String,
    @SerializedName(value = "gender")   
	   val gender: Int,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "profile_path")   
	   val profilePath: String
)

data class GuestStarDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "character")   
	   val character: String,
    @SerializedName(value = "credit_id")   
	   val creditId: String,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "order")   
	   val order: Int,
    @SerializedName(value = "profile_path")   
	   val profilePath: String
)

data class CrewDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "credit_id")   
	   val creditId: String,
    @SerializedName(value = "department")   
	   val department: String,
    @SerializedName(value = "job")   
	   val job: String,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "profile_path")   
	   val profilePath: String
)

data class PeopleDTO(
    @SerializedName(value = "id")   
	   val id: Int,
    @SerializedName(value = "adult")   
	   val adult: Boolean,
    @SerializedName(value = "also_known_as")   
	   val alsoKnownAs: List<String>,
    @SerializedName(value = "biography")   
	   val biography: String,
    @SerializedName(value = "birthday")   
	   val birthday: String,
    @SerializedName(value = "deathday")   
	   val deathday: Any,
    @SerializedName(value = "gender")   
	   val gender: Int,
    @SerializedName(value = "homepage")   
	   val homepage: Any,
    @SerializedName(value = "imdb_id")   
	   val imdbId: String,
    @SerializedName(value = "known_for_department")   
	   val knownForDepartment: String,
    @SerializedName(value = "name")   
	   val name: String,
    @SerializedName(value = "place_of_birth")   
	   val placeOfBirth: String,
    @SerializedName(value = "popularity")   
	   val popularity: Double,
    @SerializedName(value = "profile_path")   
	   val profilePath: String
)

data class ServerErrorDto(
    @SerializedName(value = "status_message")   
	   val statusMessage: String?,
    @SerializedName(value = "success")   
	   val success: Boolean = false,
    @SerializedName(value = "status_code")   
	   val statusCode: Int?
)
