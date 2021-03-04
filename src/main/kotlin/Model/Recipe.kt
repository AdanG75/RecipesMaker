package Model

class Recipe (var nameRecipe: String, var categoryRecipe: String) {

    var stepsToCook: MutableList<String> = mutableListOf()
    var ingredets: MutableList<Ingredent> = mutableListOf()

    fun addIngredent(newIngredent: Ingredent){
        ingredets.add(newIngredent)
    }

    fun addStep(newStep: String)
    {
        this.stepsToCook.add(newStep)
    }

    fun seeIngredents(){
        if (ingredets.none())
        {
            println("Recipe does not have ingredents")
        }else
        {
            println("\n\tIngredientes: \n")
            for((index, ingredent) in this.ingredets.withIndex().filterNotNull()){
                println("${index.plus(1)}.- ${ingredent.nameIngredent},  ${ingredent.categoryFood},  ${ingredent.amountIngredent}")
            }
        }
        println("\n                 ------                   \n")
    }

    fun seeSteps()
    {
        if (stepsToCook.none())
        {
            println("Recipe does not have steps to cook")
        }else
        {
            println("\n\tPasos: \n")
            for((index, step) in stepsToCook.withIndex().filterNotNull())
            {
                println("${index.plus(1)}.- ${step}")
            }
        }
        println("----------------------------------------------------\n")
    }
}