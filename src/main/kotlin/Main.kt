package org.ejemploscompose

import dao.ProductDAO
import db_connection.DataSourceFactory
import org.ejemploscompose.entity.Product
import output.Console
import java.sql.DriverManager

fun main() {

    val console = Console()

    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    val product = Product(1, "Smartphone", 999.99f, "The latest smartphone model", "Apple", "Electronics")

    val productId = ProductDAO(dataSource, console).create(product)

    println("Product ID: $productId")

}