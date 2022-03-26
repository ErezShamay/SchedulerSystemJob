package tangelo;

import java.util.UUID;

public class JobDetails {
    private String name;
    private JobInterval interval;
    private String jobId = UUID.randomUUID().toString();

    public JobDetails(String name, JobInterval interval) {
        this.name = name;
        this.interval = interval;
    }

    public String getJobId() {
        return jobId;
    }

    public String getName() {
        return name;
    }

    public JobInterval getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        return "tangelo.JobDetails{" +
                "name='" + name + '\'' +
                ", interval=" + interval +
                ", jobId=" + jobId +
                '}';
    }
}
