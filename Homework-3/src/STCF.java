import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class STCF {
	
	public static void main(String[] args) 
	{
		if(args.length > 0) {
			File inputfile = new File(args[0]);
			Scanner scan = null;
			try {
				scan = new Scanner(new FileInputStream(inputfile));
				int numJobs = Integer.parseInt(scan.nextLine());
				STCF STCF = new STCF();
				
				List<Job> jobs = new ArrayList<Job>(numJobs);
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
					
					jobs.add(new Job(i, temparrive, tempexecute));
				}
				//System.out.println("Before sorting");
				
				//STCF.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				STCF.sortJobs(jobs);
				
				//System.out.println("After sorting");
				
				//STCF.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				List<Job> runjobs = new ArrayList<Job>(jobs);
				jobs = STCF.runJobsInSTCF(runjobs);
				
				System.out.println(String.format("%.5f", Job.MeanTurnaroundTime(jobs.toArray(new Job[jobs.size()]))));
				System.out.println(String.format("%.5f", Job.MeanResponseTime(jobs.toArray(new Job[jobs.size()]))));
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
	
	private List<Job> runJobsInSTCF(List<Job> jobs)
	{
		List<Job> jobsout = new ArrayList<Job>();
		
		int time = 0;
		int jobnum = 1;
		Job currentJob = selectJob(jobs, time);
		System.out.print("Begin jobs: ");
		while(!jobs.isEmpty())
		{	
			currentJob = selectJob(jobs, time);
			if(currentJob.getArriveTime() <= time)
			{
				System.out.println("Job " + currentJob.getJobId() + " run");
				System.out.print("Time: " + time +  "  Time Left: " + currentJob.getTimeLeft());
				if(currentJob.run1TimeUnit(time)){
					jobsout.add(jobs.remove(jobs.indexOf(currentJob)));
				}
				time++; 
				System.out.println();
			}
			else{
				System.out.println("Time: " + time);
				time++;
			}
		}
		return jobsout;
	}
	
	private Job selectJob(List<Job> jobs, int time)
	{
		ListIterator<Job> JobIterator = jobs.listIterator();
		Job temp = null;
		Job execute = null;
		while(JobIterator.hasNext())
		{
			temp = JobIterator.next();
			if(execute == null)
				execute = temp;
			if(temp.getArriveTime() <= time && temp.getTimeLeft() < execute.getTimeLeft())
			{
				execute = temp;
			}
		}
		
		return execute;
	}
	
	private void printJobs(Job[] jobs)
	{
		for(int i = 0; i<jobs.length; i++)
		{
			System.out.println("Job " + jobs[i].getReadOrder() + " Arrive Time: " + jobs[i].getArriveTime() + " Run Time: " + jobs[i].getExecuteTime());
		}
	}
	
}