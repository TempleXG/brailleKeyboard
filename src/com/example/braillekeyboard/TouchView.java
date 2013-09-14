package com.example.braillekeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TouchView extends View {

	private Rect rect[] = new Rect[6];
	private Button[] btn = new Button[6];
	private EditText editText;
	private int editTextHeight;
	private int downPositionX, downPositionY, upPositionX, upPositionY;
	private int arr[] = new int[6];
	private boolean touched[] = new boolean[6];
	private int height, width;

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setEditText(EditText editText) {
		this.editText = editText;
		editTextHeight = editText.getHeight();
	}

	public void setButton(Button[] btn) { // button

		for (int i = 0; i < rect.length; i++) {
			this.btn[i] = btn[i];
			rect[i] = setRect(btn[i]);
			Log.d(rect[i] + "", rect[i].height() + "");
		}
	}

	public Rect setRect(Button btn) {

		Rect a = new Rect();
		btn.getGlobalVisibleRect(a);
		return a;
	}

	public TouchView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		for (int i = 0; i < rect.length; i++) {
			rect[i] = new Rect();

		}

	}

	public TouchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 0; i < rect.length; i++) {
			rect[i] = new Rect();
		}

	}

	public void onDraw(Canvas paramCanvas) {

	}

	public boolean onTouchEvent(MotionEvent paramMotionEvent) {
		super.onTouchEvent(paramMotionEvent);

		switch (paramMotionEvent.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downPositionX = (int) paramMotionEvent.getX();
			downPositionY = (int) paramMotionEvent.getY();

			break;

		case MotionEvent.ACTION_MOVE:
			break;

		case MotionEvent.ACTION_UP:

			upPositionX = (int) paramMotionEvent.getX();
			upPositionY = (int) paramMotionEvent.getY();

			if ((downPositionX - upPositionX >= this.width / 2)
					&& (downPositionY - downPositionY < 20 || downPositionY
							- downPositionY > -20)) {
				Log.d("aa", "aa");
				break;
			} else if ((upPositionX - downPositionX >= this.width / 2)
					&& (downPositionY - downPositionY < 20 || downPositionY
							- downPositionY > -20)) {
				Log.d("dd", "dd");
				break;
			} else if (Math.abs(upPositionX - downPositionX) < 5
					&& Math.abs(downPositionY - downPositionY) < 5) {
				

				for (int i = 0; i < rect.length; i++) {
					if (rect[i].contains((int) paramMotionEvent.getX(),
							((int) paramMotionEvent.getY() + editTextHeight))) {
						if (touched[i] == false) {// on
							btn[i].setBackgroundResource(R.drawable.ic_launcher);
							touched[i] = true;
						} else {// off
							btn[i].setBackgroundResource(R.drawable.logo02);
							touched[i] = false;
						}
					}
				}

			}

			break;

		}

		return true;
	}

	public int[] setInt() {

		return arr;
	}

}
