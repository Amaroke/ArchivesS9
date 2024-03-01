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

public class TaskAnalysisDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println(
                    "Usage: JobAnalysisDriver <input dir> <output dir> -ignore <instance status to ignore: comma-separated list> -sort <column number> -separateFiles <column number> <filesNumber> <intervals: comma-separated list of intervals>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();

        String[] instanceStatusToIgnore = null;
        int sortColumn = -1;
        int separateColumn = -1;
        int filesNumber = -1;
        String[] separateTasksIntervals = null;

        for (int i = 2; i < args.length; i++) {
            if (args[i].equals("-ignore")) {
                instanceStatusToIgnore = args[++i].split(",");
            } else if (args[i].equals("-sort")) {
                sortColumn = Integer.parseInt(args[++i]);
            } else if (args[i].equals("-separateFiles")) {
                separateColumn = Integer.parseInt(args[++i]);
                filesNumber = Integer.parseInt(args[++i]);
                separateTasksIntervals = args[++i].split(",");
            }
        }

        // Configurer les paramètres dans l'objet Configuration
        if (instanceStatusToIgnore != null) {
            conf.setStrings("instanceStatusToIgnore", instanceStatusToIgnore);
        }
        if (sortColumn != -1) {
            conf.setInt("sortColumn", sortColumn);
        }
        if (separateColumn != -1 && filesNumber != -1 && separateTasksIntervals != null) {
            conf.setInt("separateColumn", separateColumn);
            conf.setInt("filesNumber", filesNumber);
            conf.setStrings("separateTasksIntervals", separateTasksIntervals);
        }

        // Création d'un nouveau job Map/Reduce et configuration
        Job job = Job.getInstance(conf);
        job.setJarByClass(TaskAnalysisDriver.class);
        job.setJobName("Task Analysis");

        // Définition des chemins d'entrée et de sortie des données
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Configuration des classes Mapper et Reducer
        job.setMapperClass(TaskInstanceMapper.class);
        job.setReducerClass(TaskReducer.class);

        // Définition des types de sortie du Mapper
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // Définition des types de sortie finaux
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Exécution de la tâche MapReduce et attente de sa terminaison
        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        // Exécution de la tâche en utilisant ToolRunner qui gère les configurations
        int exitCode = ToolRunner.run(new Configuration(), new TaskAnalysisDriver(), args);
        System.exit(exitCode);
    }
}