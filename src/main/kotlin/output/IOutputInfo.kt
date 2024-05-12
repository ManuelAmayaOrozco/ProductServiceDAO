package output

import org.ejemploscompose.entity.Product

interface IOutputInfo {

    fun showMessage(message: String, lineBreak: Boolean = true)
    fun show(productList: List<Product>?, message: String = "All products:")

}