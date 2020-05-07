package com.example.momandpopshop.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ShopTestSolution {

    private lateinit var shop: Shop
    private lateinit var person: Shopper
    private lateinit var warehouse: Warehouse

    @Before
    fun setup() {
        warehouse = Mockito.mock(Warehouse::class.java)
        shop = Shop(warehouse)
        person = Shopper()
    }

    @Test
    fun testInitialInventory_stockItems_CorrectAmountOfItems() {
        val stockMap = Item.singleList.map { it to 3 }.toMap()
        Mockito.`when`(warehouse.getRestock(3)).thenReturn(stockMap)
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
        var stockMap = Item.singleList.map { it to 3 }.toMap()
        Mockito.`when`(warehouse.getRestock(3)).thenReturn(stockMap)
        stockMap = Item.singleList.map { it to 5 }.toMap()
        Mockito.`when`(warehouse.getRestock(5)).thenReturn(stockMap)
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
    fun testStockNewItem_shopHasStockWithNewItem() {
        val stockMap = Item.singleList.map { it to 3 }.toMap().plus(Item.Doughnuts to 3)
        Mockito.`when`(warehouse.getRestock(3)).thenReturn(stockMap)
        shop.stockItems(3)
        val items = shop.storeItems
        assertThat(items.size).isEqualTo(7)
        assertThat(items[Item.Eggs]).isEqualTo(3)
        assertThat(items[Item.Bread]).isEqualTo(3)
        assertThat(items[Item.Doughnuts]).isEqualTo(3)
        assertThat(items[Item.DogTreat]).isEqualTo(3)
        assertThat(items[Item.DogCollar]).isEqualTo(3)
        assertThat(items[Item.Marbles]).isEqualTo(3)
        assertThat(items[Item.Cards]).isEqualTo(3)
    }

    @Test
    fun testItemPurchase_userHasCash_UserHasItemShopCountDown() {
        val stockMap = Item.singleList.map { it to 3 }.toMap()
        Mockito.`when`(warehouse.getRestock(3)).thenReturn(stockMap)
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
        val stockMap = Item.singleList.map { it to 3 }.toMap()
        Mockito.`when`(warehouse.getRestock(3)).thenReturn(stockMap)
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

    @Test
    fun testStockNewItem_emptyListAddEggs_storeHasEggs() {
        Mockito.`when`(warehouse.orderNewProduct(Item.Eggs, 5)).thenReturn(Pair(Item.Eggs, 5))
        shop.stockNewItem(Item.Eggs, 5)
        val items = shop.storeItems
        assertThat(items.size).isEqualTo(1)
        assertThat(items[Item.Eggs]).isEqualTo(5)
    }

    @Test
    fun testStockNewItem_addEggsToOldStock_storeHasEggs() {
        val stockMap = Item.singleList.map { it to 3 }.toMap()
        Mockito.`when`(warehouse.getRestock(3)).thenReturn(stockMap)
        Mockito.`when`(warehouse.orderNewProduct(Item.Eggs, 5)).thenReturn(Pair(Item.Eggs, 5))
        shop.stockItems(3)
        shop.stockNewItem(Item.Eggs, 5)
        val items = shop.storeItems
        assertThat(items.size).isEqualTo(6)
        assertThat(items[Item.Eggs]).isEqualTo(5)
        assertThat(items[Item.Bread]).isEqualTo(3)
        assertThat(items[Item.DogTreat]).isEqualTo(3)
        assertThat(items[Item.DogCollar]).isEqualTo(3)
        assertThat(items[Item.Marbles]).isEqualTo(3)
        assertThat(items[Item.Cards]).isEqualTo(3)
    }

}