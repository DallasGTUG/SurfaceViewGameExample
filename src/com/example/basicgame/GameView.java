package com.example.basicgame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private GameThread thread; 
	private GameCore core; 
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
        // register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // create thread only
        thread = new GameThread(holder); 
        setFocusable(true);
        
        //set up the game engine
		core = new GameCore(); 
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		core.touch(event.getX(), event.getY());
		return true;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// set things up, figure out how big the game view is
		core.resize(width, height);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// start the game loop
		thread.start();		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// stop the game loop
		thread.interrupt();		
	}
	
	public class GameThread extends Thread {
		
		private SurfaceHolder holder; 
		private Canvas canvas;
		
		public GameThread(SurfaceHolder holder) {
			this.holder = holder; 
		}
		
		@Override
		public void run() {
			while(!interrupted()) {
				try {
					canvas = holder.lockCanvas();
					core.draw(canvas);
				} finally {
					holder.unlockCanvasAndPost(canvas);
				}
				core.step(System.currentTimeMillis());
			}
		}
	}
}
