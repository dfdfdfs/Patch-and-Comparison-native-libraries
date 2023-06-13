package com.nativee.patcher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;
import android.view.View.OnKeyListener;
import android.view.GestureDetector.OnGestureListener;

import androidx.appcompat.widget.AppCompatEditText;

public class CD extends androidx.appcompat.widget.AppCompatEditText implements Constants {

    private Rect A, B;
    private Paint C, D;
    protected Point I;
    //  final CD me;
    protected float V;
    protected int VV = 6;
    protected int VVV, VVVV;
    protected int VVVVV;
    protected int VVVVVV;
   // protected GestureDetector mGestureDetector;
    protected Scroller VVVVVVV;

    public CD(Context context, AttributeSet attrs) {
        super(context, attrs);
        C = new Paint();
        C.setTypeface(Typeface.MONOSPACE);
        C.setAntiAlias(true);

        D = new Paint();

        V = context.getResources().getDisplayMetrics().density;
        VVV = (int) (VV * V);

        VVVVV = VVVVVV = -1;

        A = new Rect();
        B = new Rect();

        // mGestureDetector = new GestureDetector(getContext(), (OnGestureListener) this);

        updateFromSettings();
    }


    @Override
    public void onDraw(Canvas canvas) {
        int count, lineX, baseline;

        count = getLineCount();

        if (Settings.SHOW_LINE_NUMBERS) {
            int padding = (int) (Math.floor(Math.log10(count)) + 1);
            padding = (int) ((padding * C.getTextSize()) + VVV + (Settings.TEXT_SIZE
                    * V * 0.5));
            if (VVVV != padding) {
                VVVV = padding;
                setPadding(VVVV, VVV, VVV, VVV);
            }
        }

        // get the drawing boundaries
        getDrawingRect(A);

        // display current line
        computeLineHighlight();

        // draw line numbers
        lineX = (int) (A.left + VVVV - (Settings.TEXT_SIZE
                * V * 0.5));
        int min = 0;
        int max = count;
        getLineBounds(0, B);
        int startBottom = B.bottom;
        int startTop = B.top;
        getLineBounds(count - 1, B);
        int endBottom = B.bottom;
        int endTop = B.top;
        if (count > 1 && endBottom > startBottom && endTop > startTop) {
            min = Math.max(min, ((A.top - startBottom) * (count - 1)) / (endBottom - startBottom));
            max = Math.min(max, ((A.bottom - startTop) * (count - 1)) / (endTop - startTop) + 1);
        }
        for (int i = min; i < max; i++) {
            baseline = getLineBounds(i, B);
            if ((I != null) && (I.x < B.right)) {
                I.x = B.right;
            }

            if ((i == VVVVV) && (!Settings.WORDWRAP)) {
                canvas.drawRect(B, D);
            }

            if (Settings.SHOW_LINE_NUMBERS) {
                canvas.drawText("" + (i + 1), A.left + VVV,
                        baseline, C);
            }
            if (Settings.SHOW_LINE_NUMBERS) {
                canvas.drawLine(lineX, A.top, lineX,
                        A.bottom, C);
            }
        }
        getLineBounds(count - 1, B);
        if (I != null) {
            I.y = B.bottom;
            I.x = Math.max(I.x + VVV - A.width(),
                    0);
            I.y = Math.max(
                    I.y + VVV - A.height(), 0);
        }

        super.onDraw(canvas);
    }




    public void updateFromSettings() {

        if (isInEditMode()) {
            return;
        }

        setTypeface(Settings.getTypeface(getContext()));

        // wordwrap
        setHorizontallyScrolling(!Settings.WORDWRAP);

        // color Theme
        switch (Settings.COLOR) {
            case COLOR_NEGATIVE:
                setBackgroundResource(R.drawable.textfield_black);
                setTextColor(Color.WHITE);
                D.setColor(Color.WHITE);
                C.setColor(Color.GRAY);
                break;
            case COLOR_MATRIX:
                setBackgroundResource(R.drawable.textfield_matrix);
                setTextColor(Color.GREEN);
                D.setColor(Color.GREEN);
                C.setColor(Color.rgb(0, 128, 0));
                break;
            case COLOR_SKY:
                setBackgroundResource(R.drawable.textfield_sky);
                setTextColor(Color.rgb(0, 0, 64));
                D.setColor(Color.rgb(0, 0, 64));
                C.setColor(Color.rgb(0, 128, 255));
                break;
            case COLOR_DRACULA:
                setBackgroundResource(R.drawable.textfield_dracula);
                setTextColor(Color.RED);
                D.setColor(Color.RED);
                C.setColor(Color.rgb(192, 0, 0));
                break;
            case COLOR_CLASSIC:
            default:
                setBackgroundResource(R.drawable.textfield_white);
                setTextColor(Color.BLACK);
                D.setColor(Color.BLACK);
                C.setColor(Color.GRAY);
                break;
        }
        D.setAlpha(48);

        // text size
        setTextSize(Settings.TEXT_SIZE);
        C.setTextSize(Settings.TEXT_SIZE * V * 0.85f);

        // refresh view
        postInvalidate();
        refreshDrawableState();

        // use Fling when scrolling settings ?
        if (Settings.FLING_TO_SCROLL) {
            VVVVVVV = new Scroller(getContext());
            I = new Point();
        } else {
            VVVVVVV = null;
            I = null;
        }

        // padding
        VVVV = VVV;
        int count = getLineCount();
        if (Settings.SHOW_LINE_NUMBERS) {
            VVVV = (int) (Math.floor(Math.log10(count)) + 1);
            VVVV = (int) ((VVVV * C.getTextSize())
                    + VVV + (Settings.TEXT_SIZE * V * 0.5));
            setPadding(VVVV, VVV, VVV, VVV);
        } else {
            setPadding(VVV, VVV, VVV, VVV);
        }
    }

    protected void computeLineHighlight() {
        int i, line, selStart;
        String text;

        if (!isEnabled()) {
            VVVVV = -1;
            return;
        }

        selStart = getSelectionStart();
        if (VVVVVV != selStart) {
            text = getText().toString();

            line = i = 0;
            while (i < selStart) {
                i = text.indexOf("\n", i);
                if (i < 0) {
                    break;
                }
                if (i < selStart) {
                    ++line;
                }
                ++i;
            }

            VVVVV = line;
        }
    }


}