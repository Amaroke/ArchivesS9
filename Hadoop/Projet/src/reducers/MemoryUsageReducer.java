import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MemoryUsageReducer extends Reducer<Text, MachineMemoryWritable, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<MachineMemoryWritable> values, Context context)
            throws IOException, InterruptedException {
        Map<String, Float> memoryUsage = new HashMap<>();
        Set<String> processedPairs = new HashSet<>();

        for (MachineMemoryWritable value : values) {
            String machineId = value.getMachineId().toString();
            float memory = value.getMemory();
            memoryUsage.put(machineId, memory);
        }

        List<String> machines = new ArrayList<>(memoryUsage.keySet());
        Collections.sort(machines);

        for (int i = 0; i < machines.size(); i++) {
            for (int j = i + 1; j < machines.size(); j++) {
                String machineId1 = machines.get(i);
                String machineId2 = machines.get(j);
                String pairKey;

                // Tri des identifiants de machine pour éviter les doublons
                List<String> sortedIds = Arrays.asList(machineId1, machineId2);
                Collections.sort(sortedIds);
                pairKey = sortedIds.get(0) + "-" + sortedIds.get(1);

                if (!processedPairs.contains(pairKey)) {
                    float memory1 = memoryUsage.get(machineId1);
                    float memory2 = memoryUsage.get(machineId2);
                    float ratio = memory1 / memory2;
                    context.write(key, new Text(machineId1 + ";" + machineId2 + ";" + ratio));
                    processedPairs.add(pairKey);
                }
            }
        }
    }
}
