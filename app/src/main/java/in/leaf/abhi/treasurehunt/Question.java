package in.leaf.abhi.treasurehunt;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by 500060150 on 16-12-2017.
 */
@Entity(tableName = "Questions")
public class Question {
    Question(int q_no,String question,String answer) {
        this.q_no=q_no;
        this.question=question;
        this.answer=answer;
    }
    @PrimaryKey
    int q_no;

    String question;
    String answer;

    @Override
    public boolean equals(Object o) {
        try {
            Question q= (Question)o;
            if (q_no == q_no)
                if (question.equals(q.question))
                    if(answer.equals(q.answer))
                        return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
