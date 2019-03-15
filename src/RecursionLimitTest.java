import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class RecursionLimitTest {

    public static String[] testQuickSort(int arraySize)
    {
        int k, randomInt;
        long startTime, estimatedTime;
        Integer[] arrayOfInts = new Integer[arraySize];
        String[] runtimeResults = new String[3];

        // build array for sorting
        for (k = 0; k < arraySize; k++) {

            randomInt = (int) (Math.random() * arraySize);
            arrayOfInts[k] = randomInt;
        }
        // measure QuickSort time
        startTime = System.nanoTime();  // ------------------ start
        FHsort.quickSort(arrayOfInts);
        estimatedTime = System.nanoTime() - startTime;    // ---------------------- stop
        /*
        System.out.println("Quick sort Elapsed Time: "
                + " size: " + arraySize + ", "
                + TimeConverter.convertTimeToString(estimatedTime)
                + " = " + estimatedTime + "ns");
        */
        runtimeResults[0] = Integer.toString(arraySize);
        runtimeResults[1] = Long.toString(estimatedTime);
        runtimeResults[2] = TimeConverter.convertTimeToString(estimatedTime);
        // Note: un-comment  to verify sort
        //displayArray(arrayOfInts, "Quick");

        return runtimeResults;
    }


    public static void displayArray(Integer [] theArray, String message) {

        for (int k = 0; k < theArray.length; k+= theArray.length/10) {

            System.out.println( message + " #" + k + ": " + theArray[k] + "");
        }
    }


    // -------  main --------------
    public static void main(String[] args) {

        //final int [] ARRAY_SIZES = {200000, 400000, 800000, 1000000, 1600000};

        //final int [] ARRAY_SIZES = {210000};
        //final int [] ARRAY_SIZES = {7020000};
        //final int [] ARRAY_SIZES = {20480000};

        // Array sizes increasing by intervals of 500,000.
        // Results output in resources/recursionLimitsResults1.csv, resources/recursionLimitsResults2.csv, and
        // resources/recursionLimitsResults3.csv
        /*
        final int [] ARRAY_SIZES = {
                20000, 520000, 1020000, 1520000, 2020000, 2520000, 3020000, 3520000, 4020000, 4520000,
                5020000, 5520000, 6020000, 6520000, 7020000, 7520000, 8020000, 8520000, 9020000, 9520000, 10020000
        };
        */
        // Array sizes starting at 20,000 and 30,000, and each doubling thereafter.
        // Results output in resources/recursionLimitsResults4.csv, resources/recursionLimitsResults5.csv, and
        // resources/recursionLimitsResults6.csv
        final int [] ARRAY_SIZES = {
              20000, 30000, 40000, 60000, 80000, 120000, 160000, 240000, 320000, 480000,
            640000, 960000, 1280000, 1920000, 2560000, 3840000, 5120000, 7680000, 10240000, 15360000, 20480000
        };

        final int LOWER_LIMIT = 2;
        final int UPPER_LIMIT = 300;
        //final int LOWER_LIMIT = 2;
        //final int UPPER_LIMIT = 300;
        final int LIMIT_STEP = 2;
        //final String PATH_FILE = "resources/recursionLimits.csv";
        //final String PATH_FILE = "resources/recursionLimitsResults1.csv";
        //final String PATH_FILE = "resources/recursionLimitsResults2.csv";
        //final String PATH_FILE = "resources/recursionLimitsResults3.csv";
        //final String PATH_FILE = "resources/recursionLimitsResults4.csv";
        final String PATH_FILE = "resources/recursionLimitsResults5.csv";
        //final String PATH_FILE = "resources/recursionLimitsResults6.csv";
        //final String PATH_FILE = "resources/test.csv";

        //double runTime = 0;
        String[] result;

        File file = new File(PATH_FILE);
        try {

            PrintStream printStream = new PrintStream(file);
            PrintStream console = System.out;
            String resultStr;
            String recursionLimit;

            //System.setOut(printStream);
            //System.out.println("Array Size, Recursion Limit, QuickSort Runtime");

            for (int test = 0; test < ARRAY_SIZES.length; test++) {

                int currentSize = ARRAY_SIZES[test];

                for (int recLimit = LOWER_LIMIT; recLimit <= UPPER_LIMIT; recLimit += LIMIT_STEP) {

                    FHsort.setRecursionLimit(recLimit);
                    recursionLimit = Integer.toString(FHsort.getRecursionLimit());
                    result = testQuickSort(currentSize);
                    resultStr = result[0] + ", " + recursionLimit + ", " + result[1];

                    System.setOut(printStream);
                    System.out.println(resultStr);
                    //result[1] = Integer.toString(FHsort.getRecursionLimit());
                    //System.out.printf("For data size %d and recusion limit %d:\nRuntime = %.2f \n\n",
                    //      currentSize, recLimit, runTime);
                    System.setOut(console);
                    System.out.printf("Array Size: %s\t\tRecusion Limit: %s\t\tQuickSort Runtime: %s ns = %s\n",
                            result[0], recursionLimit, result[1], result[2]);
                }

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
