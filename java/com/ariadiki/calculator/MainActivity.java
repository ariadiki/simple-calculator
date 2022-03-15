package com.ariadiki.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
SwitchCompat switchMode;
TextView txt_math,txt_result;
StringBuilder builder;
//MaterialRippleLayout c,delete,point,zero,one,two,three,four,five,six,seven,eight,nine,add,subtract,multiply,divide,percent,reverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.clear_text).setOnClickListener(this);
        findViewById(R.id.delete_text).setOnClickListener(this);
        findViewById(R.id.point).setOnClickListener(this);
        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.subtract).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.divide).setOnClickListener(this);
        findViewById(R.id.percent).setOnClickListener(this);
        findViewById(R.id.reverse).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);

        txt_math = findViewById(R.id.txt_math);
        txt_result = findViewById(R.id.txt_result);
        switchMode = findViewById(R.id.switchMode);

        builder = new StringBuilder();

        //dark mode / light mode
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchMode.setText("Light Mode  ");
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchMode.setText("Dark Mode  ");
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.clear_text:
                builder.delete(0,builder.length());
                txt_result.setText("0");
                break;
            case R.id.delete_text:
                if(builder.length()<1){}
                else
                    builder.delete(builder.length()-1,builder.length());
                break;
            case R.id.point:
                builder.append(".");
                break;
            case R.id.zero:
                builder.append("0");
                break;
            case R.id.one:
                builder.append("1");
                break;
            case R.id.two:
                builder.append("2");
                break;
            case R.id.three:
                builder.append("3");
                break;
            case R.id.four:
                builder.append("4");
                break;
            case R.id.five:
                builder.append("5");
                break;
            case R.id.six:
                builder.append("6");
                break;
            case R.id.seven:
                builder.append("7");
                break;
            case R.id.eight:
                builder.append("8");
                break;
            case R.id.nine:
                builder.append("9");
                break;
            case R.id.add:
                builder.append(" + ");
                break;
            case R.id.subtract:
                builder.append(" - ");
                break;
            case R.id.multiply:
                builder.append(" x ");
                break;
            case R.id.divide:
                builder.append(" / ");
                break;
            case R.id.equal:
                try {
                    String []arr = String.valueOf(builder).split(" ");
                    double res = Double.valueOf(arr[0]);
                    for (int i = 1; i < arr.length; i++) {
                        switch (arr[i])
                        {
                            case "+":
                                res += Double.valueOf(arr[i+1]);
                                txt_result.setText(String.valueOf(res));
                                break;
                            case "-":
                                res -= Double.valueOf(arr[i+1]);
                                txt_result.setText(String.valueOf(res));
                                break;
                            case "x":
                                res *= Double.valueOf(arr[i+1]);
                                txt_result.setText(String.valueOf(res));
                                break;
                            case "/":
                                if (Double.valueOf(arr[i+1])!= 0)
                                    res /= Double.valueOf(arr[i+1]);
                                else
                                    txt_result.setText("Can't divide by zero");
                                break;
                            default:
                                txt_result.setText(String.valueOf(res));
                                break;
                        }
                    }
                }catch (Exception ex){
                    Toast.makeText(this,"Error: "+ex, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.percent:
                String []arr = String.valueOf(builder).split(" ");
                double res = 0.0;
                if(Double.valueOf(txt_result.getText().toString()) != 0)
                    res = Double.valueOf(txt_result.getText().toString()) /100;
                else
                    res = Double.valueOf(arr[arr.length-1]) /100;
                txt_result.setText(String.valueOf(res));
                break;
            case R.id.reverse:
                break;
        }
        txt_math.setText(builder);
    }
}