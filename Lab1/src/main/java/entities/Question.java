package entities;

import java.util.Objects;

public class Question {

    private long id;
    private String questionText;
    private long packId;
    private long packOrder;

    public Question(long id, String questionText, long packId, long packOrder) {
        this.id = id;
        this.questionText = questionText;
        this.packId = packId;
        this.packOrder = packOrder;
    }

    public Question(String questionText, long packId, long packOrder) {
        this.questionText = questionText;
        this.packId = packId;
        this.packOrder = packOrder;
    }

    public long getPackId() {
        return packId;
    }

    public void setPackId(long packId) {
        this.packId = packId;
    }

    public long getPackOrder() {
        return packOrder;
    }

    public void setPackOrder(long packOrder) {
        this.packOrder = packOrder;
    }

    public Question(long id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public Question() {
    }

    @Override
    public String toString() {
        return Long.toString(id)+"\t"+questionText+"\t"+Long.toString(packId)+"\t"+Long.toString(packOrder);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && packId == question.packId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packId);
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    //
}

