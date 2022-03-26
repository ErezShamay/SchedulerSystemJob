import concrete.tasks.DummyCounterJobTask;
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
        jobManager.cancelAll();
    }
}