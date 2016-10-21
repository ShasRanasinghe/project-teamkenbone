package network;

import java.util.*;

/**
 * @author Alex Hoecht, Andrew Ward, Mohamed Dahrouj, Shasthra Ranasinghe
 * @version 1.0
 * 
 * The controller is the class that interacts with the user.
 * All the information taken from the user and printed to the user
 * goes through the controller class
 *
 */
public class Controller {
	
	private Scanner in;
	private String inputLine;
	
	private StringTokenizer tokenizer;
	
	private ArrayList<String> nodes;
	private ArrayList<String> links;
	
	private int frequency;
	private boolean testing;
	
	/**
	 * The constructor initializes the lists and scanner needed for the controller
	 */
	public Controller(){
		in = new Scanner(System.in);
		nodes = new ArrayList<String>();
		links = new ArrayList<String>();
		
		//request use to use the default graph
		for(;;){
			//request user for verbose or quiet mode
			System.out.print("Would you like to use the default network shown in the project specification"
					+ "with a user-settable rate of 5? (Y/N)");
			inputLine = inputLine(in.nextLine());
			if(inputLine.equals("Y")){
				testing = true;
				System.out.println("You have chosen default mode");
				break;
			}else if(inputLine.equals("N")){
				testing = false;
				System.out.println("You have chosen use defined mode");
				break;
			}//if input is invalid, ask again
		}
	}
	
	/**
	 * The start method requests the user for the nodes
	 * the links and the user-stable rate and saves them in variables
	 * to be taken by the Simulation
	 */
	public void start(){
		System.out.println("Starting New Program...");
		System.out.println("Enter \"quit\" at any time to exit the program");
		
		System.out.println("Initializing Simulation...");
		
		initializeNetwork();
		
		System.out.print("Enter user-settable rate: ");
		inputLine = inputLine(in.nextLine());
		if(Integer.parseInt(inputLine)<=4) System.out.println("Beware this might take an infinite amount of time");
		frequency = Integer.parseInt(inputLine);
	}

	/**
	 * @return user-setable rate
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * @param line The input is a string obtained from the user
	 * @return the same string it received unless its "quit"
	 * 
	 * This method, takes in an input from the user, checks if
	 * it is "quit" if it isnt it will simply return the same string back
	 * otherwise, it will end the program
	 * 
	 */
	private String inputLine(String line){
		switch(line){
		case "quit": System.out.println("Ending Program...");
						endSimulation();
						System.exit(0);
		default: return line;
		}
	}
	
	/**
	 * This methods is called in the start method to request the user 
	 * for the list of nodes and their links
	 */
	private void initializeNetwork() {
		String link, firstNode,secondNode;
		
		System.out.print("Enter All Nodes Seperated With Spaces: ");
		inputLine = inputLine(in.nextLine());
		tokenizer = new StringTokenizer(inputLine);
		while(tokenizer.hasMoreTokens()){
			nodes.add(tokenizer.nextToken());
		}

		System.out.println("\nEnter node links one at a time seperated with spaces");
		System.out.println("for example if Node A links to B, type \"A B\" and press enter for the next link");
		System.out.println("when you are done, type done");
		System.out.println("Enter Node Links: ");
		for(;;){
			inputLine = inputLine(in.nextLine());
			
			if(inputLine.equals("done")) break;
			
			tokenizer = new StringTokenizer(inputLine);
			if(tokenizer.hasMoreTokens()){
				firstNode = tokenizer.nextToken();
				if(isNode(firstNode)){
					link = firstNode + "->";
				}else{
					System.out.println("The first Node does not exist");
					continue;
				}
				if(tokenizer.hasMoreTokens()){
					secondNode = tokenizer.nextToken();
					if(isNode(secondNode)){
						if(secondNode.equals(firstNode)){
							System.out.println("The two nodes you have entered are the same");
							continue;
						}else{
							link += secondNode;
							links.add(link);
						}
					}else{
						System.out.println("The second Node does not exist");
						continue;
					}
				}
			}
		}
		System.out.println("You Have Initialized The Network");
		System.out.println("Algorithm Being Used: RANDOM");
	}
	
	/**
	 * @param node a node provided by the user
	 * @return returns true if the node exists, false otherwise
	 */
	private boolean isNode(String node){
		for(String str: nodes){
			if(node.equals(str)) return true;
		}
		return false;
	}

	/**
	 * closes the scanner when the program ends
	 */
	public void endSimulation() {
		in.close();
	}

	/**
	 * @return list of Nodes provided by the user
	 */
	public ArrayList<String> getNodes() {
		return nodes;
	}

	/**
	 * @return list of links between nodes that the simulation will parse
	 */
	public ArrayList<String> getEdges() {
		return links;
	}
	
	public boolean isTesting() {
		return testing;
	}

	/**
	 * @param msg The message being sent
	 * @param startNode The node where the message started
	 * @param endNode The final destination of the node
	 * @param hops the number of hops taken by the message
	 */
	public void printMessage(String msg, String startNode, String endNode, int hops){
		System.out.println(msg + " = " + startNode + " -> " + endNode + " | " + "Average Hops: " + hops);
	}
	
	/**
	 * @param packets
	 * 
	 * Print out the total number of packets
	 */
	public void printTotalPackets(int packets){
		System.out.println("Total number of Packets: " + packets);
	}
	
	/**
	 * Shows the user the algorithm is running
	 */
	public void runAlgorithm(){
		System.out.println("Running Algorithm....\n");
	}
	
	/**
	 * @param packets the number of packets for one message
	 * @param avgHops average hops of all the messages sent
	 */
	public void printMetrics(int packets, int avgHops){
		System.out.println("Number of Packets: " + packets);
		System.out.println("Average Number of Hops: " + avgHops);
	}

}