package com.example.basicgame;

import android.graphics.Canvas;

public abstract class GameObject {
	abstract void draw(Canvas canvas);
	abstract void step(long timediff, int width, int height);
}
