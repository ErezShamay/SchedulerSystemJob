package tangelo;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class JobManager {

    private HashMap<String, TimerJobTask> timersMap = new HashMap<>();

    public void execute(JobTask jobTask) {
        Timer timer = new Timer();

        if (jobTask.getJobInterval() == JobInterval.Now) {
            timer.schedule(jobTask, 0);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, jobTask.getJobInterval().getValue());
            timer.scheduleAtFixedRate(jobTask, calendar.getTime(), jobTask.getJobInterval().getValue() * 60 * 60 * 1000);
        }

        timersMap.put(jobTask.getJobId(), new TimerJobTask(timer, jobTask));
    }

    public void cancelJob(String jobId) throws IllegalStateException {
        TimerJobTask timerJobTask = timersMap.get(jobId);
        if (timerJobTask == null) {
            throw new IllegalStateException("No such job: " + jobId);
        }
        timerJobTask.getTask().cancel();
        timerJobTask.getTimer().cancel();
    }

    public void cancelAll() {
        for (Map.Entry<String,TimerJobTask> entry : timersMap.entrySet()) {
            System.out.println("Going to cancel: " + entry.getKey());
            entry.getValue().getTask().cancel();
            entry.getValue().getTimer().cancel();
        }
    }

    public String introspect() {
        StringBuffer result = new StringBuffer();
        result.append("The number of running jobs are: " + timersMap.size());
        result.append("\n");
        result.append("The state of jobs are: ");
        for (Map.Entry<String,TimerJobTask> entry : timersMap.entrySet()) {
            result.append(entry.getValue().getTask().toString());
            result.append("\n");
        }

        return result.toString();
    }
}
