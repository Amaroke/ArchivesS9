import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CoreEstimationDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.printf("Usage: CoreEstimationDriver <input dir> <output dir>\n");
            System.exit(-1);
        }

        Configuration conf = this.getConf();

        // Création d'un nouveau job Map/Reduce et configuration
        Job job = Job.getInstance(conf);
        job.setJarByClass(CoreEstimationDriver.class);
        job.setJobName("Core Estimation");

        // Définition des chemins d'entrée et de sortie des données
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Configuration des classes Mapper et Reducer
        job.setMapperClass(CoreEstimationMapper.class);
        job.setReducerClass(CoreEstimationReducer.class);

        // Définition des types de sortie du Mapper
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Définition des types de sortie du Mapper
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Exécution de la tâche MapReduce et attente de sa terminaison
        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        // Exécution de la tâche en utilisant ToolRunner qui gère les configurations
        int exitCode = ToolRunner.run(new Configuration(), new CoreEstimationDriver(), args);
        System.exit(exitCode);
    }
}