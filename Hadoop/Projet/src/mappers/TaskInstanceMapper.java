import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TaskInstanceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        // Diviser la ligne en utilisant les virgules comme séparateurs
        String[] fields = line.split(",");

        if (fields.length > 5) {
            String taskName = fields[1];
            String jobName = fields[2];
            // Convertir les dates de début et de fin en long pour calculer la durée
            long startTime = Long.parseLong(fields[4]);
            long endTime = Long.parseLong(fields[5]);
            long duration = endTime - startTime;

            // Émettre le nom de la tâche avec sa durée (ou toute autre information
            // pertinente)
            context.write(new Text(taskName), new IntWritable((int) duration));

            // test 2
            // Par exemple, pour les jobs : context.write(new Text(jobName), new
            // IntWritable(1));
        }
    }
}