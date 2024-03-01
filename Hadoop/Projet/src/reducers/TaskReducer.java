import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Comparator;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class TaskReducer extends Reducer<Text, IntWritable, NullWritable, Text> {

    private int sortColumn;
    int separateColumn;
    int filesNumber;
    String[] separateTasksIntervals;
    private ArrayList<String>[] results;

    @Override
    @SuppressWarnings("unchecked")
    protected void setup(Context context) throws IOException, InterruptedException {
        sortColumn = context.getConfiguration().getInt("sortColumn", 0);
        separateColumn = context.getConfiguration().getInt("separateColumn", 0);
        filesNumber = context.getConfiguration().getInt("filesNumber", 0);
        separateTasksIntervals = context.getConfiguration().getStrings("separateTasksIntervals");
        results = new ArrayList[filesNumber];
        for (int i = 0; i < filesNumber; i++) {
            results[i] = new ArrayList<>();
        }
    }

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int totalDuration = 0;
        int instanceCount = 0;
        int stragglerCount = 0;
        ArrayList<Integer> durations = new ArrayList<>();

        // Calcul de la durée totale et du nombre d'instances, stockage des durées
        for (IntWritable value : values) {
            int duration = value.get();
            totalDuration += duration;
            instanceCount++;
            durations.add(duration);
        }

        // Calcul de la durée moyenne
        double averageDuration = (double) totalDuration / instanceCount;

        // Calcul du nombre de stragglers
        for (int duration : durations) {
            if (duration > averageDuration * 1.20) { // 20% plus long que la moyenne
                stragglerCount++;
            }
        }

        // Écrire le résultat pour la tâche
        String result = key + "," + totalDuration + "," + instanceCount + "," + stragglerCount;
        if (filesNumber != 0) {

            for (int i = 0; i < separateTasksIntervals.length; i++) {
                int inf = Integer.parseInt(separateTasksIntervals[i].split("-")[0]);
                int sup = Integer.parseInt(separateTasksIntervals[i].split("-")[1]);
                if (taskCount >= inf && taskCount <= sup && separateColumn == 1) {
                    results[i].add(result);
                } else if (instanceCount >= inf && instanceCount <= sup && separateColumn == 2) {
                    results[i].add(result);
                }

            }
        } else {
            results[0].add(result);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        MultipleOutputs<NullWritable, Text> multipleOutputs = new MultipleOutputs<>(context);

        if (filesNumber != 0) {
            // Trier et émettre les résultats dans trois fichiers de sortie distincts
            for (int i = 0; i < filesNumber; i++) {
                final int index = i;
                Collections.sort(results[index], new Comparator<String>() {
                    @Override
                    public int compare(String r1, String r2) {
                        String[] split1 = r1.split(",");
                        String[] split2 = r2.split(",");
                        switch (sortColumn) {
                            case 1:
                                return Integer.compare(Integer.parseInt(split1[1]), Integer.parseInt(split2[1]));
                            case 2:
                                return Integer.compare(Integer.parseInt(split1[2]), Integer.parseInt(split2[2]));
                            case 3:
                                return Integer.compare(Integer.parseInt(split1[3]), Integer.parseInt(split2[3]));
                            default:
                                return split1[0].compareTo(split2[0]);
                        }
                    }
                });

                // Émettre les résultats triés dans le fichier de sortie approprié
                for (String result : results[index]) {
                    // Utiliser le MultipleOutputs pour écrire dans différents fichiers
                    multipleOutputs.write(NullWritable.get(), new Text(result), "output_" + index);
                }

                multipleOutputs.close();
            }
        } else {
            final int index = 0;
            Collections.sort(results[index], new Comparator<String>() {
                @Override
                public int compare(String r1, String r2) {
                    String[] split1 = r1.split(",");
                    String[] split2 = r2.split(",");
                    switch (sortColumn) {
                        case 1:
                            return Integer.compare(Integer.parseInt(split1[1]), Integer.parseInt(split2[1]));
                        case 2:
                            return Integer.compare(Integer.parseInt(split1[2]), Integer.parseInt(split2[2]));
                        case 3:
                            return Integer.compare(Integer.parseInt(split1[3]), Integer.parseInt(split2[3]));
                        default:
                            return split1[0].compareTo(split2[0]);
                    }
                }
            });

            for (String result : results[0]) {
                context.write(NullWritable.get(), new Text(result));
            }
        }

    }
}
