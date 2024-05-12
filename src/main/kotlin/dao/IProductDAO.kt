package dao

import org.ejemploscompose.entity.Product

interface IProductDAO {
    fun create(product: Product):Product?
    fun getAll(): List<Product>?
    fun getById(id: Int): Product?
    fun update(product: Product): Product?
    fun delete(id: Int): Boolean
}