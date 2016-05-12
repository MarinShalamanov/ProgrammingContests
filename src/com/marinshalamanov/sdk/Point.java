package com.marinshalamanov.sdk;

class Point {
	long x, y;

	public Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
	
	public double dist2(Point other) {
		double dx = other.x - x;
		double dy = other.y - y;
		return dx*dx + dy*dy;
	}
	
	public double dist(Point other) {
		return Math.sqrt(dist2(other));
	}
}