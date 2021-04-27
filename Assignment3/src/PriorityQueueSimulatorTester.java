//-----------------------------------------------
//Assignment 3
//Programming Question Main Driver
//Written by Jordan Chan Kum Sang- 40125997
//-----------------------------------------------
/**
 * Name: Jordan Chan Kum Sang - 40125997
 * Section: COMP 352 Section FF
 * Assignment 3
 * Due Date: November 15, 2020
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class PriorityQueueSimulatorTester {
	/**
	 * Method to generate random numbers
	 * @param max Upper Limit
	 * @return Generated random number within limit
	 */
	public static int randomGenerator( int max) {
		int number;
		
		number =((int) (Math.random()*max)+1);
		
		return number;
	}
	
	/**
	 * Method to generate Sorted List Priorirty Queue
	 * @param jobsInputArray Array of Jobs
	 * @param max Size of array 
	 * @param pw PrintWriter Object
	 */
	public static void PQ_SortedList(Job[] jobsInputArray, int max, PrintWriter pw) {
		
		PQ_SortedList sortedjobs = new PQ_SortedList(max);				//Creating Sorted List Prioirty Queue
		Job.counter = 0; 
		Job.jobsCreated =0;
		
		//Adding to Priority Queue
		for (int i=0; i < max; i++) {
			sortedjobs.enqueue(jobsInputArray[i]);
		}	
			
		int counter =0; 
		int totalChanges = 0; 
		long average = 0; 
			
		long start = System.currentTimeMillis();		
		
		//Executing all jobs in Priority Queue
		while (!sortedjobs.isEmpty()) {
			Job temp; 
			Job.totalnumberofCycles++;
			temp = sortedjobs.dequeue();				//Removing job form Priority Queue
				
			//System.out.println(temp.toString());
					
			if (temp.getJobLengthRemaning() > 0) {
					temp.setJobLengthRemaning(temp.getJobLengthRemaning() -1);
					Job.counter +=1; 
					temp.setCurrentTime((int)Job.counter);
						
					sortedjobs.enqueue(temp);					//Removing job form Priority Queue
					
			}else{
					temp.setwaitTime( (Job.counter -Job.jobsCreated) );
					average += temp.getwaitTime();
					counter++;
						
					if (counter % 30 == 0) {					//Starvation jobs
						temp = sortedjobs.getMaxWait(); 
						if (temp != null ) {
							temp.setfinalPriority(1);
							Job.counter +=1; 
							totalChanges=totalChanges+1;
							
							temp.setentryTime(counter);
							sortedjobs.enqueue(temp);			//Adding back job to Priority Queue
							totalChanges++; 
						}
					}
			}
		}
		long end = System.currentTimeMillis();
		
		//Writing to file
		pw.println("\nCurrent system time (cycles): " + (Job.totalnumberofCycles) );
		pw.println("Total number of jobs executed:  " + jobsInputArray.length +" Jobs");
		pw.println("Average process waiting time:  " + average/max +" Cycles.");
		pw.println("Total number of priority changes:  " + totalChanges );
		pw.println("Actual system time needed to execute all hobs:  " + (end- start) +"ms.");
		pw.println("\n---------------------------------------------\n");
		
		pw.flush();
		
	}
	
	/**
	 * Method to generate Unsorted List Priorirty Queue
	 * @param array Size
	 * @param pw Printwriter Object
	 */
	public static void PQ_UnsortedList(int[] array, PrintWriter pw) {
		
		//Looping trough size of maxNumberOfJobs
		for (int k = 0; k < array.length ; k ++ ) {		
			JobUnsorted[] jobsInputArray = new JobUnsorted[array[k]];
			int cycles = 0;
			int jobsCompleted = 0;
			
			//Creating Jobs
			for (int i = 0; i < jobsInputArray.length; i++) {
				JobUnsorted.jobNumber = i;

				int jobL=randomGenerator(70);
				int jobP=randomGenerator(40);
				
				int finalP=jobP;
				
				String name="Job_"+(i+1);
				jobsInputArray[i]=new JobUnsorted(name, jobL,0, jobP, finalP, 0,0,0);				//Adding job to Array
				
			}
			
			PQ_UnsortedList<JobUnsorted> upq = new PQ_UnsortedList<JobUnsorted>();					//Creating Unsorted Priority Queue
			int priorityCounter  = 1;
			
			//Adding jobs to Priority Queue and initialising entry time and priority
			for (int i = 0; i < jobsInputArray.length; i++) {
				jobsInputArray[i].setentryTime(JobUnsorted.counter);
				jobsInputArray[i].setPriorityBreak(priorityCounter++);
				upq.enqueue(jobsInputArray[i]);
				JobUnsorted.counter++;
				cycles++;
			}		
			
			int starvation = 0;
			
			long startTime = System.currentTimeMillis();		
			int completed = 1;
			
			//Executing all jobs in Priority Queue 
			while(!upq.isEmpty()) {
				
				if (jobsCompleted == 30) {
					jobsCompleted = 0;
					starvation++;
					JobUnsorted temp  = new JobUnsorted();
					
					for (int i = jobsInputArray.length - 1; i >= 0; i--) {
						if (jobsInputArray[i].getcurrentJobLength() - jobsInputArray[i].getjobLength() == 0) {
							temp = jobsInputArray[i];
//							System.out.println(" ***** " + temp.getJobName() + " changed priority to avoid starvation.");
							break;
						} else cycles++;
					}					
					temp.setCurrentPriority(JobUnsorted.starvation);
					temp.setPriorityBreak(JobUnsorted.priorityBreakMemory - jobsInputArray.length + 1);
				}
				JobUnsorted job = upq.dequeue();
				cycles++;
				job.setfinalPriority(1);
				
				JobUnsorted.starvation = job.getCurrentPriority();	
				
				job.setcurrentJobLength();
				int length = job.getcurrentJobLength();
				
				job.setPriorityBreak(job.getPriorityBreak() + jobsInputArray.length);
	
				if (length != 0) {
					upq.enqueue(job);
					cycles++;
					JobUnsorted.priorityBreakMemory = job.getPriorityBreak();
				} else {
					jobsCompleted++;
					job.setendTime(cycles);
					job.setwaitTime();
					
//					System.out.println(completed + ". Total time to complete " + job.getJobName() + ": " + job.getWaitTime() + " cycles.");
					completed++;
				}
			}
			
			long totalTime = System.currentTimeMillis() - startTime;
			long avgWait = 0;
			//Sum of all jobs waitTime
			for (int i = 0; i < jobsInputArray.length; i++) {
				avgWait += jobsInputArray[i].getwaitTime();
			}
			avgWait = avgWait/jobsInputArray.length;			//Calculating Average
			
			completed=(completed * 1);
			
			//Writing to file
			pw.println("\nCurrent system time (cycles): " + cycles);
			pw.println("Total number of jobs executed: " + jobsInputArray.length + " jobs.");
			pw.println("Average process waiting time: " + avgWait + " cycles.");
			pw.println("Total number of priority changes: " + starvation);
			pw.println("Actual system time needed to execute all hobs: " + totalTime + "ms.");
			pw.println("\n---------------------------------------------\n");
		}
	}
	
	/**
	 * Method to generate Array-based Heap Priority Queue
	 * @param jobsInputArray Array of Jobs
	 * @param max Size of array 
	 * @param pw PrintWriter Object
	 */
	public static void PQ_Heap(Job[] jobsInputArray, int max, PrintWriter pw) {
		int count =0; 
		int total = 0 ; 
		long average = 0; 
		
		Job.counter = 0; 
		Job.jobsCreated =0; 
			
		PQ_ArrayHeap jobs = new PQ_ArrayHeap (max);			//Creating Priority Queue
		
		//Adiing job to Priority Queue
		for (int i=0; i < max ; i++) {
			jobs.enqueue(jobsInputArray[i]);				
		}

		long start = System.currentTimeMillis();
		
		//Executing all jobs in Priority Queue
		while (!jobs.isEmpty()) {
			Job temp; 
			Job.totalnumberofCycles++;
			temp = jobs.dequeue();					//Dequeue each job by highest priority
				
			//System.out.println(temp.toString());
			
			if (temp.getJobLengthRemaning() > 0) {
				temp.setJobLengthRemaning(temp.getJobLengthRemaning() -1);
				Job.counter +=1; 
				temp.setCurrentTime((int)Job.counter);

				jobs.enqueue(temp);
			} else {
				temp.setwaitTime((Job.counter -Job.jobsCreated));
				average += temp.getwaitTime();
				//print.println("Total time to complete "+ temp.getJobName()+ ": " + temp.getWaitTime() +" Cycles ");
				count++;

				if (count % 30 == 0) {
					temp = jobs.getMaxWait(); 
					if (temp != null ) {
						//print.println("**To Avoid starvation priority of :  "+ temp.getJobName()+ " Changed to 1 ");
						temp.setfinalPriority(1);
						Job.counter +=1; 

						temp.setentryTime(count);
						jobs.enqueue(temp);
						total++; 
					}
				}
			}

		}
			long end = System.currentTimeMillis();
			
			//Writing to File
			pw.println("");
			pw.println("Current system time (cycles): " + (Job.totalnumberofCycles) );
			pw.println("Total number of jobs executed: " +  (max) +" Jobs");
			pw.println("Average process waiting time: " + (average/max) +" Cycles.");
			pw.println("Total number of priority changes: " + total );
			pw.println("Actual system time needed to execute all hobs: " + (end- start) +"ms.");
			pw.println("\n---------------------------------------------\n");
			
	}
	
	
	
	public static void main(String[] args) {
		//Declaration of Variables and Arrays
		Job[] jobsInputArray, secondlist;
		int[] maxNumberOfJobs= {100,1000,10000,100000};
		int jobL ,finalP, jobP;
		String name;
		
		PrintWriter pw;					//PrintWriter Object
		
		pw=null;
		
		try {
			pw=new PrintWriter (new FileOutputStream("Simulator Performance Results.txt"));					//Creating File
		} catch (FileNotFoundException e) {
			System.out.println("File cannont be created");
		}
		
		
		pw.println("1)  Unsorted List Priority Queue\n");
		PQ_UnsortedList(maxNumberOfJobs,pw);							//Calling Unsorted List Priority Queue method
		
		//Creation of jobs
		for (int i=0; i <maxNumberOfJobs.length; i++) {
			jobsInputArray=new Job[maxNumberOfJobs[i]];
			secondlist=new Job[maxNumberOfJobs[i]];
			
			for (int j=0; j<jobsInputArray.length; j++) {
				 
				
				jobL=randomGenerator(70);
				jobP=randomGenerator(40);
				
				finalP=jobP;
				
				name="Job_"+(j+1);	
				jobsInputArray[j]=new Job(name, jobL,0, jobP, finalP, 0,0,0);			//Adding jobs to Array
				secondlist[j]=new Job(name, jobL,0, jobP, finalP, 0,0,0);
			}
			/*
			for (int k=0; k<jobsInputArray.length; k++) {
				System.out.println(jobsInputArray[k].toString());
			}
			*/
		
			pw.println("\n2)  Sorted List Priority Queue\n");
			PQ_SortedList(secondlist,maxNumberOfJobs[i],pw);				//Calling Sorted List Priority Queue method
			pw.println("\n3)  Pointer-based Heap Priority Queue\n");
			PQ_Heap(jobsInputArray,maxNumberOfJobs[i],pw);						//Calling Array-based Heap Priority Queue method
			
			
		}
		
		System.out.println("Done");
		pw.close();
	}
}
