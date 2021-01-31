package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseUIModel(
    var error: Boolean = false,
    var state: String = LOADING,
    @StringRes var messageId: Int = 0
) : Parcelable {

    companion object {
        const val SUCCESS = "success"
        const val LOADING = "loading"
        const val ERROR = "error"
        const val EMPTY_DOUBLE = 0.0
    }
}

/**
 * Shallow copy of base class [BaseUIModel]
 *
 * @param [base] base class
 * @return [T] sub class
 */
fun <T : BaseUIModel> T.baseCopy(base: BaseUIModel): T {
    state = base.state
    error = base.error
    messageId = base.messageId
    return this
}
