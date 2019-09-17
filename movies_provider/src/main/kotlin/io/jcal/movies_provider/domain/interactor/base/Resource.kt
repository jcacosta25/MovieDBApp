package io.jcal.movies_provider.domain.interactor.base

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class Resource<T>(val status: Status, val data: T?, val errorCode: Int?) {

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
