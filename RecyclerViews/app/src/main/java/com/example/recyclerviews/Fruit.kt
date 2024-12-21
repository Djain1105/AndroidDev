package com.example.recyclerviews

data class Fruit (
    val name: String,
    val origin: String,
    val quantity: Int
){
    companion object {

        val FRUIT_NAMES = arrayOf("Apple", "Mango", "Orange", "Banana",
                        "Grapes", "Strawberry","Pineapple","Watermelon")

        val ORIGINS = arrayOf("Lucknow", "Delhi", "Mumbai", "Kolkata","Nagpur")

        fun genRandomFruits(n: Int): ArrayList<Fruit> {
            val fruitArray = ArrayList<Fruit>(n)

            for(i in 1..n) {
                fruitArray.add(
                    Fruit(
                        FRUIT_NAMES.random(), // FRUIT_NAMES[Random.nextInt(FRUIT_NAMES.size)]
                        ORIGINS.random(), // ORIGINS[Random.nextInt(ORIGINS.size)]
                        (1..10).random()*100 // Random.nextInt(10) * 100
                    )
                )
            }
            return fruitArray
        }

    }
}