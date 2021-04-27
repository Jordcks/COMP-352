//-----------------------------------------------
//Assignment 3
//Programming Question Job Class
//Written by Jordan Chan Kum Sang- 40125997
//-----------------------------------------------
/**
 * Name: Jordan Chan Kum Sang - 40125997
 * Section: COMP 352 Section FF
 * Assignment 3
 * Due Date: November 15, 2020
 */

/**
 * Job Class for Sorted List
 * @author jordan
 *
 */
public class Job implements Comparable<Job>{
	//Private Attributes
	private String jobName;
	private int jobLength;
	private int currentJobLength;
	private int jobPriority;
	private int finalPriority;
	private long entryTime;
	private long endTime; 
	private long waitTime;
	
	
	private int jobLengthRemaning;
	private int currentTime;
	public static int totalnumberofCycles =0;
	public static long counter; 
	public static int jobsCreated=0;
	
	/**
	 * Default Constructor
	 */
	public Job() {
		jobName="";
		jobLength=0;
		currentJobLength=0;
		jobPriority=0;
		finalPriority=0;
		entryTime=0;
		endTime=0;
		waitTime=0;
		
		this.entryTime = ++jobsCreated;
		currentTime = (int) this.entryTime;
		counter = jobsCreated; 
		jobLengthRemaning = jobLength; 
	}
	
	/**
	 * Pamaterized Constructor
	 * @param name jobName
	 * @param len jobLength
	 * @param current currentJobLength
	 * @param priority jobPriority
	 * @param fin finalPriority
	 * @param entry entryTime
	 * @param end endTime
	 * @param wait waitTime
	 */
	public Job(String name, int len, int current, int priority, int fin, long entry, long end, long wait ) {
		this.jobName=name;
		this.jobLength=len;
		this.currentJobLength=current;
		this.jobPriority=priority;
		this.finalPriority=fin;
		this.entryTime=entry;
		this.endTime=end;
		this.waitTime=wait;
		
		this.entryTime = ++jobsCreated;
		currentTime = (int) this.entryTime;
		counter = jobsCreated; 
		jobLengthRemaning = jobLength; 
	}
	
	/**
	 * Copy constrcutor
	 * @param job Job Object
	 */
	public Job(Job job) {
		this.jobName=job.jobName;
		this.jobLength=job.jobLength;
		this.currentJobLength=job.currentJobLength;
		this.jobPriority=job.jobPriority;
		this.finalPriority=job.finalPriority;
		this.entryTime=job.entryTime;
		this.endTime=job.endTime;
		this.waitTime=job.waitTime;
	}
	
	//Accessor method
	/**
	 * Return Job Name
	 * @return jobName
	 */
	public String getjobName() {
		return jobName;
	}
	
	/**
	 * Return job Length
	 * @return jobLength
	 */
	public int getjobLength() {
		return jobLength;
	}
	
	/**
	 * Return current Job length
	 * @return currentJobLength
	 */
	public int getcurrentJobLength(){
		return currentJobLength;
	}
	
	/**
	 * Return Job Priority
	 * @return jobPriority
	 */
	public int getjobPriority() {
		return jobPriority;
	}
	
	/**
	 * Return final priority
	 * @return finalPriority
	 */
	public int getfinalPriority() {
		return finalPriority;
	}
	
	/**
	 * Return entry time
	 * @return entryTime
	 */
	public long getentryTime() {
		return entryTime;
	}
	
	/**
	 * Return end time
	 * @return endTime
	 */
	public long getendTime() {
		return endTime;
	}
	
	/**
	 * Return waitTime
	 * @return waitTime
	 */
	public long getwaitTime() {
		return waitTime;
	}
	
	/**
	 * get the current time 
	 * @return currentTime 
	 */
	public int getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * get the lengh of job remaining
	 * @return jobLengthRemaning
	 */
	public int getJobLengthRemaning() {
		return jobLengthRemaning;
	}
	
	//Mutator Method
	/**
	 * set the current time
	 * @param currentTime currentRime
	 */
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	
	/**
	 * Set job Name
	 * @param name jobName
	 */
	public void setjobName(String name) {
		this.jobName=name;
	}
	
	/**
	 * set the job length
	 * @param len jobLength
	 */
	public void setjobLength(int len) {
		this.jobLength=len;
	}
	
	/**
	 * set the current job length
	 * @param current currentJobLength
	 */
	public void setcurrentJobLength(int current) {
		this.currentJobLength=current;
	}
	
	/**
	 * Set the job priority
	 * @param priority jobPrioirty
	 */
	public void setjobPriority(int priority) {
		this.jobPriority=priority;
	}
	
	/**
	 * Set the final job priority
	 * @param fin finalPriority
	 */
	public void setfinalPriority(int fin) {
		this.finalPriority=fin;
	}
	
	/**
	 * Set the entery time 
	 * @param entry entryTime
	 */
	public void setentryTime(int entry) {
		this.entryTime=entry;
	}
	
	/**
	 * set the end time 
	 * @param end endTime
	 */
	public void setendTime(long end) {
		this.endTime=end;
	}
	
	/**
	 * set the wait time 
	 * @param wait waitTime
	 */
	public void setwaitTime(long wait) {
		this.waitTime=wait;

	}
	
	/**
	 * set the leng of the job reamining 
	 * @param jobLengthRemaning remaining job length
	 */
	public void setJobLengthRemaning(int jobLengthRemaning) {
		this.jobLengthRemaning = jobLengthRemaning;
	}
	
	/**
	 * toString Method yto return descriptive message
	 */
	public String toString() {
		return ("Now executing "+this.jobName+". job Length: "+this.jobLength+" cycles; Current remaining length: "+this.jobLengthRemaning+" cycles; Intial priority: "
				+this.jobPriority+"; Current Priority: "+this.finalPriority);
	}

	@Override
	/**
	 * compareTo one job to another basd on current time and final priority. 
	 */
	public int compareTo(Job job) {
		if (job.finalPriority == this.finalPriority) {
			return (int) ( this.currentTime - job.currentTime);
		}
		return this.finalPriority - job.finalPriority;
	}

}

