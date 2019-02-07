package io.jcal.movies_provider.repository.api.network

object HttpErrorCodes {
    val SUCCESS = 200
    val SUCCESS_NO_CONTENT = 204
    val REDIRECTION = 300
    val BAD_REQUEST_ERROR = 400
    val UNAUTHORIZED_ERROR = 401
    val FORBIDDEN_ERROR = 403
    val NOT_FOUND_ERROR = 404
    val INVALID_SERVER_ERROR = 500
    val NO_NETWORK_RESPONSE_CODE = -1
}

object HttpBaseValues {
    val LANGUAGE = "en-US"
    val PAGE = 1
}