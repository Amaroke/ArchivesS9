import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MemoryUsageMapper extends Mapper<LongWritable, Text, MachinePairWritable, MachineMemoryWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        if (fields.length > 10) {
            String taskId = fields[1];
            String machineId = fields[6];
            float memory = Float.parseFloat(fields[10]);

            // Émettre une paire avec une clé vide (MachinePairWritable) et une valeur
            // MachineMemoryWritable
            context.write(new MachinePairWritable(new Text(taskId), new Text()),
                    new MachineMemoryWritable(new Text(machineId), memory));
        }
    }
}