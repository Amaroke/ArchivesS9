import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.HashSet;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;

public class GrepMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Set<String> wordToSearch;

	@Override
	public void setup(Context context) {
		Configuration conf = context.getConfiguration();
		String s = conf.get("wordToSearch");
		this.wordToSearch = new HashSet<String>();
		for (String word : s.split(";+")) {
			if (word.length() > 0) {
				this.wordToSearch.add(word);
			}
		}

	}

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		for (String word : line.split("\\W+")) {
			if (word.length() > 0 && this.wordToSearch.contains(word)) {
				context.write(new Text(word), new IntWritable(1));
			}
		}
	}
}
