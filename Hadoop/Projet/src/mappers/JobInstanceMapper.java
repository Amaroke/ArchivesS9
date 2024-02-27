import java.io.IOException;

import javax.naming.Context;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JobInstanceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(",");

        if (fields.length > 5) {
            String jobName = fields[2];
            String taksList = fields[1];
            context.write(new Text(jobName), new IntWritable(taksList.toString().split("_").length));
        }
    }
}