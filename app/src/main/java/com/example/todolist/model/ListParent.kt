package com.example.todolist.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist.util.Enums


@Entity(tableName = "files_table")
data class ListParent(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var description: String,
    var status: String,
    var deadline:String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(status)
        parcel.writeString(deadline)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListParent> {
        override fun createFromParcel(parcel: Parcel): ListParent {
            return ListParent(parcel)
        }

        override fun newArray(size: Int): Array<ListParent?> {
            return arrayOfNulls(size)
        }
    }
}
