package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Map;

public class StatisticalComputer {
	/**
	 * Compute covariance.
	 *
	 * @param array1 the array 1
	 * @param array2 the array 2
	 * @return the double
	 */
	public static double computeCovariance(Map<String, Double> array1, Map<String, Double> array2) {
		double mean1 = computeMean(array1);
		double mean2 = computeMean(array2);
		double covSum = 0.0;
		for (String i : array1.keySet()) {
			covSum += (array1.get(i) - mean1) * (array2.get(i) - mean2);
		}
		return covSum / (array1.size() - 1.0);
	}

	/**
	 * Compute mean.
	 *
	 * @param array the array
	 * @return the double
	 */
	public static double computeMean(Map<String, Double> array) {
		double sum = 0.0;
		for (String i : array.keySet()) {
			sum += array.get(i);
		}
		return sum / (array.size() + 0.0);
	}
}