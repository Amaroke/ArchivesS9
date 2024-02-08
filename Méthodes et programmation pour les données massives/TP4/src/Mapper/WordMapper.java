import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private boolean lowerCase;

	@Override
	public void setup(Context context) {
		Configuration conf = context.getConfiguration();
		lowerCase = conf.getBoolean("lowerCase", false);
	}

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		for (String word : line.split("\\W+")) {
			if (word.length() > 0) {
				if (lowerCase) {
					context.write(new Text(word.toLowerCase()), new IntWritable(1));
				} else {
					context.write(new Text(word), new IntWritable(1));
				}
			}
		}
	}
}
