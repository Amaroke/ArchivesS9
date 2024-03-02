import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class JobReducer extends Reducer<Text, Text, NullWritable, Text> {

    // Variables pour le tri et la séparation des résultats
    private int sortColumn;
    int separateColumn;
    int filesNumber;
    String[] separateTasksIntervals;
    private ArrayList<String>[] results;

    @Override
    @SuppressWarnings("unchecked")
    protected void setup(Context context) throws IOException, InterruptedException {
        // Récupérer les paramètres de configuration
        sortColumn = context.getConfiguration().getInt("sortColumn", 0);
        separateColumn = context.getConfiguration().getInt("separateColumn", 0);
        filesNumber = context.getConfiguration().getInt("filesNumber", 0);
        separateTasksIntervals = context.getConfiguration().getStrings("separateTasksIntervals");
        results = new ArrayList[filesNumber];
        if (filesNumber != 0) {
            results = new ArrayList[filesNumber];
            for (int i = 0; i < filesNumber; i++) {
                results[i] = new ArrayList<>();
            }
        } else {
            results = new ArrayList[1];
            results[0] = new ArrayList<>();
        }
    }

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        // Compteurs pour le nombre d'instances et la plus grande tâche en cas de tâches
        // qui dépendent les unes des autres.
        int instanceCount = 0;
        int biggestTask = 0;
        // Utilisation d'un HashSet pour ne pas compter les tâches de même noms
        // plusieurs fois
        Set<String> taskSet = new HashSet<>();

        for (Text value : values) {
            String task = value.toString();

            // Vérifier si la valeur correspond à un motif de tâche unique
            if (task.matches("task_.+")) {
                taskSet.add(task);
            } else if (task.matches("[A-Z](\\d+)(?:_\\d+)*")) {
                // Si la valeur correspond à un motif de tâche qui dépend d'autres tâches,
                // extraire le numéro de tâche
                Matcher matcher = Pattern.compile("[A-Z](\\d+)(?:_\\d+)*").matcher(task);
                if (matcher.find()) {
                    int taskNumber = Integer.parseInt(matcher.group(1));
                    if (taskNumber > biggestTask) {
                        biggestTask = taskNumber;
                    }
                }
            }
            instanceCount++;
        }

        // Calculer le nombre de tâches uniques
        int taskCount = biggestTask + taskSet.size();
        String result = key + "," + taskCount + "," + instanceCount;
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
            // Trier et émettre les résultats dans les fichiers de sortie distincts
            for (int i = 0; i < filesNumber; i++) {
                final int index = i;
                List<String> sortedResults = results[index].stream()
                        .sorted(getComparator(sortColumn))
                        .collect(Collectors.toList());

                // Émettre les résultats triés dans le fichier de sortie approprié
                for (String result : sortedResults) {
                    // Utiliser le MultipleOutputs pour écrire dans différents fichiers
                    multipleOutputs.write(NullWritable.get(), new Text(result), "output_" + index);
                }
            }
        } else {
            List<String> sortedResults = results[0].stream()
                    .sorted(getComparator(sortColumn))
                    .collect(Collectors.toList());

            for (String result : sortedResults) {
                context.write(NullWritable.get(), new Text(result));
            }
        }

        multipleOutputs.close();
    }

    private Comparator<String> getComparator(int sortColumn) {
        return new Comparator<String>() {
            @Override
            public int compare(String r1, String r2) {
                String[] split1 = r1.split(",");
                String[] split2 = r2.split(",");
                switch (sortColumn) {
                    case 1:
                        return Integer.compare(Integer.parseInt(split1[1]), Integer.parseInt(split2[1]));
                    case 2:
                        return Integer.compare(Integer.parseInt(split1[2]), Integer.parseInt(split2[2]));
                    default:
                        return split1[0].compareTo(split2[0]);
                }
            }
        };
    }
}
