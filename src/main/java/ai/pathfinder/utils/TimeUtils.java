package ai.pathfinder.utils;

public class TimeUtils {

    /**
     * Convert from nano second to second
     * @param nano
     * @param decimal - decimal place precision
     * @return
     */
    public static double nanoSecondToSecond(double nano, int decimal) {
        double precision = Math.pow(10, decimal);
        double second = nano / 1_000_000_000d;

        return Math.round(second * precision) / precision;
    }
}
