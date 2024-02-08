import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import java.util.ArrayList;
import java.util.Collections;

public class InverseReducer<K, V> extends Reducer<K, V, Text, K> {

	@Override
	public void reduce(K key, Iterable<V> values, Context context) throws IOException, InterruptedException {
		long wordCount = 0;
		ArrayList<String> l = new ArrayList<>();
		for (V value : values) {
			l.add(value.toString());
		}
		Collections.sort(l);
		for (String value : l) {
			context.write(new Text(value), key);
		}
	}
}
