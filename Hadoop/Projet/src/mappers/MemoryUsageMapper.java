import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MemoryUsageMapper extends Mapper<LongWritable, Text, Text, MachineMemoryWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        if (fields.length > 10) {
            String taskId = fields[1];
            String machineId = fields[6];
            float memory = Float.parseFloat(fields[10]);

            context.write(new Text(taskId), new MachineMemoryWritable(new Text(machineId), memory));
        }
    }
}
