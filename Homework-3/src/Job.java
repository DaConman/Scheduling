public class Job
{	
	private int jobid;
	private int arriveTime;
	private int executeTime;
	private int readOrder;
	private int startTime;
	private int endTime;
	private int timeLeft;
	private boolean done = false;
	
	public Job(int jobid, int arriveTime, int executeTime)
	{
		this.jobid = jobid;
		this.arriveTime = arriveTime;
		this.executeTime = executeTime;
		this.readOrder = readOrder;
		this.timeLeft = executeTime;
	}
	
	public boolean isDone(){return done;}
	public int getJobId(){return jobid;}
	public int getArriveTime(){return arriveTime;}
	public int getExecuteTime(){return executeTime;}
	public int getReadOrder(){return readOrder;}
	public int getStartTime(){return startTime;}
	public int getEndTime(){return endTime;}
	public int getTimeLeft(){return timeLeft;}
	
	public int compareArriveTo(Job otherJob)
	{
		return (this.getArriveTime() - otherJob.getArriveTime()); //three cases, this arr > other +, this arr = other 0, this arr < -
	}
	
	public boolean run1TimeUnit(int currentTime)
	{
		if(timeLeft == executeTime)
			startTime = currentTime;
		if(!isDone())
		{
			timeLeft--;
			if(timeLeft == 0)
			{
				done = true;
				endTime = currentTime;
			}
		}
		return isDone();
	}
	
	public int TurnaroundTime()
	{
		return endTime - arriveTime + 1;
	}
	
	public int ResponseTime()
	{
		return startTime - arriveTime;
	}
	
	public static float MeanTurnaroundTime(Job[] jobs)
	{
		float sum = 0;
		for(int i = 0; i<jobs.length; i++)
		{
			sum = jobs[i].TurnaroundTime() + sum;
		}
		return sum/jobs.length;
	}
	
	public static float MeanResponseTime(Job[] jobs)
	{
		float sum = 0;
		for(int i = 0; i<jobs.length; i++)
		{
			sum = jobs[i].ResponseTime() + sum;
		}
		return sum/jobs.length;
	}

}
