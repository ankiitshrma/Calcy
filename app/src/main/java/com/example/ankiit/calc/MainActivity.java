package com.example.ankiit.calc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private TextView _screen;
    private String display="";
    private String currentOperator="";
    Button btnEql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _screen=(TextView) findViewById(R.id.textView);
        _screen.setText(display);

        };



    private void updateScreen(){
        _screen.setText(display);
    }

    public void onClickNumber(View v){
        Button b= (Button) v;
        display+=b.getText();
        updateScreen();
    }

    public void onClickOperator(View v){
        Button b= (Button) v;
        display+=b.getText();
        currentOperator=b.getText().toString();
        updateScreen();
    }

    private double operateArithmetic(String a, String b, String op){
        switch (op){
            case "+": return(Double.valueOf(a) + Double.valueOf(b));
            case "-": return(Double.valueOf(a) - Double.valueOf(b));
            case "*": return(Double.valueOf(a) * Double.valueOf(b));
            case "/": try{
                return(Double.valueOf(a) / Double.valueOf(b));
            } catch(Exception e){
                Log.d("Calc", e.getMessage());
            }
            default: return -1;
        }
    }

    private double operateTrigonometric(String a, String op){
        switch (op){
            case "SIN": return(Math.sin(Double.valueOf(a)));
            case "COS": return(Math.cos(Double.valueOf(a)));
            case "TAN": try{
                return(Math.tan(Double.valueOf(a)));
            }catch(Exception e){
                Log.d("Calc", e.getMessage());
            }
            case "sqrt": return(Math.sqrt(Double.valueOf(a)));
            default: return -1;
        }
    }

    public void onClickEqual(View v){
        String[] operation=display.split(Pattern.quote(currentOperator));
        Double result;
        if(operation.length==1) {
            result = operateTrigonometric(operation[0], currentOperator);
            _screen.setText(display + "\n" + String.valueOf(result)+"\n Happy Valentine's Day");
        }

        else if (operation.length<2)
            return;

        else {
            result = operateArithmetic(operation[0], operation[1], currentOperator);
            _screen.setText(display + "\n" + String.valueOf(result)+"\n Happy Valentine's Day");
        }
    }

    private void clear(){
        display="";
        currentOperator="";
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }

}


