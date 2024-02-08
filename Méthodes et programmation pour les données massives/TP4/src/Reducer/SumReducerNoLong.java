import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;

public class SumReducerNoLong extends Reducer<Text, IntWritable, Text, IntWritable> {

	private int filter;

	@Override
	public void setup(Context context) {
		Configuration conf = context.getConfiguration();
		filter = conf.getInt("filter", 0);
	}

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int wordCount = 0;
		for (IntWritable value : values) {
			wordCount += value.get();
		}
		if (wordCount >= filter) {
			context.write(key, new IntWritable(wordCount));
		}
	}
}
