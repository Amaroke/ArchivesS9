import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TaskReducer extends Reducer<Text, IntWritable, Text, Text> {
    
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int totalDuration = 0;
        int instanceCount = 0;
        int stragglerCount = 0;
        ArrayList<Integer> durations = new ArrayList<>();

        // Calcul de la durée totale et du nombre d'instances, stockage des durées
        for (IntWritable value : values) {
            int duration = value.get();
            totalDuration += duration;
            instanceCount++;
            durations.add(duration);
        }

        // Calcul de la durée moyenne
        double averageDuration = (double) totalDuration / instanceCount;

        // Calcul du nombre de stragglers
        for (int duration : durations) {
            if (duration > averageDuration * 1.20) { // 20% plus long que la moyenne
                stragglerCount++;
            }
        }

        // Afficher des informations résumées pour le débogage
        if (stragglerCount > 0 || instanceCount > 1000) { // Afficher seulement pour les tâches significatives
            System.out.println("Tâche: " + key.toString() + ", Stragglers: " + stragglerCount + ", Instances: " + instanceCount);
        }

        // Écrire le résultat pour la tâche
        String result = "Durée Totale: " + totalDuration + ", Nombre d'Instances: " + instanceCount + ", Stragglers: " + stragglerCount;
        context.write(key, new Text(result));
    }
}