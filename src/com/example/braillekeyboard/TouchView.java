package com.example.braillekeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Vibrator;
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
	private boolean m_isVibrator;
	private Vibrator vibrator;
	private String result;

	public void setVibrator(Vibrator vib) {
		this.vibrator = vib;
	}

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
			
			/*if ((paramMotionEvent.getX() <= this.width / 2 + 10)
					&& (paramMotionEvent.getX() >= this.width / 2 - 10)
					&& (paramMotionEvent.getY() <= this.height / 2 + 10+editTextHeight)
					&& (paramMotionEvent.getY() >= this.height / 2 - 10+editTextHeight)) {

				
				this.vibrator.vibrate(1000);
				
				break;
			}*/
			break;

		case MotionEvent.ACTION_UP:

			upPositionX = (int) paramMotionEvent.getX();
			upPositionY = (int) paramMotionEvent.getY();

			
			
			if ((downPositionX - upPositionX >= this.width / 2) // 왼쪽으로 움직이기
																// backspace
					&& (downPositionY - downPositionY < 20 || downPositionY
							- downPositionY > -20)) {
				this.vibrator.vibrate(100);
				break;
			} else if ((upPositionX - downPositionX >= this.width / 2) // 오른쪽으로
																		// 움직이기
																		// 입력하기
					&& (downPositionY - downPositionY < 20 || downPositionY
							- downPositionY > -20)) {
				this.vibrator.vibrate(100);

				result = setString();
				Log.d("result", "" + result);
				break;
			} else if (Math.abs(upPositionX - downPositionX) < 5
					&& Math.abs(downPositionY - downPositionY) < 5) {// no break

				for (int i = 0; i < rect.length; i++) {
					if (rect[i].contains((int) paramMotionEvent.getX(),
							((int) paramMotionEvent.getY() + editTextHeight))) {
						if (touched[i] == false) {// on
							btn[i].setBackgroundResource(R.drawable.on);
							this.vibrator.vibrate(80);
							touched[i] = true;
						} else {// off
							btn[i].setBackgroundResource(R.drawable.off);
							touched[i] = false;
						}
					}
				}

			}

			break;

		}

		return true;
	}

	// String 으로 변환
	public String setString() {

		String str = new String();

		for (int i = 0; i < touched.length; i++) {
			if (touched[i] == true)
				str = str + "1";
			else
				str = str + "0";
		}

		return str;
	}

}
