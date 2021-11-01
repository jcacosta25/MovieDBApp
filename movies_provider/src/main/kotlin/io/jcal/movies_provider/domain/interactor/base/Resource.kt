package io.jcal.movies_provider.domain.interactor.base

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<T>(val status: Status, val data: T? = null, val errorCode: Int? = null) {

	override fun equals(other: Any?): Boolean {
		if (this === other) {
			return true
		}
		if (other == null || javaClass != other.javaClass) {
			return false
		}

		val resource = other as Resource<*>?

		if (status !== resource!!.status) {
			return false
		}
		if (if (errorCode != null) errorCode != resource!!.errorCode else resource!!.errorCode != null) {
			return false
		}
		return if (data != null) data == resource.data else resource.data == null
	}

	override fun hashCode(): Int {
		var result = status.hashCode()
		result = 31 * result + errorCode!!.hashCode()
		result = 31 * result + (data?.hashCode() ?: 0)
		return result
	}

	override fun toString(): String =
		"Resource{ status= $status , errorCode= $errorCode, data= $data }"

	companion object {

		fun <T> success(data: T): Resource<T> {
			return Resource(
				Status.SUCCESS,
				data,
				null
			)
		}

		fun <T> error(errorCode: Int, data: T?): Resource<T> {
			return Resource(
				Status.ERROR,
				data,
				errorCode
			)
		}

		fun <T> loading(data: T): Resource<T> {
			return Resource(
				Status.LOADING,
				data,
				null
			)
		}
	}
}

class ResourceCoroutines<T, U>(
    val status: Status,
    val body: T? = null,
    val errorBody: U? = null,
    val errorCode: Int? = null
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) {
			return true
		}
		if (other == null || javaClass != other.javaClass) {
			return false
		}

		val resource = other as Resource<*>?

		if (status !== resource!!.status) {
			return false
		}
		if (if (errorCode != null) errorCode != resource?.errorCode else resource?.errorCode != null) {
			return false
		}
		return if (body != null) body == resource?.data else resource?.data == null
	}

	override fun hashCode(): Int {
		var result = status.hashCode()
		result = 31 * result + errorCode!!.hashCode()
		result = 31 * result + (body?.hashCode() ?: 0)
		return result
	}

	override fun toString(): String =
		"Resource{ status= $status , errorCode= $errorCode, data= $body  }"

	companion object {

		fun <T, U> success(data: T): ResourceCoroutines<T, U> {
			return ResourceCoroutines(
				Status.SUCCESS,
				data
			)
		}

		fun <T, U> error(
		    errorCode: Int,
		    body: T? = null,
		    errorBody: U? = null
		): ResourceCoroutines<T, U> {
			return ResourceCoroutines(
				status = Status.ERROR,
				body = body,
				errorBody = errorBody,
				errorCode = errorCode
			)
		}

		fun <T, U> loading(data: T? = null): ResourceCoroutines<T, U> {
			return ResourceCoroutines(
				Status.LOADING,
				data
			)
		}
	}
}
