import java.io.Closeable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Implement the two methods below. We expect this class to be stateless and thread safe.
 */
public class Census {
    /**
     * Number of cores in the current machine.
     */
    private static final int CORES = Runtime.getRuntime().availableProcessors();

    /**
     * Output format expected by our tests.
     */
    public static final String OUTPUT_FORMAT = "%d:%d=%d"; // Position:Age=Total

    /**
     * Factory for iterators.
     */
    private final Function<String, Census.AgeInputIterator> iteratorFactory;

    /**
     * Creates a new Census calculator.
     *
     * @param iteratorFactory factory for the iterators.
     */
    public Census(Function<String, Census.AgeInputIterator> iteratorFactory) {
        this.iteratorFactory = iteratorFactory;
    }

    /**
     * Given one region name, call {@link #iteratorFactory} to get an iterator for this region and return
     * the 3 most common ages in the format specified by {@link #OUTPUT_FORMAT}.
     */
    public String[] top3Ages(String region) {
        Map<Integer, Integer> ageCountMap = getCountAges(region);
        throw new UnsupportedOperationException();
    }

    /**
     * calculate the age and count for a single region
     *
     * @param region a single region
     * @return return a map of age(key) and count(value)
     */
    private Map<Integer, Integer> getCountAges(String region) {
        Map<Integer, Integer> countAge = new HashMap<>();
        try (AgeInputIterator iterator = iteratorFactory.apply(region)) {
            while (iterator.hasNext()) {
                Integer age = iterator.next();
                if (countAge.containsKey(age)) {
                    countAge.put(age, countAge.get(age) + 1);
                } else {
                    countAge.put(age, 1);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to iterate region: " + region, e);
        }
        return countAge;
    }

    /**
     * Given a list of region names, call {@link #iteratorFactory} to get an iterator for each region and return
     * the 3 most common ages across all regions in the format specified by {@link #OUTPUT_FORMAT}.
     * We expect you to make use of all cores in the machine, specified by {@link #CORES).
     */
    public String[] top3Ages(List<String> regionNames) {

//        In the example below, the top three are ages 10, 15 and 12
//        return new String[]{
//                String.format(OUTPUT_FORMAT, 1, 10, 38),
//                String.format(OUTPUT_FORMAT, 2, 15, 35),
//                String.format(OUTPUT_FORMAT, 3, 12, 30)
//        };

        throw new UnsupportedOperationException();
    }


    /**
     * Implementations of this interface will return ages on call to {@link Iterator#next()}. They may open resources
     * when being instantiated created.
     */
    public interface AgeInputIterator extends Iterator<Integer>, Closeable {
    }
}