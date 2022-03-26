package tangelo;

import java.util.Timer;

public class TimerJobTask {
    private Timer timer;
    private JobTask task;

    public TimerJobTask(Timer timer, JobTask task) {
        this.timer = timer;
        this.task = task;
    }

    public Timer getTimer() {
        return timer;
    }

    public JobTask getTask() {
        return task;
    }
}