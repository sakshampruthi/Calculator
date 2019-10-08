package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;



public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newnum;
    private TextView displayOperation;
    private AdView mAdView;


    // variables to hold operations

    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        }

        result = findViewById(R.id.result);
        newnum = findViewById(R.id.newnum);
        displayOperation = findViewById(R.id.operation);

       if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });


            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }


        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        final Button buttonac = findViewById(R.id.buttonac);
        Button buttonneg = findViewById(R.id.buttonneg);
        Button buttonpercent = findViewById(R.id.buttonpercent);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonPlus = findViewById(R.id.buttonAdd);
        Button buttonpower = findViewById(R.id.buttonpower );
        Button buttone = findViewById(R.id.buttone);
        Button buttonroot = findViewById(R.id.buttonroot);
        final View.OnClickListener oplistener = new View.OnClickListener() { //listener for the operations
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();         //
                String value = newnum.getText().toString(); //gets the no. entered in newnum
                try {
                    Double doublevalue = Double.valueOf(value); //check if any no. is entered
                    performOperation(doublevalue, op);// perform calculation
                } catch (NumberFormatException e) {
                    newnum.setText("");
                }

                pendingOperation = op;
                if(pendingOperation.equals("xʸ"))
                    displayOperation.setText("^");
                else
                    displayOperation.setText(pendingOperation);                    // display the operation pressed
            }
        };
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Button buttonsquare = findViewById(R.id.buttonsqaure);
            Button buttonlog = findViewById(R.id.buttonlog);
            Button buttonln=findViewById(R.id.buttonln);
            Button buttonpi=findViewById(R.id.buttonpi);
            Button buttoncube= findViewById(R.id.buttoncube);
            buttonlog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = newnum.getText().toString();
                    if (str.equals("0"))
                        result.setText("Error");
                    else {
                        newnum.setText("log₁₀(" + str + ")");

                        System.out.println(str);
                        Double n = Double.parseDouble(str);
                        n = Math.log10(n);
                        displayOperation.setText("=");
//
                        if (n % 1 == 0) {
                            result.setText(String.format("%.0f", n));
                        } else
                            result.setText(String.format("%.4f", n));

                    }
                    buttonac.setText("C");
                }
            });
            buttonln.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = newnum.getText().toString();
                    if (str.equals("0"))
                        result.setText("Error");
                    else {
                        newnum.setText("log(" + str + ")");

                        System.out.println(str);
                        Double n = Double.parseDouble(str);
                        n = Math.log(n);
                        displayOperation.setText("=");
//
                        if (n % 1 == 0) {
                            result.setText(String.format("%.0f", n));
                        } else
                            result.setText(String.format("%.4f", n));

                    }
                    buttonac.setText("C");
                }
            });
            buttonpi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newnum.setText("3.14159265359");

                }
            });

            buttonsquare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = newnum.getText().toString();
                    System.out.println(str);

                    Double n = Double.parseDouble(str);
                    n = Math.pow(n,2);
                    newnum.setText(str+"²");
                    displayOperation.setText("=");

                    if (n % 1 == 0) {
                        result.setText(String.format("%.0f", n));
                    } else
                        result.setText(String.format("%.4f", n));


                    buttonac.setText("C");

                }
            });
            buttoncube.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = newnum.getText().toString();
                    System.out.println(str);
                    newnum.setText(str+"³");
                    Double n = Double.parseDouble(str);
                    n = Math.pow(n,3);

                    displayOperation.setText("=");
//
                    if (n % 1 == 0) {
                        result.setText(String.format("%.0f", n));
                    } else
                        result.setText(String.format("%.4f", n));


                    buttonac.setText("C");
                }
            });
            buttonpower.setOnClickListener(oplistener);

            buttone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String str = newnum.getText().toString();
                    newnum.setText("2.71828");

                }
            });

            buttonroot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = newnum.getText().toString();

                    Double n = Double.parseDouble(str);
                    n = Math.sqrt(n);

                    newnum.setText("√"+str);
                    displayOperation.setText("=");

                    if (n % 1 == 0) {
                        result.setText(String.format("%.0f", n));
                    } else
                        result.setText(String.format("%.4f", n));


                    buttonac.setText("C");
                }
            });
        }

        newnum.setEnabled(false);
        result.setEnabled(false);

        View.OnClickListener remove = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newnum.getText().toString().equals("") || result.getText().toString().equals("Error")){
                    newnum.setText("0");
                    result.setText("");
                    displayOperation.setText("");
                    operand1 = null;
                    operand2 = null;
                    buttonac.setText("AC");
                }

                    String str=newnum.getText().toString();
                    if (str.length() >1 ) {
                        str = str.substring(0, str.length() - 1);
                        newnum.setText(str);
                    }
                    else if (str.length() <=1 ) {
                        newnum.setText("0");
                        displayOperation.setText("");
                        buttonac.setText("AC");
                    }
                }


        };
        buttonac.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                    newnum.setText("0");
                    result.setText("");
                    displayOperation.setText("");
                    operand1 = null;
                    operand2 = null;
                    buttonac.setText("AC");

                return false;
            }
        });

        buttonac.setOnClickListener(remove);

        View.OnClickListener listener = new View.OnClickListener() { //onclicklistener for
            @Override                                                  // the buttons to enter
            public void onClick(View view) {// no. in new num
                Button b = (Button) view;
                if(newnum.getText().toString().equals("0")){
                    newnum.setText("");
                }
                newnum.append(b.getText().toString()); //adds the clicked num to newnum
                buttonac.setText("C");
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

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
        buttonEquals.setOnClickListener(oplistener);
        buttonDivide.setOnClickListener(oplistener);
        buttonMinus.setOnClickListener(oplistener);
        buttonMultiply.setOnClickListener(oplistener);
        buttonPlus.setOnClickListener(oplistener);



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

        buttonneg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = newnum.getText().toString();
                if(value.length() == 0) {
                    newnum.setText("-");
                } else {
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        if(doubleValue%1==0){
                            newnum.setText(String.format("%.0f",doubleValue));
                        }
                        else
                            newnum.setText(doubleValue.toString());

                    } catch(NumberFormatException e) {
                        // newNumber was "-" or ".", so clear it
                        newnum.setText("");
                    }
                }

            }
        });


        buttonpercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = newnum.getText().toString();
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue /= 100;
                        newnum.setText(doubleValue.toString());
                    } catch(NumberFormatException e) {
                        // newNumber was "-" or ".", so clear it
                        newnum.setText("");
                    }
                }

            });

    }


    private void performOperation(Double value, String op) {
        boolean error=false;
        if (null == operand1) {
            operand1 = value;
        } else {
            operand2 = value;

            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = operand2;
                    break;
                case "÷":
                    if (operand2 == 0) {
                       error=true;
                    } else {
                        operand1 /= operand2;
                    }
                    break;
                case "x":
                    operand1 *= operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;

                case "xʸ":
                    operand1=Math.pow(operand1,operand2);
                    break;


            }
        }
        if (!error) {
            if(operand1%1==0){
                result.setText(String.format("%.0f",operand1));
            }
            else
                result.setText(String.format("%.4f",operand1));

        }
        else {
            result.setText("Error");
            operand1=0.0;
        }
        newnum.setText("");


    }

}
