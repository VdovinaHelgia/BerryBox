package com.example.berrybox.adapter

import java.io.Serializable

data class ItemFruit(val id: Int? = null, val nameN: String, val description: String, val  cost: Int, val avatarUrl: Int, val favorite: Int, val shop: Int) :
    Serializable