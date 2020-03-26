package com.example.momandpopshop.data

class Shop()

data class Item(val name: String, val cost: Int, val category: ItemType)

enum class ItemType {
    FOOD,
    HYGIENE,
    PET_SUPPLIES,
    TOYS
}