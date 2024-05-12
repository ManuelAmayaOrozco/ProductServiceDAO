package org.ejemploscompose.entity

import java.util.*

data class Product(var id: Int,
                   var name: String,
                   var price: Float,
                   var description: String,
                   var brand: String,
                   var category: String)