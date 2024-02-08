import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.util.Random;
import org.apache.hadoop.mapreduce.lib.map.InverseMapper;

public class WordCount extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new Configuration(), new WordCount(), args);
		System.exit(exitCode);
	}

	public int run(String[] args) throws Exception {

		if (args.length < 2) {
			System.out.printf("Usage: WordCount [generic options] <input dir> <output dir> [options]\n");
			return -1;
		}

		Configuration conf = this.getConf();

		conf.setInt("filter", 0);
		conf.setBoolean("lowerCase", false);

		int i = 2;
		while (i < args.length) {
			if (args[i].equals("-lowerCase")) {
				conf.setBoolean("lowerCase", true);
			} else if (args[i].equals("-filter")) {
				i++;
				if (i < args.length) {
					conf.setInt("filter", Integer.parseInt(args[i]));
				} else {
					System.out.printf("Missing argument for -filter option\n");
					System.exit(-1);
				}
			} else {
				System.out.printf("Unknown option " + args[2] + "\n");
				System.exit(-1);
			}
			i++;
		}

		Path tempDir = new Path("WordCount-temp-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));

		Job jobCount = Job.getInstance(conf);

		jobCount.setJarByClass(WordCount.class);
		jobCount.setJobName("Word-Count");

		FileInputFormat.setInputPaths(jobCount, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobCount, tempDir);
		jobCount.setOutputFormatClass(SequenceFileOutputFormat.class);

		jobCount.setMapperClass(WordMapper.class);
		jobCount.setReducerClass(SumReducerNoLong.class);
		jobCount.setCombinerClass(SumReducer.class);

		jobCount.setMapOutputKeyClass(Text.class);
		jobCount.setMapOutputValueClass(IntWritable.class);

		jobCount.setMapOutputKeyClass(Text.class);
		jobCount.setMapOutputValueClass(LongWritable.class);

		jobCount.setOutputKeyClass(Text.class);
		jobCount.setOutputValueClass(LongWritable.class);

		boolean success = jobCount.waitForCompletion(true);

		if (success) {
			FileSystem.get(conf).delete(tempDir, true);
			return 0;
		} else {
			return -1;
		}
	}
}
