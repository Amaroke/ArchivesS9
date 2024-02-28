import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JobReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        // Compteurs pour le nombre d'instances et la plus grande tâche en cas de tâches
        // qui dépendent les unes des autres.
        int instanceCount = 0;
        int biggestTask = 0;
        // Utilisation d'un HashSet pour ne pas compter les tâches de même noms
        // plusieurs fois
        Set<String> taskSet = new HashSet<>();

        for (Text value : values) {
            String task = value.toString();

            // Vérifier si la valeur correspond à un motif de tâche unique
            if (task.matches("task_.+")) {
                taskSet.add(task);
            } else if (task.matches("[A-Z](\\d+)(?:_\\d+)*")) {
                // Si la valeur correspond à un motif de tâche qui dépend d'autres tâches,
                // extraire le numéro de tâche
                Matcher matcher = Pattern.compile("[A-Z](\\d+)(?:_\\d+)*").matcher(task);
                if (matcher.find()) {
                    int taskNumber = Integer.parseInt(matcher.group(1));
                    if (taskNumber > biggestTask) {
                        biggestTask = taskNumber;
                    }
                }
            }
            instanceCount++;
        }

        // Calculer le nombre de tâches uniques
        int taskCount = biggestTask + taskSet.size();

        String result = "Nombre de Tâches : " + taskCount + ", Nombre d'Instances : " + instanceCount;
        context.write(key, new Text(result));
    }
}
