package com.example.basicgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Ball extends GameObject {
	
	private int color; 
	private PointF position, speed; 
	private Paint paint;
	private int radius; 
	
	public Ball(int radius, PointF speed) {
		color = Color.parseColor("white");
		paint = new Paint(); 
		paint.setColor(color);
		position = new PointF(20, 20);
		this.speed = speed;
		this.radius = radius;
	}

	@Override
	void draw(Canvas canvas) {
		// draw the ball circle on the screen
		canvas.drawCircle(position.x, position.y, radius, paint);
	}

	@Override
	void step(long timediff, int width, int height) {
		
		// move the ball based on speed over time
		position.x += (speed.x * timediff) / 100;  
		position.y += (speed.y * timediff) / 100; 
		
		// test the ball for bouncing off the walls
		if (position.x <= 0) speed.x = Math.abs(speed.x); 
		if (position.x >= width) speed.x = -Math.abs(speed.x);
		if (position.y <= 0) speed.y = Math.abs(speed.x);
		
		// test the ball for falling through
		if (position.y >= height) position.y = 0;
	}
	
	void testPaddle(Paddle paddle) {
		
		//test to see if the ball has intersected with the paddle
		if(position.x >= paddle.position.left &&
		position.x <= paddle.position.right && 
		position.y >= paddle.position.top && 
		position.y <= paddle.position.bottom) {
			
			// if it has, make sure the ball is moving up
			speed.y = - Math.abs(speed.y);
			
		}
	}

}
