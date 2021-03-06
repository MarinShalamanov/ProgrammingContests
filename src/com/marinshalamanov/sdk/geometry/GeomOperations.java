package com.marinshalamanov.sdk.geometry;

public class GeomOperations {
	//Compute the dot product AB . AC
	private double DotProduct(double[] pointA, double[] pointB, double[] pointC)
	{
	    double[] AB = new double[2];
	    double[] BC = new double[2];
	    AB[0] = pointB[0] - pointA[0];
	    AB[1] = pointB[1] - pointA[1];
	    BC[0] = pointC[0] - pointB[0];
	    BC[1] = pointC[1] - pointB[1];
	    double dot = AB[0] * BC[0] + AB[1] * BC[1];

	    return dot;
	}

	//Compute the cross product AB x AC
	private double CrossProduct(double[] pointA, double[] pointB, double[] pointC)
	{
	    double[] AB = new double[2];
	    double[] AC = new double[2];
	    AB[0] = pointB[0] - pointA[0];
	    AB[1] = pointB[1] - pointA[1];
	    AC[0] = pointC[0] - pointA[0];
	    AC[1] = pointC[1] - pointA[1];
	    double cross = AB[0] * AC[1] - AB[1] * AC[0];

	    return cross;
	}

	//Compute the distance from A to B
	double Distance(double[] pointA, double[] pointB)
	{
	    double d1 = pointA[0] - pointB[0];
	    double d2 = pointA[1] - pointB[1];

	    return Math.sqrt(d1 * d1 + d2 * d2);
	}

	//Compute the distance from AB to C
	//if isSegment is true, AB is a segment, not a line.
	double LineToPointDistance2D(double[] pointA, double[] pointB, double[] pointC, 
	    boolean isSegment)
	{
	    double dist = CrossProduct(pointA, pointB, pointC) / Distance(pointA, pointB);
	    if (isSegment)
	    {
	        double dot1 = DotProduct(pointA, pointB, pointC);
	        if (dot1 > 0) 
	            return Distance(pointB, pointC);

	        double dot2 = DotProduct(pointB, pointA, pointC);
	        if (dot2 > 0) 
	            return Distance(pointA, pointC);
	    }
	    return Math.abs(dist);
	}
}
