import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class MachinePairWritable implements WritableComparable<MachinePairWritable> {

    private Text taskId;
    private Text machineId1;
    private Text machineId2;

    public MachinePairWritable() {
        this.taskId = new Text();
        this.machineId1 = new Text();
        this.machineId2 = new Text();
    }

    public MachinePairWritable(Text taskId, Text machineId1, Text machineId2) {
        this.taskId = taskId;
        this.machineId1 = machineId1;
        this.machineId2 = machineId2;
    }

    public Text getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Text taskId) {
        this.taskId = taskId;
    }

    public Text getMachineId1() {
        return this.machineId1;
    }

    public void setMachineId1(Text machineId1) {
        this.machineId1 = machineId1;
    }

    public Text getMachineId2() {
        return this.machineId2;
    }

    public void setMachineId2(Text machineId2) {
        this.machineId2 = machineId2;
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.taskId.readFields(in);
        this.machineId1.readFields(in);
        this.machineId2.readFields(in);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        this.taskId.write(out);
        this.machineId1.write(out);
        this.machineId2.write(out);
    }

    @Override
    public int compareTo(MachinePairWritable other) {
        int cmp = this.taskId.compareTo(other.taskId);
        if (cmp != 0) {
            return cmp;
        }
        cmp = this.machineId1.compareTo(other.machineId1);
        if (cmp != 0) {
            return cmp;
        }
        return this.machineId2.compareTo(other.machineId2);
    }

    @Override
    public String toString() {
        return "TaskID: " + this.taskId + ", Machine1: " + this.machineId1 + ", Machine2: " + this.machineId2;
    }
}
