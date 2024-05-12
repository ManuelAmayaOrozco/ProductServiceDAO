package output

import org.ejemploscompose.entity.Product

class Console: IOutputInfo {

    override fun showMessage(message: String, lineBreak: Boolean) {
        if (lineBreak) println(message) else print(message)
    }

    override fun show(productList: List<Product>?, message: String) {
        if (productList != null) {
            if (productList.isEmpty()) {
                showMessage("No products found!")
            } else {
                showMessage(message)
                productList.forEachIndexed { index, user ->
                    showMessage("\t${index + 1}. $user")
                }
            }
        }
    }

}