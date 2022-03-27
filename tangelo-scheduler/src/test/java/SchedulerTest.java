import concrete.tasks.*;
import org.junit.*;
import tangelo.JobInterval;
import tangelo.JobManager;
import tangelo.JobTask;

public class SchedulerTest {
    @Test
    public void sanity() throws Exception {
        DummyCounterJobTask.DummyState dummyCounter = new DummyCounterJobTask.DummyState();

        JobManager jobManager = new JobManager();
        JobTask jobTask = new DummyCounterJobTask("DummyCounterJobTask", JobInterval.ThreeSecond, dummyCounter);

        Assert.assertEquals(dummyCounter.counter, 0);
        Assert.assertEquals(dummyCounter.cleanedup, false);

        jobManager.execute(jobTask);

        Thread.sleep(5000);

        Assert.assertEquals(dummyCounter.counter, 1);
        Assert.assertEquals(dummyCounter.cleanedup, true);
        jobManager.introspect();
        jobManager.cancelAll();
    }

    @Test
    public void sleeperTest() throws Exception {
        JobManager jobManager = new JobManager();
        JobTask jobTask1 = new SleeperJobTask("SleeperJobTask1", JobInterval.ThreeSecond, 4);
        JobTask jobTask2 = new SleeperJobTask("SleeperJobTask2", JobInterval.ThreeSecond, 6);
        jobManager.execute(jobTask1);
        jobManager.execute(jobTask2);

        Thread.sleep(5000);

        jobManager.introspect();
        jobManager.cancelAll();
    }
}