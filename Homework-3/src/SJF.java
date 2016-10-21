import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SJF {
	
	private float averageTurnaroundTime;
	private float averageResponseTime;
	
	public static void main(String[] args) 
	{
		if(args.length > 0) {
			String inputfilename = args[0];
			File inputfile = new File(inputfilename);
			try {
				Scanner scan = new Scanner(inputfile);
				int numJobs = Integer.parseInt(scan.nextLine());
				SJF sjf = new SJF();
				
				Job[] jobs = new Job[numJobs];
				for(int i = 1; i < numJobs; i++)
				{
					jobs[i] = new Job(Integer.parseInt(scan.nextLine()), Integer.parseInt(scan.nextLine()));
				}
				
				sjf.runJobs(jobs);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		else {
			System.exit(1);
		}
	}	
	
	private void runJobs(Job[] jobs)
	{
		
	}
	
	static class Job
	{	
		private int arriveTime;
		private int executeTime;
		
		public Job(int arriveTime, int executeTime)
		{
			this.arriveTime = arriveTime;
			this.executeTime = executeTime;
		}
		
		public int getArriveTime()
		{
			return arriveTime;
		}
		public int getExecuteTime()
		{
			return executeTime;
		}
	}
}

