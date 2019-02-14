package io.jcal.theMovie.presentation.mapper.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.StringRes

open class BaseUIModel(
    var error: Boolean = false,
    var state: String = LOADING,
    @StringRes var messageId: Int = 0,
    var errorCode: Int = 0
) : Parcelable {
    constructor(source: Parcel) : this(
        1 == source.readInt(),
        source.readString(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (error) 1 else 0))
        writeString(state)
        writeInt(messageId)
        writeInt(errorCode)
    }

    companion object {
        const val SUCCESS = "SUCCESS"
        const val LOADING = "LOADING"
        const val ERROR = "ERROR"
        const val EMPTY_STRING = ""
        const val EMPTY_INT = 0
        const val EMPTY_DOUBLE = 0.0
        const val EMPTY_BOOLEAN = false
        @JvmField
        val CREATOR: Parcelable.Creator<BaseUIModel> =
            object : Parcelable.Creator<BaseUIModel> {
                override fun createFromParcel(source: Parcel): BaseUIModel =
                    BaseUIModel(source)

                override fun newArray(size: Int): Array<BaseUIModel?> =
                    arrayOfNulls(size)
            }
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