import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MemoryRatioPreReducer extends Reducer<Text, MachineMemoryWritable, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<MachineMemoryWritable> values, Context context)
            throws IOException, InterruptedException {
        Map<String, Float> memoryUsage = new HashMap<>();

        for (MachineMemoryWritable value : values) {
            String machineId = value.getMachineId().toString();
            float memory = value.getMemory();
            memoryUsage.put(machineId, memory);
        }

        for (Map.Entry<String, Float> entry : memoryUsage.entrySet()) {
            context.write(new Text(key), new Text(entry.getKey() + ";" + entry.getValue()));
        }
    }
}
