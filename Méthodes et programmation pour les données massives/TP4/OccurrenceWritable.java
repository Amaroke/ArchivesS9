import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Text ;
import java.io.DataInput;
import java.io.DataOutput;

class OccurrenceWritable implements Writable{
	
	private Text word ;
	private long occurrence ;

	public OccurrenceWritable(){
		this.word = new Text() ;
	}

	public OccurrenceWritable(String word, long occurrence){
		this.word = new Text(word) ;
		this.occurrence = occurrence ;
	}

	public OccurrenceWritable(Text word, long occurrence){
		this.word = word ;
		this.occurrence = occurrence ;
	}


	public Text getWord(){
		return this.word ;
	}

	public long getOccurrence(){
		return this.occurrence ;
	}

	@Override 
	public void readFields(DataInput in) throws IOException {
		this.word = new Text() ;
		this.word.readFields(in);
		this.occurrence = in.readLong() ;
	}

	@Override 
	public void write(DataOutput out) throws IOException {
		this.word.write(out);
		out.writeLong(this.occurrence);
	}

	@Override
	public String toString(){
		return this.word+","+this.occurrence ;
	}

}
