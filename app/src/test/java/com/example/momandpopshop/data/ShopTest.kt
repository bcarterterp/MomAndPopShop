package com.example.momandpopshop.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Before
import org.junit.Test

class ShopTest {

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

    }

    @Test
    fun testItemPurchase_userHasNoMoney_NoChange() {

    }

}