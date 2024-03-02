import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TaskInstanceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private String[] instanceStatusToIgnore;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        instanceStatusToIgnore = context.getConfiguration().getStrings("instanceStatusToIgnore");
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        // On ignore les instances avec les status spécifiés
        if (instanceStatusToIgnore != null) {
            for (String status : instanceStatusToIgnore) {
                if (fields[3].equals(status)) {
                    return;
                }
            }
        }

        if (fields.length > 5) {
            String taskName = fields[1];

            // Convertir les dates de début et de fin en long pour calculer la durée
            long startTime = Long.parseLong(fields[4]);
            long endTime = Long.parseLong(fields[5]);

            // Si endTime est à 0, la tâche n'est pas terminée et on la retire
            if (endTime == 0) {
                return;
            }
            long duration = endTime - startTime;

            // Émettre le nom de la tâche avec sa durée (en secondes)
            context.write(new Text(taskName), new IntWritable((int) duration));
        }
    }
}