
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class MachineMemoryWritable implements WritableComparable<MachineMemoryWritable> {

    private Text machineId;
    private float memory;

    public MachineMemoryWritable() {
        this.machineId = new Text();
    }

    public MachineMemoryWritable(String machineId, float memory) {
        this.machineId = new Text(machineId);
        this.memory = memory;
    }

    public MachineMemoryWritable(Text machineId, float memory) {
        this.machineId = machineId;
        this.memory = memory;
    }

    public Text getMachineId() {
        return this.machineId;
    }

    public float getMemory() {
        return this.memory;
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.machineId.readFields(in);
        this.memory = in.readFloat();
    }

    @Override
    public void write(DataOutput out) throws IOException {
        this.machineId.write(out);
        out.writeFloat(this.memory);
    }

    @Override
    public int compareTo(MachineMemoryWritable other) {
        int cmp = this.machineId.compareTo(other.machineId);
        if (cmp != 0) {
            return cmp;
        }
        return Float.compare(this.memory, other.memory);
    }

    @Override
    public String toString() {
        return this.machineId + "," + this.memory;
    }
}
