package com.example.berrybox.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "nameN")
    var nameN: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "cost")
    var cost: Int,
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: Int,
    @ColumnInfo(name = "favorite")
    var favorite: Int,
    @ColumnInfo(name = "shop")
    var shop: Int,
    )