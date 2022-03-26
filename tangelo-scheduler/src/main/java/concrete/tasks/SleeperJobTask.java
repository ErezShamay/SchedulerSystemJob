package concrete.tasks;

import tangelo.JobInterval;
import tangelo.JobTask;

import java.util.logging.Logger;

public class SleeperJobTask extends JobTask {
    private int timeToSleepInSeconds;

    private static Logger logger = Logger.getLogger(String.valueOf(JobTask.class));

    public SleeperJobTask(String name, JobInterval interval, int timeToSleepInSeconds) {
        super(name, interval);
        this.timeToSleepInSeconds = timeToSleepInSeconds;
    }
    @Override
    public void execute() {
        try {
            logger.info("executing job with id: " + this.getJobId());
            Thread.sleep(this.timeToSleepInSeconds * 1000);
        } catch (Exception e){
            logger.severe(e.toString());
        }
    }

    @Override
    public void cleanup() {
        try {
            logger.info("cleanup job with id: " + this.getJobId());
            Thread.sleep(this.timeToSleepInSeconds * 1000);
        } catch (Exception e){
            logger.severe(e.toString());
        }
    }
}
