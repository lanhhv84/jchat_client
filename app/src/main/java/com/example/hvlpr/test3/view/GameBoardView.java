package com.example.hvlpr.test3.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.hvlpr.test3.R;

import java.util.ArrayList;
import java.util.List;

public class GameBoardView extends GridLayout {
    private static final int ENEMY_STEP = 3;
    private static int N_ROWS;
    private static int N_COLS;
    private static final int WIN_COUNT = 5;
    private List<List<Button>> lstButton;
    private boolean userTurn = true;
    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        N_COLS = getColumnCount();
        N_ROWS = getRowCount();
        lstButton = new ArrayList<>();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.gameboard_view, this);

        float dip = 31;
        Resources r = getResources();
        int px = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );

        int px2 = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                1,
                r.getDisplayMetrics()
        );

        for (int i = 0 ; i < getRowCount(); ++i) {
            for (int j = 0; j < getColumnCount(); ++j) {
                lstButton.add(new ArrayList<>());
                Button btn = new Button(new ContextThemeWrapper(context, R.style.Theme_GameButtonStyle), null, R.style.Theme_GameButtonStyle);
                btn.setOnClickListener((View view) -> onMove(view));
                btn.setTag(R.integer.STEP, R.integer.NONE_STEP);
                btn.setTag(R.integer.ROW_KEY, i);
                btn.setTag(R.integer.COLUMN_KEY, j);
                lstButton.get(i).add(btn);
                this.addView(btn, new LayoutParams(GridLayout.spec(i), GridLayout.spec(j)));
            }
        }

        int[] sets = {};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        typedArray.recycle();
        initComponents();
    }

    public void onMove(View view) {
        Button btn = (Button)view;

        int column = (int)btn.getTag(R.integer.COLUMN_KEY);
        int row = (int)btn.getTag(R.integer.ROW_KEY);

        if (userTurn) {
            if (userMove(row, column)) {
                btn.setBackgroundResource(R.color.user_color);
                userTurn = !userTurn;
            }

        }
        else {
            if (enemyMove(row, column)) {
                btn.setBackgroundResource(R.color.enemy_color);
                userTurn = !userTurn;
            }

        }

        if (isWon(row, column)) {
            // TODO: Display notification in both devices
            // TODO: Suggest to play new game, if so clear current game table

            Snackbar.make(view, "You win", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }

    }

    private void initComponents() {

    }

    public boolean isWon(int row, int column) {
        boolean won = isWonColumn(row, column) || isWonRow(row, column) || isWonLeft(row, column) || isWonRight(row, column);
        if (won) {
            // TODO: Send signal to enemy
        }
        return won;
    }

    private boolean isWonRow(int row, int column) {
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP) {
            return 1 + isWonRowD(row + 1, column) + isWonRowT(row - 1, column) >= WIN_COUNT;
        }
        return false;
    }

    private int isWonRowT(int row, int column) {

        if (row < 0) {
            return 0;
        }

        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP) {
            return 1 + isWonRowT(row - 1, column);
        }
        else {
            return 0;
        }
    }

    private int isWonRowD(int row, int column) {
        if (row >= N_ROWS)
            return 0;
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + isWonRowD(row + 1, column);
        return 0;
    }

    private boolean isWonColumn(int row, int column) {
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + isWonColumnL(row , column - 1) +
                    isWonColumnR(row , column + 1) >= WIN_COUNT;
        return false;
    }

    private int isWonColumnL(int row, int column) {
        if (column < 0)
            return 0;
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + isWonColumnL(row, column - 1);
        return 0;
    }

    private int isWonColumnR(int row, int column) {
        if (column < 0)
            return 0;
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + this.isWonColumnR(row, column + 1);
        return 0;
    }

    private boolean isWonLeft(int row, int column) {
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP) {
            return 1 + isWonLeftL(row - 1, column - 1) +
                    isWonLeftR(row + 1, column + 1) >= WIN_COUNT;
        }
        return false;
    }

    private boolean isWonRight(int row, int column) {
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP) {
            return 1 + isWonRightL(row + 1, column - 1) +
                    isWonRightR(row - 1, column + 1) >= WIN_COUNT;
        }
        return false;
    }

    private int isWonLeftL(int row, int column) {
        if (column < 0 || row < 0)
            return 0;
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + isWonLeftL(row - 1, column - 1);
        return 0;
    }

    private int isWonLeftR(int row, int column) {
        if (column >= N_COLS || row >= N_ROWS)
            return 0;
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + isWonLeftR(row + 1, column + 1);
        return 0;
    }

    private int isWonRightL(int row, int column) {
        if (column < 0 || row >= N_ROWS)
            return 0;
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + isWonRightL(row + 1, column - 1);
        return 0;
    }

    private int isWonRightR(int row, int column) {
        if (row < 0 || column >= N_COLS)
            return 0;
        if ((int)lstButton.get(row).get(column).getTag(R.integer.STEP) == R.integer.USER_STEP)
            return 1 + isWonRightR(row - 1, column + 1);
        return 0;
    }

    public boolean userMove(int row, int column) {
        Button btn = this.lstButton.get(row).get(column);
        if ((int)btn.getTag(R.integer.STEP) == R.integer.NONE_STEP) {
            btn.setTag(R.integer.STEP, R.integer.USER_STEP);
            // TODO: Send signal to enemy
            return true;
        }
        return false;
    }


    public boolean enemyMove(int row, int column) {
        // Stimulate
        // TODO: Receive signal from other
        Button btn = this.lstButton.get(row).get(column);
        if ((int)btn.getTag(R.integer.STEP) == R.integer.NONE_STEP) {
            btn.setTag(R.integer.STEP, R.integer.ENEMY_STEP);
            //
            return true;
        }
        return false;
    }
}
