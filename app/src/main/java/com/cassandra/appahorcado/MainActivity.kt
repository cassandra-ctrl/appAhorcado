package com.cassandra.appahorcado

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cassandra.appahorcado.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    //ActivityMainBinding se utiliza para encontrar cada uno de los elementos y acceder a sus propiedades
    private lateinit var binding:ActivityMainBinding
    //falseCount: Cuantas veces hacemos una mala eleccion en la letra
    private var falseCount=0
    //gameOverFlay: Resultado del juego si encontramos la palabra o no
    private var gameOverFlag=true
    //word: Palabra que estamos tratando de encontrar
    private lateinit var  word: String
    //targetWord: Actualizar el id/word cada vez que acertamos una palabra
    private lateinit var targetWord: String
    //indexes: Cuando hay dos letras repetidas en la misma palabra
    private lateinit var  indexes: MutableList<Int>
    //randomNumber: Obtiene una palabra aleatoria del diciconario
    private var randomNumber=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startGame()

        //Devuelve el id que corresponde a cada letra
        for (letter in 'a'..'z'){
            //OBTIENE EL NUMERO DE ID QUE SE LLAMA COMO LETTER
            val buttonId=resources.getIdentifier(letter.toString(),"id",packageName)
            //Obtiene el boton con ese Id
            val button=findViewById<View>(buttonId)

            button.setOnClickListener{


            }

        }



    }



    private fun startGame(){
        falseCount=0
        //Cuando el juego comienza, no debemos ver ninguna imagen en la pantalla
        binding.hangman.setImageResource(0)
        //Inicializamos el rango que tendra randomNumber
        randomNumber= Random.nextInt(0,320)
        //Guarda la palabra
        word=Words.DICTIONARY[randomNumber]
        createBlanks(word.length,binding)

    }

    /*
    Creamos la siguiente funcion para establecer
    cuantos espacios en blanco se mostraran
    en pantalla, esto segun el tamano de la palabra
    asignada en startGame
    */
    private fun createBlanks(size:Int, binding: ActivityMainBinding){
        binding.word.text ="_ ".repeat(size)
    }

    //La letra esta en la palabra o no
    private fun findIndexes(binding: ActivityMainBinding, word:String, letter:Char):MutableList<Int>{
        /*
        indexes: Se crea una lista vacia donde se iran
        guardando los indices de todas las coincidencias
        que encuentre
        * */
        val indexes = mutableListOf<Int>()

        /*
        word.mapIndexed: Le proporciona un indice a
        cada caracter de la palabra
        * */
        word.mapIndexed{ index, char ->
            //Si coincide la el actual caracter con la letra guardamos
            //el indice en indexes
            if(char==letter){
                indexes.add(index)
            }


        }
        //si nuestra lista esta vacia
        if(indexes.size==0){
            //Los intentos =10
            if(falseCount==10){
                gameOverFlag=false
                //showGameOverDialog(gameOverFlag)
            }
            falseCount++
            updateImage(binding,falseCount)
        }
        return indexes
    }

    private fun updateImage(binding: ActivityMainBinding, falseCount:Int){

    }

}