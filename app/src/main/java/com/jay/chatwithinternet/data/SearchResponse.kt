package com.jay.chatwithinternet.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(val text: String, val isSender: Boolean = false): Parcelable {
}