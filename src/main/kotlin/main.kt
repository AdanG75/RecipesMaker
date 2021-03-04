import Model.Ingredent
import Model.Recipe

val typeRecipe : Array<String> = arrayOf("Agua", "Leche", "Carne", "Verduras", "Frutas", "Cereal", "Huevos", "Aceites")

fun main(args: Array<String>) {

    var option : String = "0"
    var recipes: MutableList<Recipe> = mutableListOf()

    do{
        showMenu()
        option = readLine()?.toString() ?: "0"
        selectOption(option, recipes)
    }while(option != "3")
}

fun showMenu(){
    println("""
        
        
        :: Bienvennido a Recipe Maker ::
        
                Selecciona una opción deseada:
            1. Hacer una receta
            2. Ver mis recetas
            3. Salir
           
    """.trimIndent())
    print(" -> ")
}

fun selectOption(option : String, recipes: MutableList<Recipe>){
    when(option){
        "1" -> recipes.add(makeRecipe())
        "2" -> viewRecipe(recipes)
        "3" -> println("Adios :)")
        else -> println("La opción no fue encontrada. Favor de intertarlo de nuevo")
    }
}

fun makeRecipe() : Recipe
{
    println("\n         Hacer receta          \n Selecciona una categoría para tu receta (ingresa el númeroque se encuentra del lado izquierdo de la categorias)")

    var option: Int = selectRecipeType()

    println("\nQue nombre desea darle a su receta")
    val nameRecipe = readLine()?.toString() ?: "La receta de Dios"

    var recipe = Recipe(nameRecipe, typeRecipe[option.toInt()])

    loadIngredients(recipe)
    loadSteps(recipe)

    println("\n\tReceta creada exitosamente :)\n")
    return recipe
}

fun selectRecipeType() : Int
{
    showCategories()
    print("-> ")
    val option = readLine()?.toString() ?: "0"
    val length : Int = typeRecipe.size

    return when(option){
        in "1".."${length.toString()}" -> option.toInt().minus(1)
        else -> 0
    }
}

fun viewRecipe (recipes: MutableList<Recipe>)
{
    var flag = false
    println("\n            Ver mis recetas\n\n ")
    println("Seleccione la categoria de recetas que desea ver: ")
    var typeRecipeNumber: Int = selectRecipeType()
    for (recipe in recipes)
    {
        if (recipe.categoryRecipe == typeRecipe[typeRecipeNumber])
        {
            flag = true
            println("\n\t\t${recipe.nameRecipe}\n\t${recipe.categoryRecipe}")
            recipe.seeIngredents()
            recipe.seeSteps()
        }
    }
    if (!flag){
        println("No hay ninguna receta de ese tipo :(")}
}


fun loadIngredients(recipe: Recipe)
{
    var option: String = "0"
    var flagCategory: Boolean = false

    do{
        showCategories()
        println("\t\tEscribe los ingredientes necesarios para tu receta")
        println("\nEscriba el nombre del ingrediente: ")
        var nameIngredent: String = readLine().toString() ?: "Agua"
        println("\nEscriba la categoria del ingrediente")
        var categoryfood: String = readLine().toString() ?: "Agua"
        for ( type in typeRecipe){
            if(type == categoryfood)
            {
                flagCategory = true
                break
            }
        }
        println("\nEscribe la cantidad que se debe agregar. Para bebidas y aceites en ml. y para lo demás en gr.")
        var amountFood: String = readLine().toString() ?: "0"
        try{
            amountFood.toInt()
            if(flagCategory){
                var ingredent = Ingredent(nameIngredent, categoryfood)
                ingredent.setAmount(amountFood.toInt())
                recipe.addIngredent(ingredent)
                println("Ingrediente añadido")
            }else
            {
                println("No se pudo añadir el ingrediente. La categoria es incorrecta.")
            }
        }catch (e: NumberFormatException)
        {
            println("La cantidad ingresada no es correcta. Debe ser un número entero")
        }
        println("\n 1.- Para añadir otro ingrediente")
        println("\n 5.- Para terminar con los ingredientes")
        print("\n -> ")
        option = readLine().toString() ?: "0"
    }while (option != "5")

}

fun loadSteps(recipe: Recipe)
{
    var option: String = "0"
    var step: Int = 1
    var flagCategory: Boolean = false

    do{
        println("\t\tEscribe los pasos necesarios para tu receta")
        println("\nPaso $step: ")
        var stepRecipe: String = readLine().toString() ?: "Este paso es un distractor"
        recipe.addStep(stepRecipe)

        println("\n 1.- Para añadir otro paso")
        println("\n 5.- Para terminar con los pasos")
        print("\n -> ")
        option = readLine().toString() ?: "0"
        if (option != "5") { step += 1 }
    }while (option != "5")

}

fun showCategories(){
    println("\n\tCategorias:")
    for ( (index,type) in typeRecipe.withIndex()){
        println("${index.plus(1)}.- $type")
    }
    println("\n")
}


