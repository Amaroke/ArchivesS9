import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Grep extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(), new Grep(), args);
		System.exit(exitCode);
	}

	public int run(String[] args) throws Exception {

		if (args.length < 3) {
			System.out.printf(
					"Usage: Grep [generic options] <input dir> <output dir> <word to search> [<other words to search>]\n");
			return -1;
		}

		Configuration conf = this.getConf();

		String wordToSearch = "";
		for (int i = 2; i < args.length; i++) {
			wordToSearch += (args[i] + ";");
		}

		conf.set("wordToSearch", wordToSearch);

		Job job = Job.getInstance(conf);

		job.setJarByClass(Grep.class);
		job.setJobName("My Grep");

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(GrepMapper.class);
		job.setReducerClass(SumReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		boolean success = job.waitForCompletion(true);
		if (success) {
			return 0;
		} else {
			return -1;
		}
	}
}
