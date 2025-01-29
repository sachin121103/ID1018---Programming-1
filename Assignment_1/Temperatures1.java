// Temperatures1.java

// proccessing measurement data


/**********************************************************************

A problem: processing measurement data

Temperature measurements are taken in one and the same place for a couple
of weeks. The measurements are taken regularly � the same number of
measurements each week. At the end of the measurement period the collected
data is to be processed: for each week the least, the greatest and the
average temperature is to be determined. The least, greatest and
average temperature for the whole period is also to be computed.

A solution to the problem

This program reads the temperatures and displays them. Then the least,
greatest and average temperature for each week is computed and stored.
These results are printed on the standard output device. Finally, the
least, greatest and average temperature over the whole measurement
period is decided. These results are also printed on the standard
output device.

Author: Fadil Galjic

**********************************************************************/

// Order of solution:
// Get the smallest value in the array for least temp: Do this with a for loop. Append to the min array.
// Get the largest value in the array and append to the max array
// Add up the values and put them in sum.
// To add values to avg, divide by the length of the array.
// To get it for the whole period, compare the values in the above arrays.

import java.util.*; // Scanner, Locale
import static java.lang.System.out;

class Temperatures1
{
	public static void main (String[] args)
	{
 		out.println("TEMPERATURES\n");

		// input tool
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);

        // enter the number of weeks and measurements
		out.print("number of weeks: ");
		int nofWeeks = in.nextInt();
		out.print("number of measurements per week: ");
		int nofMeasurementsPerWeek = in.nextInt();

        // storage space for temperature data
        double[][] t = new double[nofWeeks + 1]
                                 [nofMeasurementsPerWeek + 1];

        // read the temperatures
		for (int week = 1; week <= nofWeeks; week++)
		{
			out.println("temperatures - week " + week + ":");
			for (int measurement = 1; measurement <= nofMeasurementsPerWeek; measurement++)
				t[week][measurement] = in.nextDouble();
		}
		out.println("");

		// show the temperatures
		out.println("the temperatures");
        for (int week = 1; week <= nofWeeks; week++)
        {
			for (int measurement = 1;
			    measurement <= nofMeasurementsPerWeek; measurement++)
			    out.print(t[week][measurement] + " ");
		    out.println("");
		}
		out.println("");

		// the least, greatest and average temperatures - weekly
		double[] minT = new double[nofWeeks + 1];
		double[] maxT = new double[nofWeeks + 1];
		double[] sumT = new double[nofWeeks + 1];
		double[] avgT = new double[nofWeeks + 1];
		// add code here

		// Get the minimum value from each week and add it to the minT array
		for (int i = 1; i <= nofWeeks ; i++) {
			minT[i] = t[i][1];
			for (int j = 1; j < t[i].length ; j++) {
				if(t[i][j] < minT[i]) {
					minT[i] = t[i][j];
				}
			}

		}

		// Get the maximum value from each week and add it to the maxT array
		for (int i = 1; i <= nofWeeks ; i++) {
			maxT[i] = t[i][1];
			for (int j = 1; j < t[i].length ; j++) {
				if(t[i][j] > maxT[i]) {
					maxT[i] = t[i][j];
				}
			}
		}

		// Get the sum of temperatures for the week and add it to the sumT array
		for (int i = 1; i <= nofWeeks ; i++) {
			double sum = 0;
			for (int j = 1; j < t[i].length ; j++) {
				sum += t[i][j];
			}

			sumT[i] = sum;
		}

		// Get the average temperature and append to avgT.

		for (int i = 1; i < sumT.length; i++) {
			avgT[i] = sumT[i]/nofMeasurementsPerWeek;
		}


		// show the least, greatest and average temperatures
		out.println("the least, greatest and average temperatures"
		    + " - weekly");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(minT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(maxT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(avgT[week] + " ");
		out.println("");
		out.println();

		// the least, greatest and average temperatures - whole period
		double minTemp = minT[1];
		double maxTemp = maxT[1];
		double sumTemp = sumT[1];
		double avgTemp = 0;
		// add code here

		// Get the least value of the period
		for (int i = 1; i < minT.length; i++) {
			if(minT[i] < minTemp){
				minTemp = minT[i];
			}
		}

		// Get the max value of the period
		for (int i = 1; i < maxT.length; i++) {
			if(maxT[i] > maxTemp){
				maxTemp = maxT[i];
			}
		}

		// Get the average of the period

		for (int i = 1; i < avgT.length; i++) {
			avgTemp += avgT[i];
		}

		avgTemp /= (avgT.length-1);

        // show the least, greatest and average temperature for
        // the whole period
		out.println("the least, greatest and average temperature"
		    + " - whole period");
        out.println(minTemp + "\n" + maxTemp + "\n" + avgTemp);
    }
}
