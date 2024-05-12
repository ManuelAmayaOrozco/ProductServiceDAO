package dao

import org.ejemploscompose.entity.Product
import output.IOutputInfo
import java.sql.SQLException
import javax.sql.DataSource

class ProductDAO(private val dataSource: DataSource, private val console: IOutputInfo) : IProductDAO {

    override fun create(product: Product): Product? {
        val sql = "INSERT INTO products (id, name, price, description, brand, category) VALUES (?, ?, ?, ?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, product.id.toString())
                    stmt.setString(2, product.name)
                    stmt.setString(3, product.price.toString())
                    stmt.setString(4, product.description)
                    stmt.setString(5, product.brand)
                    stmt.setString(6, product.category)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        product
                    } else {
                        console.showMessage("*Error* Insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Insert query failed! (${e.message})")
            null
        }
    }

    override fun getById(id: Int): Product? {
        val sql = "SELECT * FROM products WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        Product(
                            id = rs.getString("id").toInt(),
                            name = rs.getString("name"),
                            price = rs.getString("price").toFloat(),
                            description = rs.getString("description"),
                            brand = rs.getString("brand"),
                            category = rs.getString("category")
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Select query failed! (${e.message})")
            null
        }
    }

    override fun getAll(): List<Product>? {
        val sql = "SELECT * FROM products"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<Product>()
                    while (rs.next()) {
                        users.add(
                            Product(
                                id = rs.getString("id").toInt(),
                                name = rs.getString("name"),
                                price = rs.getString("price").toFloat(),
                                description = rs.getString("description"),
                                brand = rs.getString("brand"),
                                category = rs.getString("category")
                            )
                        )
                    }
                    users
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Select query failed! (${e.message})")
            null
        }
    }

    override fun update(product: Product): Product? {
        val sql = "UPDATE products SET name = ?, price = ?, description = ?, brand = ?, category = ? WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, product.name)
                    stmt.setString(2, product.price.toString())
                    stmt.setString(3, product.description)
                    stmt.setString(4, product.brand)
                    stmt.setString(5, product.category)
                    stmt.setString(6, product.id.toString())
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        product
                    } else {
                        console.showMessage("*Error* Update query failed! ($rs records updated)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Update query failed! (${e.message})")
            null
        }
    }

    override fun delete(id: Int): Boolean {
        val sql = "DELETE FROM products WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    (stmt.executeUpdate() == 1)
                }
            }
        } catch (e: SQLException) {
            console.showMessage("*Error* Delete query failed! (${e.message})")
            false
        }
    }
}