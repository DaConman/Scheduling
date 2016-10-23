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
				//System.out.println("Before sorting");
				
				//FCFS.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				FCFS.sortJobs(jobs);
				
				//System.out.println("After sorting");
				
				//FCFS.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				List<Job> runjobs = new ArrayList<Job>(jobs);
				jobs = FCFS.runJobsInFCFS(runjobs);
				
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
	
	private List<Job> runJobsInFCFS(List<Job> jobs)
	{
		List<Job> jobsout = new ArrayList<Job>(jobs);
		
		int time = 0;
		//int jobnum = 1; //Debug
		Job currentJob = jobs.get(0);
		//System.out.print("Begin jobs: "); //Debug
		while(!jobs.isEmpty())
		{	
			currentJob = jobs.get(0);
			if(currentJob.getArriveTime() <= time)
			{
				//System.out.println("Job " + jobnum + " started"); //Debug
				//System.out.print("Time: ");//r
				while(!currentJob.isDone())
				{
					currentJob.run1TimeUnit(time);
					//System.out.print(" " + time);
					time++; 
				}
				//System.out.println();//r
				jobsout.add(jobs.remove(0));
				//jobnum++;
			}
			else{
				//System.out.println("Time: " + time);
				time++;
			}
		}
			
		return jobsout;
	}
	
	private void printJobs(Job[] jobs)
	{
		for(int i = 0; i<jobs.length; i++)
		{
			System.out.println("Job " + jobs[i].getReadOrder() + " Arrive Time: " + jobs[i].getArriveTime() + " Run Time: " + jobs[i].getExecuteTime());
		}
	}
	
}