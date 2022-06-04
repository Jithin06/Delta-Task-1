package com.example.inductiontask1;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<Integer> choices;
    List<String> signs;
    List<Double> answers;
    String selectedAnswer = "";
    String[] operators = {"+", "-", "x", "/"};
    GridLayout grid1;
    GridLayout grid2;
    GridLayout grid3;
    GridLayout grid4;
    GridLayout grid5;
    GridLayout grid6;
    GridLayout grid7;
    int lives = 3;
    int score = 0;
    TextView scorebox;
    TextView livesbox;

    public void newPuzzle() {
        choices = new ArrayList<>();
        signs = new ArrayList<>();
        answers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int rand_int = new Random().nextInt(50) + 1;
            choices.add(rand_int);
        }
        Log.i("choices", String.valueOf(choices));

        for (int i = 0; i < grid2.getChildCount(); i++) {
            TextView cell2 = (TextView) grid2.getChildAt(i);
            int rand = new Random().nextInt(4);
            signs.add(operators[rand]);
            cell2.setText(operators[rand]);
        }
        Log.i("signs", String.valueOf(signs));


        for (int i = 0; i < 5; i++) {
            double operand1 = choices.get(2 * i);
            double operand2 = choices.get((2 * i) + 1);

            if (signs.get(i).equals("+")) {
                double sum = operand1 + operand2;
                answers.add(sum);
            } else if (signs.get(i).equals("-")) {
                double difference = operand1 - operand2;
                answers.add(difference);
            } else if (signs.get(i).equals("x")) {
                double product = operand1 * operand2;
                answers.add(product);
            } else if (signs.get(i).equals("/")) {
                double quotient = operand1 / operand2;
                answers.add(quotient);
            }
        }
        Log.i("answers", String.valueOf(answers));

        for (int i = 0; i < grid4.getChildCount(); i++) {
            TextView cell4 = (TextView) grid4.getChildAt(i);
            cell4.setText("=");
        }

        for (int i = 0; i < 5; i++) {
            TextView cell5 = (TextView) grid5.getChildAt(i);
            double d = answers.get(i);
            cell5.setText(String.format("%.2f", d));
        }

        List<Integer> temp = new ArrayList<>(choices);
        Collections.shuffle(temp);
        for (int i = 0; i < 5; i++) {
            Button cell6 = (Button) grid6.getChildAt(i);
            cell6.setText(String.valueOf(temp.get(i)));
            cell6.setTag(String.valueOf(temp.get(i)));
            Button cell7 = (Button) grid7.getChildAt(i);
            cell7.setText(String.valueOf(temp.get(i + 5)));
            cell7.setTag(String.valueOf(temp.get(i + 5)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate: Started.");

        grid1 = (GridLayout) findViewById(R.id.grid1);
        grid2 = (GridLayout) findViewById(R.id.grid2);
        grid3 = (GridLayout) findViewById(R.id.grid3);
        grid4 = (GridLayout) findViewById(R.id.grid4);
        grid5 = (GridLayout) findViewById(R.id.grid5);
        grid6 = (GridLayout) findViewById(R.id.grid6);
        grid7 = (GridLayout) findViewById(R.id.grid7);
        livesbox = (TextView) findViewById(R.id.livesbox);
        livesbox.setText("Lives: " + String.valueOf(lives));
        scorebox = (TextView) findViewById(R.id.scorebox);
        scorebox.setText("Score: " + String.valueOf(score));
        for (int i = 0; i < 5; i++) {
            Button cell1 = (Button) grid1.getChildAt(i);
            Button cell3 = (Button) grid3.getChildAt(i);
            cell1.setText("");
            cell3.setText("");
        }
        newPuzzle();
    }

    public void onOptionClick(View view) {
        Button button = (Button) view;
        if (selectedAnswer.equals("")) {
            view.setBackgroundColor(Color.GRAY);
            selectedAnswer = (String) button.getText();
            ((Button) view).setText("");
            for (int i = 0; i < 5; i++) {
                Button cell6 = (Button) grid6.getChildAt(i);
                Button cell7 = (Button) grid7.getChildAt(i);
                cell6.setClickable(false);
                cell7.setClickable(false);
                Button cell1 = (Button) grid1.getChildAt(i);
                Button cell3 = (Button) grid3.getChildAt(i);
                cell1.setClickable(true);
                cell3.setClickable(true);
            }
        } else {
            view.setBackgroundColor(Color.WHITE);
            do {
                button.setText(selectedAnswer);
                selectedAnswer = "";
            } while (button.getText().equals(""));
            for (int i = 0; i < 5; i++) {
                Button cell6 = (Button) grid6.getChildAt(i);
                Button cell7 = (Button) grid7.getChildAt(i);
                cell6.setClickable(true);
                cell7.setClickable(true);
                Button cell1 = (Button) grid1.getChildAt(i);
                Button cell3 = (Button) grid3.getChildAt(i);
                cell1.setClickable(true);
                cell3.setClickable(true);
            }
        }

    }

    public void onChoiceClick(View view) {
        Button button = (Button) view;
        if (selectedAnswer.equals("")) {
            selectedAnswer = (String) button.getText();
            view.setBackgroundColor(0xBDBCBC);
            button.setText("");
            for (int i = 0; i < 5; i++) {
                Button cell6 = (Button) grid6.getChildAt(i);
                Button cell7 = (Button) grid7.getChildAt(i);
                if (cell6.getTag()==selectedAnswer)
                {
                    cell6.setText(selectedAnswer);
                    cell6.setBackgroundColor(Color.WHITE);
                    selectedAnswer = "";
                }
                else if (cell7.getTag()==selectedAnswer)
                {
                    cell7.setText(selectedAnswer);
                    cell7.setBackgroundColor(Color.WHITE);
                    selectedAnswer = "";
                }
            }
            for (int i = 0; i < 5; i++) {
                Button cell6 = (Button) grid6.getChildAt(i);
                Button cell7 = (Button) grid7.getChildAt(i);
                cell6.setClickable(true);
                cell7.setClickable(true);
                Button cell1 = (Button) grid1.getChildAt(i);
                Button cell3 = (Button) grid3.getChildAt(i);
                cell1.setClickable(true);
                cell3.setClickable(true);
            }
        } else {
            view.setBackgroundColor(Color.WHITE);
            do {
                button.setText(selectedAnswer);
                selectedAnswer = "";
            } while (button.getText().equals(""));
            for (int i = 0; i < 5; i++) {
                Button cell6 = (Button) grid6.getChildAt(i);
                Button cell7 = (Button) grid7.getChildAt(i);
                cell6.setClickable(true);
                cell7.setClickable(true);
                Button cell1 = (Button) grid1.getChildAt(i);
                Button cell3 = (Button) grid3.getChildAt(i);
                cell1.setClickable(true);
                cell3.setClickable(true);
            }
        }
    }
    public void ResetChoices() {
        for (int i = 0; i < 5; i++) {
            Button cell1 = (Button) grid1.getChildAt(i);
            Button cell3 = (Button) grid3.getChildAt(i);
            Button cell6 = (Button) grid6.getChildAt(i);
            Button cell7 = (Button) grid7.getChildAt(i);
            cell1.setBackgroundColor(0xBDBCBC);
            cell3.setBackgroundColor(0xBDBCBC);
            cell6.setBackgroundColor(Color.WHITE);
            cell7.setBackgroundColor(Color.WHITE);
            cell1.setText("");
            cell3.setText("");
        }
    }

    public void onSubmitClick(View view) {
        for (int i = 0; i < 5; i++) {
            Button cell1 = (Button) grid1.getChildAt(i);
            Button cell3 = (Button) grid3.getChildAt(i);
            TextView cell2 = (TextView) grid2.getChildAt(i);
            if (((cell1.getText()).equals(String.valueOf(choices.get(2 * i)))) && ((cell3.getText()).equals(String.valueOf(choices.get((2 * i) + 1))))) {
                score++;
                scorebox = (TextView) findViewById(R.id.scorebox);
                scorebox.setText("Score: " + String.valueOf(score));
            }
            else if (((cell1.getText()).equals(String.valueOf(choices.get((2 * i) + 1)))) && ((cell3.getText()).equals(String.valueOf(choices.get(2 * i))))) {
                if (cell2.getText()=="+" || cell2.getText()=="x") {
                    score++;
                    scorebox = (TextView) findViewById(R.id.scorebox);
                    scorebox.setText("Score: " + String.valueOf(score));
                }
            }

            else {

                if (lives > 0) {
                    lives--;
                }
                else {
                    lives = 3;
                    score = 0;
                }
                livesbox = (TextView) findViewById(R.id.livesbox);
                livesbox.setText("Lives: " + String.valueOf(lives));
                break;
            }
        }
        ResetChoices();
        newPuzzle();
    }
}