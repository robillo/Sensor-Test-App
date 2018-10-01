package com.appbusters.robinkamboj.senseitall.view.tool_activity.everyday_tools.calculator.calc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;

import com.appbusters.robinkamboj.senseitall.R;

public class CustomCalcEraseButton  extends AppCompatImageView {

    private static final String TAG = CustomCalcEraseButton.class.getSimpleName();

    private static final int NO_HOLD_ERASE = -1;

    private int eraseHoldDelay;
    private int eraseHoldSpeed;
    private boolean eraseAllOnHold;

    private final Handler eraseHandler;
    private final Runnable eraseRunnable;
    private boolean clickingDown;

    private @Nullable
    CustomCalcEraseButton.EraseListener listener;

    public CustomCalcEraseButton(Context context) {
        this(context, null, 0);
    }

    public CustomCalcEraseButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalcEraseButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // Get speed attributes
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalcEraseButton);
        eraseHoldDelay = ta.getInt(R.styleable.CalcEraseButton_calcEraseBtnHoldDelay, 750);
        eraseHoldSpeed = ta.getInt(R.styleable.CalcEraseButton_calcEraseBtnHoldSpeed, 100);
        eraseAllOnHold = ta.getBoolean(R.styleable.CalcEraseButton_calcEraseAllOnHold, false);
        ta.recycle();

        eraseHandler = new Handler();
        eraseRunnable = new Runnable() {
            @Override
            public void run() {
                if (listener != null && clickingDown) {
                    if (eraseAllOnHold) {
                        listener.onEraseAll();
                    } else {
                        listener.onErase();
                        eraseHandler.postDelayed(eraseRunnable, eraseHoldSpeed);
                    }
                }
            }
        };
    }

    public interface EraseListener {
        void onErase();
        void onEraseAll();
    }

    public void setOnEraseListener(@Nullable CustomCalcEraseButton.EraseListener listener) {
        this.listener = listener;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean superReturn = super.onTouchEvent(event);  // does performClick(), so ignore warning

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (listener != null && eraseHoldDelay != NO_HOLD_ERASE) {
                eraseHandler.removeCallbacks(eraseRunnable);
            }
            clickingDown = false;
            return true;

        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            clickingDown = true;

            if (listener != null) {
                if (eraseHoldDelay != NO_HOLD_ERASE) {
                    eraseHandler.postDelayed(eraseRunnable, eraseHoldDelay);
                    eraseHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (clickingDown) {
                                performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                            }
                        }
                    }, eraseHoldDelay);
                }

                if (eraseHoldDelay != 0) {
                    listener.onErase();
                }
            }
            return true;
        }

        return superReturn;
    }

}
