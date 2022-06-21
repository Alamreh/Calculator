package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var input :TextView?=null

    private var lastNumeric= true
    private var lastDot=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input=findViewById(R.id.text_view_input)
    }

    fun onDigit(view: View) {

        input?.append((view as Button).text)
        lastDot=false
        lastNumeric=true

    }
    fun onEquals(view: View)
    {
        if(lastNumeric)
        {
            var tvValue=input?.text.toString()
            var prefix=""
            try {
                 if(tvValue.startsWith("-"))
                 {
                     prefix="-"
                     tvValue=tvValue.substring(1)
                 }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]//99
                    var two = splitValue[1]//1
                    if(!prefix.isEmpty())
                        one=prefix+one
                    input?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }


                else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]//99
                    var two = splitValue[1]//1
                    if(!prefix.isEmpty())
                        one=prefix+one
                    input?.text = removeZeroAfterDot((one.toDouble() +two.toDouble()).toString())
                }

                else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]//99
                    var two = splitValue[1]//1
                    if(!prefix.isEmpty())
                        one=prefix+one
                    input?.text = removeZeroAfterDot((one.toDouble() /two.toDouble()).toString())
                }

               else if(tvValue.contains("X")) {
                    val splitValue = tvValue.split("X")
                    var one = splitValue[0]//99
                    var two = splitValue[1]//1
                    if(!prefix.isEmpty())
                        one=prefix+one
                    input?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }catch (e:ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }

    fun removeonCharacter(view: View)
    {
        try {
            var value=input?.text.toString()
            value=value.substring(0,value.length-1)
            input?.text=value
        }
        catch (e:Exception)
        {
         Toast.makeText(this,"No Input!!!",Toast.LENGTH_SHORT).show()
        }

    }
    fun onClear(view: View)
    {
        input?.text=""
    }
    //this function call when decimal point button is clicked
     fun onDecimal(view: View)
    {
        //if last numeric true we are able to add decimal after numeric
        if(lastNumeric && !lastDot)
        {
            input?.append(".")

        }

    }

    private fun removeZeroAfterDot(result:String):String
    {
        var value=result;
        if(value.contains(".0"))
            value=value.substring(0,value.length-2)
        return value
    }

    //this function is called when operator button is clicked
     fun onOperator(view: View)
     {
         /* it is act as char sequence
             we use let beacause if input is empty no action is performed
         * */
         input?.text?.let {
             if(lastNumeric && !isOperatorAdded(it.toString()))
             {
                 input?.append((view as Button).text)
                 lastNumeric=false
                 lastDot=false
             }
         }
     }


    private  fun isOperatorAdded(value:String):Boolean{
        /*
        * value.startsWith("") means this function act as if we click minus before numeric and add to the number after the digit is clicked
        * */
       return  if(value.startsWith("-"))
       {
           false
       }
        else
       {//cintains return either true or false;
           value.contains("/")||value.contains("*")||
                   value.contains("+")||value.contains("-")
       }
    }
}