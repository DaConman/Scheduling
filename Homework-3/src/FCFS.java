import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS {
	
	public static void main(String[] args) 
	{
		if(args.length > 0) {
			File inputfile = new File(args[0]);
			Scanner scan = null;
			try {
				scan = new Scanner(new FileInputStream(inputfile));
				int numJobs = Integer.parseInt(scan.nextLine());
				FCFS FCFS = new FCFS();
				
				List<Job> jobs = new ArrayList<Job>(numJobs);
				List<Job> runjobs = new ArrayList<Job>(numJobs);
				String temp = null;
				int temparrive = 0;
				int tempexecute = 0;
				for(int i = 0; i < numJobs; i++)
				{
					temp = scan.nextLine();
					while(temp.equals(""))
					{
						temp = scan.nextLine();
					}
					temparrive = Integer.parseInt(temp);
					tempexecute = Integer.parseInt(scan.nextLine());
					
					jobs.add(new Job(temparrive, tempexecute, i));
				}
				System.out.println("Before sorting");
				
				FCFS.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				FCFS.sortJobs(jobs);
				
				System.out.println("After sorting");
				
				FCFS.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				Collections.copy(runjobs, jobs);
				FCFS.runJobsInFCFS(runjobs);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
			}
			finally{
				scan.close();
			}
		}
		else {
			System.exit(1);
		}
	}	
	
	private void sortJobs(List<Job> jobs)
	{
		Collections.sort(jobs, new Comparator<Job>() {
			public int compare(Job job1, Job job2) {
				return job1.compareArriveTo(job2);
			}
		});
	}
	
	private void runJobsInFCFS(List<Job> jobs)
	{
		int time = 0;
		do{
//			if(jobs.get(0).
//					
//					jobs.get(0).run1TimeUnit(time)){
//				jobs.remove(0);
//			}
			time++;
		}while(!jobs.isEmpty());
			
	}
	
	private void printJobs(Job[] jobs)
	{
		for(int i = 0; i<jobs.length; i++)
		{
			System.out.println("Job " + jobs[i].readOrder + " Arrive Time: " + jobs[i].getArriveTime() + " Run Time: " + jobs[i].getExecuteTime());
		}
	}
	
	static class Job
	{	
		private int arriveTime;
		private int executeTime;
		private int readOrder;
		private int startTime;
		private int endTime;
		private int timeLeft;
		private boolean done = false;
		
		public Job(int arriveTime, int executeTime, int readOrder)
		{
			this.arriveTime = arriveTime;
			this.executeTime = executeTime;
			this.readOrder = readOrder;
			this.timeLeft = executeTime;
		}
		
		public boolean isDone(){return done;}
		public int getArriveTime(){return arriveTime;}
		public int getExecuteTime(){return executeTime;}
		public int getReadOrder(){return readOrder;}
		public int getStartTime(){return startTime;}
		public int getEndTime(){return endTime;}
		
		public int compareArriveTo(Job otherJob)
		{
			return (this.getArriveTime() - otherJob.getArriveTime()); //three cases, this arr > other +, this arr = other 0, this arr < -
		}
		
		public boolean run1TimeUnit(int currentTime)
		{
			if(timeLeft == executeTime)
				startTime = currentTime;
			if(timeLeft > 0)
			{
				timeLeft--;
			}
			else
			{
				done = true;
				endTime = currentTime;
			}
			return isDone();
		}
		
		public int TurnaroundTime()
		{
			return endTime - arriveTime;
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
}