import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MemoryRatioReducer extends Reducer<MachinePairWritable, MachineMemoryWritable, MachinePairWritable, Text> {

    @Override
    public void reduce(MachinePairWritable key, Iterable<MachineMemoryWritable> values, Context context)
            throws IOException, InterruptedException {

        Map<String, ArrayList<Float>> memoryMap = new HashMap<>();

        // Accumuler les mémoires pour chaque machine
        for (MachineMemoryWritable value : values) {
            String machineId = value.getMachineId().toString();
            float memory = value.getMemory();
            memoryMap.computeIfAbsent(machineId, k -> new ArrayList<>()).add(memory);
        }

        // Calculer et émettre les ratios pour chaque paire de machines
        for (String machineId1 : memoryMap.keySet()) {
            for (String machineId2 : memoryMap.keySet()) {
                if (!machineId1.equals(machineId2)) {
                    float ratio = calculateMedian(memoryMap.get(machineId1))
                            / calculateMedian(memoryMap.get(machineId2));
                    context.write(new MachinePairWritable(new Text(machineId1), new Text(machineId2)),
                            new Text(String.valueOf(ratio)));
                }
            }
        }
    }

    private float calculateMedian(ArrayList<Float> values) {
        Collections.sort(values);
        int size = values.size();
        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0f;
        } else {
            return values.get(size / 2);
        }
    }
}