import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TaskReducer extends Reducer<Text, IntWritable, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int totalDuration = 0;
        int instanceCount = 0;
        int stragglerCount = 0;
        double averageDuration;

        // Calcul de la durée totale et du nombre d'instances
        for (IntWritable value : values) {
            int duration = value.get();
            totalDuration += duration;
            instanceCount++;
        }

        // Calcul de la durée moyenne
        averageDuration = (double) totalDuration / instanceCount;

        // Reset pour calculer le nombre de stragglers
        for (IntWritable value : values) {
            if (value.get() > averageDuration * 1.20) { // 20% plus long que la moyenne
                stragglerCount++;
            }
        }

        // Écrire le résultat pour la tâche
        String result = "Durée Totale: " + totalDuration + ", Nombre d'Instances: " + instanceCount + ", Stragglers: "
                + stragglerCount;
        context.write(key, new Text(result));
    }
}