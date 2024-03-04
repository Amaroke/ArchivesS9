import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CoreUsageMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        if (fields.length > 7) {
            int startTime = Integer.parseInt(fields[4]); // Seconde de début
            int endTime = Integer.parseInt(fields[5]); // Seconde de fin
            int meanCores = Integer.parseInt(fields[7]); // Consommation moyenne des cœurs

            // Émettre la consommation des cœurs pour chaque seconde de la tâche
            for (int i = startTime; i <= endTime; i++) {
                context.write(new IntWritable(i), new IntWritable(meanCores));
            }
        }
    }
}