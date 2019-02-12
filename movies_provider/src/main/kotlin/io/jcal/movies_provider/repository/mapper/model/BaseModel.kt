package io.jcal.movies_provider.repository.mapper.model

open class BaseModel(
    var state: String = LOADING,
    var error: Boolean = false,
    var errorCode: Int = BASE_ERROR_CODE
) {
    fun setError(errorCode: Int) {
        this.errorCode = errorCode
        this.error = true
        this.state = ERROR
    }

    companion object {
        const val SUCCESS = "success"
        const val LOADING = "loading"
        const val ERROR = "error"
        const val BASE_ERROR_CODE = 0
    }
}