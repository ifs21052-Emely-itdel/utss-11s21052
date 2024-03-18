package com.ifs21052.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dino(
    var name: String,
    var icon: Int,
    var description: String,
    var periode: String,
    var characteristic: String,
    var habitat: String,
    var lingkungan: String,
    var peilaku: String,
    var klasifikasi: String,
) : Parcelable