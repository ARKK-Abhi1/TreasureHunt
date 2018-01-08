package in.leaf.abhi.treasurehunt.database;

import android.arch.persistence.room.RoomDatabase;

/**
 * Created by 500060150 on 16-12-2017.
 */
@android.arch.persistence.room.Database(entities={Question.class,Results.class},version=5)
public abstract class Database extends RoomDatabase {
    public abstract QuestionDao getQuestionDao();

    public abstract ResultsDao getResultsDao();
}
