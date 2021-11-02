package io.jcal.provider.repository.datasource

import io.jcal.provider.domain.interactor.base.ResourceCoroutines
import io.jcal.provider.repository.api.network.HttpErrorCodes.EXCEPTION_RESPONSE_CODE
import io.jcal.provider.repository.api.network.HttpErrorCodes.NO_NETWORK_RESPONSE_CODE
import io.jcal.provider.repository.api.network.coroutinesAdapter.NetworkResponse

abstract class BaseCloudDataSource {

	protected suspend fun <T, U> getResultCoroutines(call: suspend () -> NetworkResponse<T, U>): ResourceCoroutines<T, U> =
		try {
			when (val response = call()) {
				// Handle Success
				is NetworkResponse.Success ->
					ResourceCoroutines.success(data = response.body)
				// Handle Server Error
				is NetworkResponse.ServerError ->
					ResourceCoroutines.error(
						errorCode = response.code,
						errorBody = response.errorBody
					)
				// Handle Network Error
				is NetworkResponse.NetworkError ->
					ResourceCoroutines.error(NO_NETWORK_RESPONSE_CODE)
			}
		} catch (e: Exception) {
			ResourceCoroutines.error(EXCEPTION_RESPONSE_CODE)
		}
}
