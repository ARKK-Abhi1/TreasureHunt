package in.leaf.abhi.treasurehunt.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by 500060150 on 04-01-2018.
 */
@Dao
public interface ResultsDao {
    @Insert
    public long insertResults(Results results);

    @Update
    public int updateResults(Results results);

    @Query("SELECT * FROM Results")
    public Results getResults();
}
