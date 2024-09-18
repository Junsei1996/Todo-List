package com.example.todolist.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "files_table",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.CASCADE
        )
    ))
data class ListParent(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String?,
    var description: String?,
    var categoryId:Int,
    var status: String?,
    var deadline:String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(categoryId)
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