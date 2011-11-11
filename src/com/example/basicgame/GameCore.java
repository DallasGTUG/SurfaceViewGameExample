package com.example.basicgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;

public class GameCore {

	private int backColor; 
	private int width, height; 
	private long last = 0, timediff; 
	private Ball ball;
	private Paddle paddle;
	
	public GameCore() {
		backColor = Color.parseColor("black");
	}
	
	public void draw(Canvas canvas) {
		// this is the draw frame phase of the game
		canvas.drawColor(backColor);
		paddle.draw(canvas);
		ball.draw(canvas);
	}
	
	public void step(long now) {
		// this is the move step phase of the game		
		if(last == 0) last = now;
		timediff = now - last; 
		paddle.step(timediff, width, height);		
		ball.step(timediff, width, height);
		ball.testPaddle(paddle);
		last = now; 
	}

	public void resize(int width, int height) {
		// called when the game view is resized
		PointF speed = new PointF(width/12F, width/8.5F);
		ball = new Ball(width / 30, speed);
		paddle = new Paddle(width/4, height/25, height-(height/30));
		this.width = width;
		this.height = height; 
	}

	public void touch(float x, float y) {
		// called when the screen is touched
		paddle.touch(x, y);
	}
}
