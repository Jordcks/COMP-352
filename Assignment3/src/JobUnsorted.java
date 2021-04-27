//-----------------------------------------------
//Assignment 3
//Programming Question Unsorted Job Class
//Written by Jordan Chan Kum Sang- 40125997
//-----------------------------------------------
/**
 * Name: Jordan Chan Kum Sang - 40125997
 * Section: COMP 352 Section FF
 * Assignment 3
 * Due Date: November 15, 2020
 */

/**
 * Job Class for Unsorted List
 * @author jordan
 *
 */
public class JobUnsorted implements Comparable<JobUnsorted>{
	//Private Attributes
	private String jobName;
	private int jobLength;
	private int currentJobLength;
	private int jobPriority;
	private int finalPriority;
	private long entryTime;
	private long endTime; 
	private long waitTime;
	
	private int currentPriority;
	static int priorityBreakMemory = 0;
	static int starvation = 0;
	int priorityBreak = 0;
	static int jobNumber;
	static int counter = 1;
	
	/**
	 * Default Constructor
	 */
	public JobUnsorted() {
		jobName="";
		jobLength=0;
		currentJobLength=0;
		jobPriority=0;
		finalPriority=0;
		entryTime=0;
		endTime=0;
		waitTime=0;
		currentPriority=0;
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
	public JobUnsorted(String name, int len, int current, int priority, int fin, long entry, long end, long wait ) {
		this.jobName=name;
		this.jobLength=len;
		this.currentJobLength=len;
		this.jobPriority=priority;
		this.finalPriority=fin;
		this.entryTime=entry;
		this.endTime=end;
		this.waitTime=wait;
		
		this.currentPriority=priority;
	}
	
	/**
	 * Copy constrcutor
	 * @param job Job Unsorted
	 */
	public JobUnsorted(JobUnsorted job) {
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
	 * Return Job Nane
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
	 * Return priority of job
	 * @return priorityBreak
	 */
	public int getPriorityBreak() {
		return this.priorityBreak;
	}
	
	/** 
	 * get current priority
	 * @return currentPriority
	 */
	public int getCurrentPriority() {
		return currentPriority;
	}

	//Mutator Method
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
	 * set the current job length by -1
	 */
	public void setcurrentJobLength() {
		this.currentJobLength--;
	}
	
	/**
	 * Set the job priority
	 * @param priority jobPriority
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
	 * @param entry entrytime
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
	 * Set wait time 
	 */
	public void setwaitTime() {
		this.waitTime = this.endTime - this.entryTime - this.jobLength;
	}
	
	/**
	 * used to break even when two jobs have same priority
	 * @param x priorityBreak
	 */
	public void setPriorityBreak(int x) {
		this.priorityBreak = x;
	}

	/**
	 * Set current priority
	 * @param currentPriority currentPriority
	 */
	public void setCurrentPriority(int currentPriority) {
		this.currentPriority = currentPriority;
	}
	
	/**
	 * toString Method yto return descriptive message
	 */
	public String toString() {
		return ("Now executing "+this.jobName+". job Length: "+this.jobLength+" cycles; Current remaining length: "+this.currentJobLength+" cycles; Intial priority: "
				+this.jobPriority+"; Current Priority: "+this.finalPriority);
	}

	/**
	 * return the priority of final priority
	 * @return final priority
	 */
	@Override
	public int compareTo (JobUnsorted other) {
		if (Integer.compare(this.getCurrentPriority(), other.getCurrentPriority()) != 0 ) 
			return Integer.compare(this.getCurrentPriority(), other.getCurrentPriority());
		else
			return Integer.compare(this.getPriorityBreak(), other.getPriorityBreak());
	}

}
