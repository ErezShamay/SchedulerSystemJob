package concrete.tasks;

import tangelo.JobInterval;
import tangelo.JobTask;

import java.util.logging.Logger;

public class DummyCounterJobTask extends JobTask {

    public static class DummyState {
        public int counter = 0;
        public boolean cleanedup = false;
    }

    private DummyState dummyState;

    private static Logger logger = Logger.getLogger(String.valueOf(JobTask.class));



    public DummyCounterJobTask(String name, JobInterval interval, DummyState dummyState) {
        super(name, interval);
        this.dummyState = dummyState;
    }
    @Override
    public void execute() {
        logger.info("executing job with id: " + this.getJobId());
        this.dummyState.counter++;
    }

    @Override
    public void cleanup() {
        logger.info("cleanup job with id: " + this.getJobId());
        this.dummyState.cleanedup = true;
    }
}
