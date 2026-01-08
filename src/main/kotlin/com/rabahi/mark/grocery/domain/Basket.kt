package com.rabahi.mark.grocery.domain

data class Basket(val items: MutableList<StockItem> = mutableListOf()){

    fun total():Double= items.sumOf { item -> item.cost }
    fun addItem(item: StockItem){
        items.add(item)
    }

    fun hasThreeSoupAndTwoBread(basket: Basket): Boolean {
        val loaves = basket.items.count { item -> StockItem.BREAD == item }
        val soups = basket.items.count { item -> StockItem.SOUP == item }
        return (2 == loaves && soups == 3)
    }
}
