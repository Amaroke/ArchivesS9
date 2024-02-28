import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JobAnalysisDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.printf("Usage: JobAnalysisDriver <input dir> <output dir>\n");
            System.exit(-1);
        }

        Configuration conf = this.getConf();

        // Création d'un nouveau job Map/Reduce et configuration
        Job job = Job.getInstance(conf);
        job.setJarByClass(JobAnalysisDriver.class);
        job.setJobName("Job Analysis");

        // Définition des chemins d'entrée et de sortie des données
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Configuration des classes Mapper et Reducer
        job.setMapperClass(JobInstanceMapper.class);
        job.setReducerClass(JobReducer.class);

        // Définition des types de sortie du Mapper
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        // Définition des types de sortie finaux
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Exécution de la tâche MapReduce et attente de sa terminaison
        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        // Exécution de la tâche en utilisant ToolRunner qui gère les configurations
        int exitCode = ToolRunner.run(new Configuration(), new JobAnalysisDriver(), args);
        System.exit(exitCode);
    }
}