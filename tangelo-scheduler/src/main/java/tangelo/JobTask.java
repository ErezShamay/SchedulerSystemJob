package tangelo;

import java.util.*;
import java.util.logging.Logger;

public abstract class JobTask extends TimerTask {
    private JobDetails jobDetails;
    private JobStatus jobStatus = JobStatus.New;

    private static Logger logger = Logger.getLogger(String.valueOf(JobTask.class));

    public JobTask(String name, JobInterval interval) {
        this.jobDetails =  new JobDetails(name, interval);
    }

    public void run() {
        logger.info(String.format("Task is running %s", jobDetails.toString()));
        try {
            this.jobStatus = JobStatus.Running;
            execute();
            this.jobStatus = JobStatus.Done;
        } catch(Exception e) {
            this.jobStatus = JobStatus.Failed;
            this.logger.severe(e.toString());
        } finally {
            cleanup();
        }
    }

    public boolean cancel() {
        logger.info(String.format("Task is canceled %s", jobDetails.toString()));
        return super.cancel();
    }

    public abstract void execute();
    public abstract void cleanup();

    public String getJobId() {
        return this.jobDetails.getJobId();
    }

    public JobInterval getJobInterval() {
        return this.jobDetails.getInterval();
    }

    @Override
    public String toString() {
        return "JobTask{" +
                "jobDetails=" + jobDetails +
                ", jobStatus=" + jobStatus +
                '}';
    }
}
