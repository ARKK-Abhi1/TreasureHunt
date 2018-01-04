package in.leaf.abhi.treasurehunt.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by 500060150 on 04-01-2018.
 */
@Entity
public class Results {
    public Results(int teamNo,long timeInMillis) {
        this.teamNo=teamNo;
        this.timeInMillis=timeInMillis;
    }
    @PrimaryKey
    public int teamNo;

    public long timeInMillis;
}
