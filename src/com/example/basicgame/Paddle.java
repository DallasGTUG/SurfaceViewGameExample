package com.example.basicgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Paddle extends GameObject {

	private Paint paint; 
	private int color; 
	private int width; 
	
	public RectF position; 
	
	public Paddle(int width, int height, int ypos) {
		paint = new Paint(); 
		color = Color.parseColor("#55FF55FF");
		paint.setColor(color);
		position = new RectF(0, ypos, width, ypos+height);
		this.width = width;
	}
	
	@Override
	void draw(Canvas canvas) {
		canvas.drawRect(position, paint);
	}

	@Override
	void step(long time, int width, int height) {
		// don't need to do anything for the paddle here		
	}

	public void touch(float x, float y) {
		position.left = x-(width/2F); 
		position.right = x+(width/2F);
	}

}
