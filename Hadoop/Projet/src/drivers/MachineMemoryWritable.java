import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Text;

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
        return machineId;
    }

    public void setMachineId(Text machineId) {
        this.machineId = machineId;
    }

    public float getMemory() {
        return memory;
    }

    public void setMemory(float memory) {
        this.memory = memory;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        machineId.write(out);
        out.writeFloat(memory);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        machineId.readFields(in);
        memory = in.readFloat();
    }

    @Override
    public int compareTo(MachineMemoryWritable o) {
        int cmp = machineId.compareTo(o.machineId);
        if (cmp != 0) {
            return cmp;
        }
        return Float.compare(memory, o.memory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MachineMemoryWritable that = (MachineMemoryWritable) o;
        return Float.compare(that.memory, memory) == 0 && machineId.equals(that.machineId);
    }

    @Override
    public int hashCode() {
        return 31 * machineId.hashCode() + Float.floatToIntBits(memory);
    }

    @Override
    public String toString() {
        return machineId + ", " + memory;
    }
}
