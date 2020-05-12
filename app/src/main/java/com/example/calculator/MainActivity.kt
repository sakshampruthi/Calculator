package com.example.calculator

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    // variables to hold operations
    private var operand1: Double? = null
    private var operand2: Double? = null
    private var pendingOperation = "="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportActionBar!!.hide()
        }
        val oplistener = View.OnClickListener { view ->

            //listener for the operations
            val b = view as Button
            val op = b.text.toString() //
            val value = newnum?.text.toString() //gets the no. entered in newnum
            try {
                val doublevalue = java.lang.Double.valueOf(value) //check if any no. is entered
                performOperation(doublevalue, op) // perform calculation
            } catch (e: NumberFormatException) {
                newnum?.setText("")
            }
            pendingOperation = op
            if (pendingOperation == "xʸ") operation?.text = "^" else operation?.text = pendingOperation // display the operation pressed
        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            buttonlog!!.setOnClickListener {
                val str = newnum?.getText().toString()
                if (str == "0") result?.setText("Error") else {
                    newnum?.setText("log₁₀($str)")
                    println(str)
                    var n = str.toDouble()
                    n = Math.log10(n)
                    operation?.setText("=")
                    //
                    if (n % 1 == 0.0) {
                        result?.setText(String.format("%.0f", n))
                    } else result?.setText(String.format("%.4f", n))
                }
                buttonac.
                text = "C"
            }
            buttonln?.setOnClickListener {
                val str = newnum?.text.toString()
                if (str == "0") result?.setText("Error") else {
                    newnum?.setText("log($str)")
                    println(str)
                    var n = str.toDouble()
                    n = Math.log(n)
                    operation?.setText("=")
                    //
                    if (n % 1 == 0.0) {
                        result?.setText(String.format("%.0f", n))
                    } else result?.setText(String.format("%.4f", n))
                }
                buttonac.text = "C"
            }
            buttonpi.setOnClickListener { newnum?.setText("3.14159265359") }
            buttonsqaure.setOnClickListener {
                val str = newnum?.getText().toString()
                println(str)
                var n = str.toDouble()
                n = n.pow(2.0)
                newnum?.setText("$str²")
                operation?.text = "="
                if (n % 1 == 0.0) {
                    result?.setText(String.format("%.0f", n))
                } else result?.setText(String.format("%.4f", n))
                buttonac.text = "C"
            }
            buttoncube!!.setOnClickListener {
                val str = newnum?.text.toString()
                println(str)
                newnum?.setText("$str³")
                var n = str.toDouble()
                n = Math.pow(n, 3.0)
                operation?.setText("=")
                //
                if (n % 1 == 0.0) {
                    result?.setText(String.format("%.0f", n))
                } else result?.setText(String.format("%.4f", n))
                buttonac.text = "C"
            }
            buttonpower.setOnClickListener(oplistener)
            buttone.setOnClickListener { //                    String str = newnum.getText().toString();
                newnum?.setText("2.71828")
            }
            buttonroot.setOnClickListener {
                val str = newnum?.getText().toString()
                var n = str.toDouble()
                n = Math.sqrt(n)
                newnum?.setText("√$str")
                operation?.setText("=")
                if (n % 1 == 0.0) {
                    result?.setText(String.format("%.0f", n))
                } else result?.setText(String.format("%.4f", n))
                buttonac.text = "C"
            }
            buttoncuberoot.setOnClickListener {
                val str = newnum?.getText().toString()
                var n = str.toDouble()
                n = Math.cbrt(n)
                newnum?.setText("√$str")
                operation?.setText("=")
                if (n % 1 == 0.0) {
                    result?.setText(String.format("%.0f", n))
                } else result?.setText(String.format("%.4f", n))
                buttonac.text = "C"
            }
            buttonsin.setOnClickListener { newnum?.setText("sin(") }
            buttoncos.setOnClickListener { newnum?.setText("cos(") }
            buttontan.setOnClickListener { newnum?.setText("tan(") }
        }
        newnum?.isEnabled = false
        result?.isEnabled = false
        val remove = View.OnClickListener {
            if (newnum?.text.toString() == "" || result?.getText().toString() == "Error") {
                newnum?.setText("0")
                result?.setText("")
                operation?.setText("")
                operand1 = null
                operand2 = null
                buttonac.text = "AC"
            }
            var str = newnum?.getText().toString()
            if (str.length > 1) {
                str = str.substring(0, str.length - 1)
                newnum?.setText(str)
            } else if (str.length <= 1) {
                newnum?.setText("0")
                operation?.setText("")
                buttonac.text = "AC"
            }
        }
        buttonac.setOnLongClickListener {
            newnum?.setText("0")
            result?.setText("")
            operation?.text = ""
            operand1 = null
            operand2 = null
            buttonac.text = "AC"
            false
        }
        buttonac.setOnClickListener(remove)


        val listener = View.OnClickListener { view ->
            //onclicklistener for
            // the buttons to enter
            // no. in new num
            val b = view as Button
            if (newnum.text.toString() == "0") {
                newnum.setText(b.text)
            }
            else newnum?.append(b.text.toString()) //adds the clicked num to newnum
            buttonac.text = "C"
        }
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

//        final View.OnClickListener oplistener = new View.OnClickListener() { //listener for the operations
//            @Override
//            public void onClick(View view) {
//                Button b = (Button) view;
//                String op = b.getText().toString();         //
//                String value = newnum.getText().toString(); //gets the no. entered in newnum
//                try {
//                    Double doublevalue = Double.valueOf(value); //check if any no. is entered
//                    performOperation(doublevalue, op);// perform calculation
//                } catch (NumberFormatException e) {
//                    newnum.setText("");
//                }
//
//                pendingOperation = op;
//                if(pendingOperation.equals("x²"))
//                    displayOperation.setText("^");
//                else
//                    displayOperation.setText(pendingOperation);                    // display the operation pressed
//            }
//        };
        buttonEquals.setOnClickListener(oplistener)
        buttonDivide.setOnClickListener(oplistener)
        buttonMinus.setOnClickListener(oplistener)
        buttonMultiply.setOnClickListener(oplistener)
        buttonAdd.setOnClickListener(oplistener)


//        buttonsquare.setOnClickListener(oplistener);
//        buttonlog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String str = newnum.getText().toString();
//                if (str.equals("0"))
//                    result.setText("Error");
//                else {
//                    newnum.setText("Log₁₀(" + str + ")");
//
//                    System.out.println(str);
//                    Double n = Double.parseDouble(str);
//                    n = Math.log10(n);
//                    displayOperation.setText("=");
////
//                    if (n % 1 == 0) {
//                        result.setText(String.format("%.0f", n));
//                    } else
//                        result.setText(String.format("%.4f", n));
//
//                }
//            }
//        });
        buttonneg.setOnClickListener {
            val value = newnum?.getText().toString()
            if (value.isEmpty()) {
                newnum?.setText("-")
            } else {
                try {
                    var doubleValue = java.lang.Double.valueOf(value)
                    doubleValue *= -1.0
                    if (doubleValue % 1 == 0.0) {
                        newnum?.setText(String.format("%.0f", doubleValue))
                    } else newnum?.setText(doubleValue.toString())
                } catch (e: NumberFormatException) {
                    // newNumber was "-" or ".", so clear it
                    newnum?.setText("")
                }
            }
        }
        buttonpercent.setOnClickListener {
            val value = newnum?.text.toString()
            try {
                var doubleValue = java.lang.Double.valueOf(value)
                doubleValue /= 100.0
                newnum?.setText(doubleValue.toString())
            } catch (e: NumberFormatException) {
                // newNumber was "-" or ".", so clear it
                newnum?.setText("")
            }
        }
    }

    private fun performOperation(value: Double, op: String) {
        var error = false
        if (null == operand1) {
            operand1 = value
        } else {
            operand2 = value
            if (pendingOperation == "=") {
                pendingOperation = op
            }
            when (pendingOperation) {
                "=" -> operand1 = operand2
                "÷" -> if (operand2 == 0.0) {
                    error = true
                } else {
                    operand1 = operand1!!/ operand2!!
                }
                "x" -> operand1 = operand1!! * operand2!!
                "-" -> operand1 = operand1!! - operand2!!
                "+" -> operand1  = operand1!!+ operand2!!
                "xʸ" -> operand1 = Math.pow(operand1!!, operand2!!)
                "sin" -> {
                }
            }
        }
        if (!error) {
            if (operand1!! % 1 == 0.0) {
                result!!.setText(String.format("%.0f", operand1))
            } else result!!.setText(String.format("%.4f", operand1))
        } else {
            result!!.setText("Error")
            operand1 = 0.0
        }
        newnum!!.setText("")
    }
}