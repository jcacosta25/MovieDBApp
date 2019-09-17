package io.jcal.movies_provider.repository.datasource

import io.jcal.movies_provider.domain.interactor.base.Resource
import retrofit2.Response


abstract class BaseCloudDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return Resource.error(response.code(),response.body())
        } catch (e: Exception) {
            return Resource.error(99999,null)
        }
    }

}