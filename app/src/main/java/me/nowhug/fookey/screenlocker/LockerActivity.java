package me.nowhug.fookey.screenlocker;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

public class LockerActivity extends Activity
{

	private MyView myView;
    public int pt0X,pt1X,pt0Y,pt1Y;
    private double _lastDistance = 0;
    public static double dtDistance=0;
    public static boolean LOCK=true;
    private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        mContext=getApplicationContext();
		super.onCreate(savedInstanceState);
		myView = new MyView(this);
		WindowManager windowManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
		/*setContentView(R.layout.activity_test);*/
		WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
		/*layoutParams.type= WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		layoutParams.format= PixelFormat.RGBA_8888;*/
		layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
		layoutParams.height= WindowManager.LayoutParams.MATCH_PARENT;

		windowManager.addView(myView,layoutParams);
		// ���������
		/*myView.setOnTouchListener(this);*/

		// ���ó���ģʽ


		//setContentView(_touchView);
	}



	/*
	public void onAttachedToWindow() {
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
		super.onAttachedToWindow();
	}*/



    public boolean onKeyDown(int keyCode ,KeyEvent event){

		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			return true ;
		}
		if (event.getKeyCode()== KeyEvent.KEYCODE_SEARCH)
		{
			return true;
		}
		if (event.getKeyCode()== KeyEvent.KEYCODE_HOME)
		{
			return true;
		}
			return super.onKeyDown(keyCode, event);

	}


    class MyView extends View implements View.OnTouchListener{




        public MyView(Context context) {
            super(context);
            this.setClickable(true);
            this.setLongClickable(true);
            this.setOnTouchListener(this);

        }
        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);

            canvas.drawColor(Color.WHITE);

            Paint paint = new Paint();

            paint.setAntiAlias(true);


            paint.setStyle(Paint.Style.FILL);

            paint.setColor(Color.BLUE);


            canvas.drawCircle(pt0X, pt0Y, 30, paint);

            canvas.drawRect(pt1X - 30, pt1Y - 30, pt1X + 30, pt1Y + 30, paint);
            paint.setColor(Color.RED);
            canvas.drawLine(pt0X, pt0Y, pt1X, pt1Y, paint);

            paint.setTextSize(14);
            canvas.drawText("xy1: " + pt0X + "; " + pt0Y, 80, 50, paint);
            canvas.drawText("xy2: " + pt1X + "; " + pt1Y, 80, 120, paint);
            canvas.drawText("dtDistance " +dtDistance, 80, 200, paint);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            // ��ȡ��������
            int points = event.getPointerCount();

            switch (event.getAction())
            {
                // �����¼�
                case MotionEvent.ACTION_DOWN:
                {
                    break;
                }
                // �ƶ��¼�
                case MotionEvent.ACTION_MOVE:
                {
                    System.out.println("ACTION_MOVE");


                    // ���������Ϊ2ʱ
                    if(points == 2)
                    {
                        pt0X = (int) event.getX(0);
                        pt0Y = (int) event.getY(0);

                        pt1X = (int) event.getX(1);
                        pt1Y = (int) event.getY(1);

                        //	_touchView = new MyView(this);
                        //	setContentView(_touchView);
                        this.invalidate();
                        // ����˫ָ���ż��

                        zoomProcess(pt0X, pt0Y, pt1X, pt1Y);

                        // ����˫ָ��ת���
                        //rotationProcess(pt0X, pt0Y, pt1X, pt1Y);
                    }
                    //else{Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();}

                    break;
                }

            }

            return false;
        }

        public void zoomProcess(int pt0X, int pt0Y, int pt1X, int pt1Y) {
            double curDistance = 0;  // ��ǰ����
            //double dtDistance  = 0;	 // ������
            double range = 0;  // ����

            // ������������֮�䵱ǰ�ľ���
            curDistance = Math.sqrt(Math.pow((pt1X - pt0X), 2) + Math.pow((pt1Y - pt0Y), 2));

            // �������ϴεľ����
            dtDistance = curDistance - _lastDistance;

            // �������仯�ﵽһ������
		/*if(Math.abs(dtDistance) > range)
		{
			if(dtDistance > 0)
			{
				// �Ŵ�
				//_stateText.setText("�Ŵ� " + String.valueOf(dtDistance));
				Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
				// �������
				finish();
			}
			else {
				_stateText.setText("����");
			}

		}*/

            int x = pt1X - pt0X;
            int y = pt0Y - pt1Y;
            if ((x * x) > (y * y)) {
            Toast.makeText(mContext, "OK", Toast.LENGTH_SHORT).show();


                finish();


            } else {
            Toast.makeText(mContext, "ERROR!", Toast.LENGTH_SHORT).show();

            }
            // ���¾���
            _lastDistance = curDistance;
        }

    }

}
