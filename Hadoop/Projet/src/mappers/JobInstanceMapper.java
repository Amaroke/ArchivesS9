import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JobInstanceMapper extends Mapper<LongWritable, Text, Text, Text> {

    private String[] instanceStatusToIgnore;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        instanceStatusToIgnore = context.getConfiguration().getStrings("instanceStatusToIgnore");
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        if (instanceStatusToIgnore != null) {
            for (String status : instanceStatusToIgnore) {
                if (fields[3].equals(status)) {
                    return;
                }
            }
        }

        if (fields.length > 5) {
            String jobName = fields[2];
            String tasks = fields[1];

            context.write(new Text(jobName), new Text(tasks));
        }
    }
}