import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListDino(
    var name: String,
    var icon: Int,
    var description: String,
    var kelompok: String,
    var habitat: String,
    var makanan: String,
    var panjang: String,
    var tinggi: String,
    var bobot: String,
    var kelemahan: String,
) : Parcelable