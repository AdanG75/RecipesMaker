package Model

class Ingredent ( nameIngredent: String, categoryFood: String) : Food(nameIngredent, categoryFood) {

    var amountIngredent : String = ""

    fun setAmount(amountIngredent : Int)
    {
        if(this.categoryFood == "Agua" || this.categoryFood == "Aceites")
            this.amountIngredent = amountIngredent.toString() + "ml."
        else
            this.amountIngredent = amountIngredent.toString() + "gr."
    }

}