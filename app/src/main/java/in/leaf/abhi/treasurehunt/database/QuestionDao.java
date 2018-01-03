package in.leaf.abhi.treasurehunt.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import in.leaf.abhi.treasurehunt.database.Question;

/**
 * Created by 500060150 on 16-12-2017.
 */
@Dao
public interface QuestionDao {
    @Query("SELECT * FROM Questions WHERE q_no=:q_no")
    Question getQuestion(int q_no);

    @Insert
    long insertQuestion(Question question);

    @Delete
    int deleteQuestion(Question question);
}
