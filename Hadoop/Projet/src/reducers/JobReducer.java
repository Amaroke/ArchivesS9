import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JobReducer extends Reducer<Text, IntWritable, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int taskCount = 0;
        int instanceCount = 0;

        for (IntWritable value : values) {
            instanceCount++;
            taskCount += value.get();
        }

        String result = "Nombre de Tâches: " + taskCount + ", Nombre d'Instances: " + instanceCount;
        context.write(key, new Text(result));
    }
}