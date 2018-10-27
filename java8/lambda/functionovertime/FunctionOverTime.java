package com.java8.lambda.functionovertime;

@FunctionalInterface
public interface FunctionOverTime {
	double valueAt(int time);
	static FunctionOverTime monthByMonth(final double [] values) 
	{
		return time -> values[time - 1];
	}
	static FunctionOverTime constant(final double value)
	{
		return polynomial(new double[] {value});
	}
	
	static FunctionOverTime line(final double intercept, final double slope)
	{
		return polynomial(new double[] { intercept, slope });
	}
	
	static FunctionOverTime polynomial(final double [] coeficients)
	{
		return time -> {
			Double sum = 0.0;
			for(int i=0; i<coeficients.length; i++)
				sum +=Math.pow(time, i) * coeficients[i];
			return sum;
		};
	}
	
	static FunctionOverTime combiner(final FunctionOverTime a,
			                               final FunctionOverTime b,
			                               final FunctionOverTime c,
			                               final TriFunction<Double, Double, Double, Double> f)
	{
		return time -> f.apply(a.valueAt(time), b.valueAt(time), c.valueAt(time));
	}
	
}
