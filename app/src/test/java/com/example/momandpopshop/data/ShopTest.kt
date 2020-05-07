package com.example.momandpopshop.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ShopTest {

    private lateinit var shop: Shop
    private lateinit var person: Shopper
    private var realWarehouse = WarehouseImpl()
    private lateinit var warehouse: Warehouse

    @Before
    fun setup() {
        warehouse = mock(Warehouse::class.java)
        //shop = Shop(realWarehouse)
        shop = Shop(warehouse)
        person = Shopper()
    }

    @Test
    fun testInitialInventory_stockItems_CorrectAmountOfItems() {
        val stockMap = Item.singleList.map { it to 3 }.toMap()
        Mockito.`when`(warehouse.getRestock(Mockito.eq(3))).thenReturn(stockMap)
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
        Mockito.`when`(warehouse.getRestock(Mockito.eq(3))).thenReturn(stockMap)
        stockMap = Item.singleList.map { it to 5 }.toMap()
        Mockito.`when`(warehouse.getRestock(Mockito.eq(5))).thenReturn(stockMap)
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

    }

    @Test
    fun testItemPurchase_userHasCash_UserHasItemShopCountDown() {

    }

    @Test
    fun testItemPurchase_userHasNoMoney_NoChange() {

    }

    @Test
    fun testStockNewItem_emptyListAddEggs_storeHasEggs() {

    }

    @Test
    fun testStockNewItem_addEggsToOldStock_storeHasEggs() {

    }

}