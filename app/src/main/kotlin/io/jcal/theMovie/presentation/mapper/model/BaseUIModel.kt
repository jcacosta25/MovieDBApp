package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseUIModel(
    var error: Boolean = false,
    var state: String = LOADING,
    @StringRes var messageId: Int = 0,
    var errorCode: Int = 0
) : Parcelable {

    companion object {
        const val SUCCESS = "success"
        const val LOADING = "loading"
        const val ERROR = "error"
        const val EMPTY_STRING = ""
        const val EMPTY_INT = 0
        const val EMPTY_DOUBLE = 0.0
        const val EMPTY_BOOLEAN = false
    }
}

/**
 * Shallow copy of base class [BaseUIModel]
 *
 * @param [BaseUIModel] base class
 * @return [T] sub class
 */
fun <T : BaseUIModel> T.baseCopy(base: BaseUIModel): T {
    state = base.state
    error = base.error
    errorCode = base.errorCode
    return this
}
