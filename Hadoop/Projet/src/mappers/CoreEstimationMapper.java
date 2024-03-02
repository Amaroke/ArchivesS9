import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CoreEstimationMapper extends Mapper<Object, Text, Text, IntWritable> {

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        if (fields.length > 8) {
            String machineId = fields[6];
            int maxCores = Integer.parseInt(fields[8]); // Utilisation maximale des CPU

            context.write(new Text(machineId), new IntWritable(maxCores));
        }
    }
}