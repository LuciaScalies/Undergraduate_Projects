/* 
 Assignments.java		Author: AMH, LJS, CIS 220 01 Sp16
 Creates an Assignment storing the possible points, points earned, and its topic
*/
public class Assignments
{
	private int possible; //the potential points earned for an assignment
	private int earned; //the points earned on an Assignment
	private String topic; //the name of the assignment
	
	public Assignments(int p, int e, String t)
	{
		possible = p;
		earned = e;
		topic = t;
	}
	
	//points accessor
	public int getPossible()
	{
		return possible;
	}
	
	//credit accessor
	public double getEarned()
	{
		return earned;
	}
	
	//name accessor
	public String getTopic()
	{
		return topic;
	}
	
	//points modifier
	public void setPossible(int num)
	{
		possible = num;
	}
	
	//credit modifier
	public void setEarned(int num)
	{
		earned = num;
	}
	
	//name modifier
	public void setTopic(String str)
	{
		topic = str;
	}
	
	public String toString()
	{
		return earned + "/" + possible + " on " + topic;
	}
}