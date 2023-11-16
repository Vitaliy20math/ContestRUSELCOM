package webapp;

import java.time.LocalDateTime;

public class Movement{
    private LocalDateTime date;
    private int count;
    private String flow;

    public Movement() {
    }

    public Movement(String flow, LocalDateTime date, int count) {
        this.flow = flow;
        this.date = date;
        this.count = count;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }
}
