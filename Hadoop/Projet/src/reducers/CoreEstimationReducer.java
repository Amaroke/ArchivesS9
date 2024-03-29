import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CoreEstimationReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        // On récupère la valeur maximale des coeurs utilisés pour chaque machine
        int maxCores = 0;
        for (IntWritable value : values) {
            maxCores = Math.max(maxCores, value.get());
        }

        context.write(key, new IntWritable(maxCores));
    }
}