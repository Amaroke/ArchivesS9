import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUsageReducer extends Reducer<Text, MachineMemoryWritable, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<MachineMemoryWritable> values, Context context)
            throws IOException, InterruptedException {
        Map<String, Float> memoryUsage = new HashMap<>();

        // Stocker l'utilisation de la mémoire pour chaque machine
        for (MachineMemoryWritable value : values) {
            memoryUsage.put(value.getMachineId().toString(), value.getMemory());
        }

        // Calculer et écrire les ratios pour chaque paire unique, en excluant les
        // ratios égaux à 1
        List<String> machines = new ArrayList<>(memoryUsage.keySet());
        for (int i = 0; i < machines.size(); i++) {
            for (int j = i + 1; j < machines.size(); j++) {
                float memory1 = memoryUsage.get(machines.get(i));
                float memory2 = memoryUsage.get(machines.get(j));
                float ratio = memory1 / memory2;

                if (ratio != 1.0) { // Exclure les ratios égaux à 1
                    String output = machines.get(i) + ";" + machines.get(j) + ";" + ratio;
                    context.write(key, new Text(output));
                }
            }
        }
    }
}
