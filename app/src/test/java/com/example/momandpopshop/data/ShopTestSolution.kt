package com.example.momandpopshop.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.Before
import org.junit.Test

class ShopTestSolution {

    lateinit var shop: Shop
    lateinit var person: Shopper

    @Before
    fun setup() {
        shop = Shop()
        person = Shopper()
    }

    @Test
    fun testInitialInventory_stockItems_CorrectAmountOfItems() {
        assertThat(shop.storeItems.size).isEqualTo(0)
        shop.stockItems(3)
        val items = shop.storeItems
        assertThat(items.size).isEqualTo(6)
        assertThat(items[Item.Eggs]).isEqualTo(3)
        assertThat(items[Item.Bread]).isEqualTo(3)
        assertThat(items[Item.DogTreat]).isEqualTo(3)
        assertThat(items[Item.DogCollar]).isEqualTo(3)
        assertThat(items[Item.Marbles]).isEqualTo(3)
        assertThat(items[Item.Cards]).isEqualTo(3)
    }

    @Test
    fun testRestockInventory_secondStock_stockIsHigher() {
        shop.stockItems(3)
        shop.stockItems(5)
        val items = shop.storeItems
        assertThat(items.size).isEqualTo(6)
        assertThat(items[Item.Eggs]).isEqualTo(5)
        assertThat(items[Item.Bread]).isEqualTo(5)
        assertThat(items[Item.DogTreat]).isEqualTo(5)
        assertThat(items[Item.DogCollar]).isEqualTo(5)
        assertThat(items[Item.Marbles]).isEqualTo(5)
        assertThat(items[Item.Cards]).isEqualTo(5)
    }

    @Test
    fun testItemPurchase_userHasCash_UserHasItemShopCountDown() {
        shop.stockItems(3)
        val itemBought = shop.buyItem(Item.Eggs, person)
        val items = shop.storeItems
        assertThat(items.size).isEqualTo(6)
        assertThat(itemBought).isTrue()
        assertThat(items[Item.Eggs]).isEqualTo(2)
        assertThat(items[Item.Bread]).isEqualTo(3)
        assertThat(items[Item.DogTreat]).isEqualTo(3)
        assertThat(items[Item.DogCollar]).isEqualTo(3)
        assertThat(items[Item.Marbles]).isEqualTo(3)
        assertThat(items[Item.Cards]).isEqualTo(3)

        val userItems = person.items
        assertThat(userItems.size).isEqualTo(1)
        assertThat(userItems[Item.Eggs]).isEqualTo(1)
        assertThat(person.cash).isEqualTo(99)
    }

    @Test
    fun testItemPurchase_userHasNoMoney_NoChange() {
        val shopper = Shopper(0)
        shop.stockItems(3)
        val itemBought = shop.buyItem(Item.Eggs, shopper)
        val items = shop.storeItems
        assertThat(items.size).isEqualTo(6)
        assertThat(itemBought).isFalse()
        assertThat(items[Item.Eggs]).isEqualTo(3)
        assertThat(items[Item.Bread]).isEqualTo(3)
        assertThat(items[Item.DogTreat]).isEqualTo(3)
        assertThat(items[Item.DogCollar]).isEqualTo(3)
        assertThat(items[Item.Marbles]).isEqualTo(3)
        assertThat(items[Item.Cards]).isEqualTo(3)
    }

}