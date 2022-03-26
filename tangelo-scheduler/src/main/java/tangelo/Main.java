package tangelo;

import concrete.tasks.SleeperJobTask;

import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(String.valueOf(JobTask.class));

    public static void main(String[] args) throws Exception {
        /**
         * A dummy playground
         */

        JobManager jobManager = new JobManager();
        JobTask jobTask1 = new SleeperJobTask("test1", JobInterval.Now, 2);
        jobManager.execute(jobTask1);
        JobTask jobTask2 = new SleeperJobTask("test2", JobInterval.ThreeSecond, 3);
        jobManager.execute(jobTask2);

        jobManager.cancelJob(jobTask2.getJobId());

        Thread.sleep(1000);

        logger.info(jobManager.introspect());

        Thread.sleep(5000);

        jobManager.cancelAll();
    }
}
