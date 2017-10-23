package edu.orangecoastcollege.cs273.jburk.todo2day;

public class Task {
    private int mId;
    private String description;
    private boolean mIsDone;

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return mIsDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsDone(boolean done) {
        mIsDone = done;
    }

    public Task(int id, String description, boolean isDone) {
        mId = id;
        this.description = description;
        mIsDone = isDone;
    }

    public Task(String description, boolean isDone) {
        this(-1, description, isDone);
    }

    public Task() {
        this(-1, "", false);
    }

    @Override
    public String toString() {
        return "Task{" +
                "mId=" + mId +
                ", description='" + description + '\'' +
                ", mIsDone=" + mIsDone +
                '}';
    }
}
