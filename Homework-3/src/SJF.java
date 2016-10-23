import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF {
	
	public static void main(String[] args) 
	{
		if(args.length > 0) {
			//URL = getClass().getResource(arg)
			//String inputfilename = args[0];
			File inputfile = new File(args[0]);
			Scanner scan = null;
			try {
				scan = new Scanner(new FileInputStream(inputfile));
				int numJobs = Integer.parseInt(scan.nextLine());
				SJF sjf = new SJF();
				
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
				
				sjf.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				sjf.sortJobs(jobs);
				
				System.out.println("After sorting");
				
				sjf.printJobs(jobs.toArray(new Job[jobs.size()]));
				
				Collections.copy(runjobs, jobs);
				sjf.runJobsInSJF(runjobs);
				
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
	
	private void runJobsInSJF(List<Job> jobs)
	{
		int time = 0;
		do{
//			if(jobs.get(0).
//					
//					//jobs.get(0).run1TimeUnit(time)){
//				jobs.remove(0);
//			}
			time++;
		}while(!jobs.isEmpty());
			
	}
	
	private void printJobs(Job[] jobs)
	{
		for(int i = 0; i<jobs.length; i++)
		{
			System.out.println("Job " + jobs[i].getReadOrder() + " Arrive Time: " + jobs[i].getArriveTime() + " Run Time: " + jobs[i].getExecuteTime());
		}
	}
}

