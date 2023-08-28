package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, solution_tv;

    MaterialButton button_c, button_open_bracket,button_close_bracket,button_divide;
    MaterialButton button_7,button_8,button_9,button_mul;
    MaterialButton button_4,button_5 , button_6,button_plus;
    MaterialButton button_3,button_2,button_1,button_minus;

    MaterialButton  button_AC,button_dot,button_0,button_equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);

        assignId(button_c , R.id.button_c);
        assignId(button_0 , R.id.button_0);
        assignId(button_1 , R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9 , R.id.button_9);
        assignId(button_AC, R.id.button_AC);
        assignId(button_dot,R.id.button_dot);
        assignId(button_divide,R.id.button_divide);
        assignId(button_mul,R.id.button_mul);
        assignId(button_plus , R.id.button_plus);
        assignId(button_minus , R.id.button_minus);

    }

    void assignId(MaterialButton btn , int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttontext=button.getText().toString();
        String datatocalcu = solution_tv.getText().toString();

        if(buttontext.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return ;
        }
        if(buttontext.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }

        if(buttontext.equals("C")){
            datatocalcu=datatocalcu.substring(0 , datatocalcu.length()-1);
        }  else{
            datatocalcu=datatocalcu+buttontext;
        }

//        datatocalcu=datatocalcu+buttontext;
        solution_tv.setText(datatocalcu);

        String finalResult=getResult(datatocalcu);

        if(!finalResult.equals("Err")){

           if(!finalResult.equals("Err")){
               result_tv.setText(finalResult);
           }
        }
    }

    String getResult(String data){
       try {
          Context context=Context.enter();
          context.setOptimizationLevel(-1);
          Scriptable scriptable=context.initStandardObjects();
          String finalresult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();

          if(finalresult.endsWith(".0")){
              finalresult=finalresult.replace(".0","");
          }
          return finalresult;
       } catch (Exception e){
           return "Err";
       }

    }
}