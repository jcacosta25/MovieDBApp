package io.jcal.movies_provider.repository.mapper.model

open class BaseModel(
    var state: String = LOADING,
    var error: Boolean = false,
    var errorCode: Int = BASE_ERROR_CODE
) {
	var isSuccess: Boolean = false
	fun setError(errorCode: Int) {
		this.errorCode = errorCode
		this.error = true
		this.state = ERROR
	}
	
	fun setSuccess() {
		this.state = SUCCESS
		this.error = false
		this.isSuccess = true
		this.errorCode = BASE_ERROR_CODE
	}
	
	companion object {
		const val SUCCESS = "success"
		const val LOADING = "loading"
		const val ERROR = "error"
		const val BASE_ERROR_CODE = 0
		const val PARSING_ERROR = -2
		const val NOT_EXISTING_VALUE = -3
	}
}
