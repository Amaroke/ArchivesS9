import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MemoryUsageReducer extends Reducer<Text, MachineMemoryWritable, MachinePairWritable, Text> {

    @Override
    public void reduce(Text key, Iterable<MachineMemoryWritable> values, Context context)
            throws IOException, InterruptedException {

        Iterator<MachineMemoryWritable> iter = values.iterator();
        MachineMemoryWritable prev = iter.next();

        while (iter.hasNext()) {
            MachineMemoryWritable current = iter.next();
            double ratio = (double) current.getMemory() / prev.getMemory();

            context.write(new MachinePairWritable(prev.getMachineId(), current.getMachineId()),
                    new Text(Double.toString(ratio)));

            prev = current;
        }
    }
}
