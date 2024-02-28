import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CoreUsageReducer extends Reducer<IntWritable, IntWritable, IntWritable, Text> {

    private List<Integer> coreList = new ArrayList<Integer>();

    @Override
    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int totalCores = 0;

        for (IntWritable value : values) {
            totalCores += value.get();
        }

        coreList.add(totalCores);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (int i = 1; i < coreList.size() - 1; i++) {
            int current = coreList.get(i);
            int next = coreList.get(i + 1);

            if (next - current > 100) {
                int j = i + 1;
                int sum = 0;
                while (j < coreList.size() - 1 && coreList.get(j + 1) - coreList.get(j) > 100) {
                    sum += coreList.get(j);
                    j++;
                }

                if (j != i + 1) {
                    int duration = j - i;
                    double average = (double) sum / duration;
                    context.write(new IntWritable(i), new Text(
                            "Peak from " + i + " to " + j + ", duration: " + duration + ", core average: "
                                    + (int) average));
                }
            }
        }
    }

}