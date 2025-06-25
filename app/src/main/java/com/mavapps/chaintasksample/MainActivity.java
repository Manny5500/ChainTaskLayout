package com.mavapps.chaintasksample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView circleNumberTopLeft;
    TextView circleNumberTopMiddle;
    TextView circleNumberTopRight;
    TextView circleNumberBottomLeft;
    TextView circleNumberBottomMiddle;
    TextView circleNumberBottomRight;

    ConstraintLayout circleLineTopLeft;
    ConstraintLayout circleLineTopMiddle1;
    ConstraintLayout circleLineTopMiddle2;
    ConstraintLayout circleLineTopRight;
    ConstraintLayout circleLineBottomLeft;
    ConstraintLayout circleLineBottomMiddle1;
    ConstraintLayout circleLineBottomMiddle2;
    ConstraintLayout circleLineBottomRight;


    Button btnPrev;
    Button btnNext;

    TextView textDescription;

    int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleNumberTopLeft = findViewById(R.id.circleNumberTopLeft);
        circleLineTopLeft = findViewById(R.id.circleLineTopLeft);
        circleLineTopMiddle1 = findViewById(R.id.circleLineTopMiddle1);
        circleNumberTopMiddle = findViewById(R.id.circleNumberTopMiddle);
        circleNumberTopRight = findViewById(R.id.circleNumberTopRight);
        circleLineTopMiddle2 = findViewById(R.id.circleLineTopMiddle2);
        circleLineTopRight = findViewById(R.id.circleLineTopRight);
        circleLineBottomMiddle1 = findViewById(R.id.circleLineBottomMiddle1);
        circleLineBottomMiddle2 = findViewById(R.id.circleLineBottomMiddle2);
        circleNumberBottomLeft = findViewById(R.id.circleNumberBottomLeft);
        circleNumberBottomMiddle = findViewById(R.id.circleNumberBottomMiddle);
        circleNumberBottomRight = findViewById(R.id.circleNumberBottomRight);
        circleLineBottomLeft = findViewById(R.id.circleLineBottomLeft);
        circleLineBottomRight = findViewById(R.id.circleLineBottomRight);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        textDescription = findViewById(R.id.textDescription);



        //store sa arrays ang mga layouts para  dynamic
        TextView[] circles = new TextView[]{circleNumberTopLeft, circleNumberTopMiddle, circleNumberTopRight,
                circleNumberBottomLeft, circleNumberBottomMiddle, circleNumberBottomRight};

        ConstraintLayout[][] lines = new ConstraintLayout[][]{
                new ConstraintLayout[]{circleLineTopLeft, circleLineTopMiddle1},
                new ConstraintLayout[]{circleLineTopMiddle2, circleLineTopRight},
                new ConstraintLayout[]{},
                new ConstraintLayout[]{circleLineBottomLeft, circleLineBottomMiddle1},
                new ConstraintLayout[]{circleLineBottomMiddle2, circleLineBottomRight},
                new ConstraintLayout[]{}
        };

        String[] textDescriptions = new String[]{"Test1", "Test2", "Test3", "Test4", "Test5", "Test6"};


        //gumawa ng arraylist para sa routing
        List<TextView> circleList= new ArrayList();
        circleList.add(circleNumberTopLeft);

        List<ConstraintLayout[]> lineList= new ArrayList();
        lineList.add(lines[0]);


        btnPrev.setOnClickListener(view -> {
            if (currentIndex > 0) {
                unsetFocusSingle(circles[currentIndex], lines[currentIndex - 1]);

                if (!circleList.isEmpty()) {
                    circleList.remove(circleList.size() - 1);
                }
                if (!lineList.isEmpty()) {
                    lineList.remove(lineList.size() - 1);
                }

                currentIndex--;

                List<TextView> currentFocusCircles = new ArrayList<>();
                List<ConstraintLayout[]> currentFocusLines = new ArrayList<>();
                for (int i = 0; i <= currentIndex; i++) {
                    currentFocusCircles.add(circles[i]);
                    if (i < currentIndex) {
                        currentFocusLines.add(lines[i]);
                    }
                }

                setFocus(currentFocusCircles, currentFocusLines);

                if (currentIndex < textDescriptions.length) {
                    textDescription.setText(textDescriptions[currentIndex]);
                }
            }
        });


        btnNext.setOnClickListener(view -> {
            if (currentIndex < circles.length - 1) {
                currentIndex++;
                circleList.add(circles[currentIndex]);
                lineList.add(lines[currentIndex]);
            }
            setFocus(circleList, lineList);

            if (currentIndex < textDescriptions.length) {
                textDescription.setText(textDescriptions[currentIndex]);
            }
        });
    }

    private void firstFocus(){
        int focusedColor = Color.parseColor("#6750A4");
        circleNumberTopLeft.setBackgroundResource(R.drawable.circle_background);
        circleNumberTopLeft.setTextColor(Color.WHITE);
        circleLineTopLeft.setBackgroundColor(focusedColor);
        circleLineTopMiddle1.setBackgroundColor(focusedColor);
    }

    private void setFocus(
            List<TextView> circlesToFocus,
            List<ConstraintLayout[]> linesToFocus
    ) {
        int focusedColor = Color.parseColor("#6750A4");
        for (ConstraintLayout[] lineSet : linesToFocus) {
            for (ConstraintLayout line : lineSet) {
                if (line != null) {
                    line.setBackgroundColor(focusedColor);
                }
            }
        }
        for (TextView circle : circlesToFocus) {
            if (circle != null) {
                circle.setBackgroundResource(R.drawable.circle_background);
                circle.setTextColor(Color.WHITE);
            }
        }
    }

    private void unsetFocusSingle(TextView circle, ConstraintLayout[] associatedLines) {
        int unfocusedColor = Color.parseColor("#B39DFF");
        int textUnfocusedColor = Color.parseColor("#6750A4");

        if (circle != null) {
            circle.setBackgroundResource(R.drawable.circle_background_outlined);
            circle.setTextColor(textUnfocusedColor);
        }

        if (associatedLines != null) {
            for (ConstraintLayout line : associatedLines) {
                if (line != null) {
                    line.setBackgroundColor(unfocusedColor);
                }
            }
        }
    }

    private void unsetFocusPath(
                                 List<TextView> circlesToUnfocus,
                                 List<ConstraintLayout[]> linesToUnfocus
    ) {
        int unfocusedColor = Color.parseColor("#B39DFF");
        int textUnfocusedColor = Color.parseColor("#6750A4");

        for (ConstraintLayout[] lineSet : linesToUnfocus) {
            for (ConstraintLayout line : lineSet) {
                if (line != null) {
                    line.setBackgroundColor(unfocusedColor);
                }
            }
        }
        for (TextView circle : circlesToUnfocus) {
            if (circle != null) {
                circle.setBackgroundResource(R.drawable.circle_background_outlined);
                circle.setTextColor(textUnfocusedColor);
            }
        }
    }
}