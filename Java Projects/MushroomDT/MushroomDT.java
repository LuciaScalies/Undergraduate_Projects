
//Main Author: Dr. Amanda Holland-Minkley
//Student: Dominic Joseph Scalies
//Sources: preOrder transversal in Lafore pg. 411
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// object class representing a single data item
class Mushroom {
	public String classification; // "poisonous" or "edible"
	public String[] values; // attribute vector

	public Mushroom(String c, String[] v) {
		classification = c;
		values = v;
	}
}

// node within the DecisionTree
class Node {
	public Node[] children; // depending on the attribute, a variable number of
							// children
	// note that children will be assumed to be attached in the same order as
	// the values
	// are listed in the "values" array of the DecisionTree class
	public int index; // index of the attribute associated with this node if it
						// is internal
	public String classification; // classification to return if this node is a
									// leaf

	public Node() {
		children = null;
		index = -1;
		classification = null;
	}

	public Node(int num, int i) {
		children = new Node[num];
		index = i;
		classification = null;
	}

	public Node(String clss) {
		children = null;
		index = -1;
		classification = clss;
	}

	// Dominic Added, Question 6, edited for question 7
	// Displays the summary of the full tree
	public void display(Node current, int level, String[] labels, String[][] values) {
		if (current.index != -1) {
			// base case inherent in for loop; display is only called on
			// children with no classification
			// recursive case
			System.out.println("Level " + level + ": Attribute " + labels[current.index]);
			System.out.print("      " + current.children.length + " Children: ");
			for (int i = 0; i < current.children.length; i++) {
				if (i == current.children.length - 1) {
					if (current.children[i].index != -1) {
						System.out.print(values[current.index][i] + "\n");
					} else {
						if (current.children[i].classification.equals("poisonous")) {
							System.out.print("poisonous\n");
						} else {
							System.out.print("edible\n");
						}
					}
				} else {
					if (current.children[i].index != -1) {
						System.out.print(values[current.index][i] + ", ");
					} else {
						if (current.children[i].classification.equals("poisonous")) {
							System.out.print("poisonous, ");
						} else {
							System.out.print("edible, ");
						}
					}
				}
			}
			for (int i = 0; i < current.children.length; i++) {
				if (current.children[i].classification == null) {
					display(current.children[i], level + 1, labels, values);
				}
			}
		}
	}
}

// class implementing a decision tree
class DecisionTree {
	public Node root; // link to the root node of the tree
	public int numAttributes; // number of attributes tree can be built over
	public String[][] values; // set of possible values for all attributes

	public DecisionTree(String[][] v) {
		values = v;
		numAttributes = values.length;
		root = null;
	}

	// recursive method that, given a list of data to construct a decision tree
	// over an a list indicating which attributes have and have not been used
	// yet, selects out the node with the lowest weighted average impurity to
	// be the root and recurses to generate valid decision trees for the
	// children - returns the entire decision tree
	public Node recBuild(MushList list, boolean[] usedAttribute) {
		// checks to see if should stop recursing
		// if all attributes have been used already, return a node
		// classifying based on the consensus of the list
		// DJS Note: Base Case 1
		boolean allUsed = true;
		for (int i = 0; i < usedAttribute.length; i++) {
			allUsed = allUsed && usedAttribute[i];
		}
		if (allUsed)
			return new Node(list.consensus());

		// if current list has all items with the same classification, return
		// a node with no index but the appropriate classification indicated
		// DJS Note: Base Case 2
		if (list.allPoison())
			return new Node("poisonous");
		// DJS Note: Base Case 3
		if (list.allEdible())
			return new Node("edible");

		// calculate the weighted average impurity for a root node with each
		// unused attribute as a possible attribute
		double[] wimpurities = new double[usedAttribute.length];
		int listsize = list.getNum();

		for (int i = 0; i < wimpurities.length; i++) {
			// i is the index of the current attribute considering
			// only proceed if that attribute has not been used yet
			if (!usedAttribute[i]) {
				wimpurities[i] = 0;
				// look up how many values the attribute at that index has
				int numValues = values[i].length;

				for (int j = 0; j < numValues; j++) {
					// number of items in list with value
					double numValue = list.getValueCount(i, values[i][j]);
					// number of items in list with value that are poisonous
					double numPoison = list.getClassCount(i, values[i][j], "poisonous");
					// number of items in list with value that are edible
					double numEdible = list.getClassCount(i, values[i][j], "edible");
					double term = (numValue / listsize) * (1 - (numEdible / numValue) - (numPoison / numValue));
					wimpurities[i] += term;
				}
			}
		}

		// wimpurities stores the weighted average impurity for each attribute
		// over list
		// find the lowest one, make the associated attribute the node to return
		double lowest = Integer.MAX_VALUE;
		int lowAttribute = -1;
		for (int i = 0; i < numAttributes; i++) {
			if (!usedAttribute[i] && wimpurities[i] < lowest) {
				lowest = wimpurities[i];
				lowAttribute = i;
			}
		}

		// if there is no such attribute, simply return a node that classifies
		// based on the consensus of the list
		if (lowAttribute == -1)
			return new Node(list.consensus());

		// an attribute has been selected for the root of the decision tree
		Node newNode = new Node(values[lowAttribute].length, lowAttribute);

		// recurse on each child with the appropriate sublist
		usedAttribute[lowAttribute] = true;
		for (int i = 0; i < values[lowAttribute].length; i++) {
			MushList sublist = list.getSublist(lowAttribute, values[lowAttribute][i]);
			newNode.children[i] = recBuild(sublist, usedAttribute);
		}

		return newNode;
	}

	// recursive method that, given a list of data to construct a decision tree
	// over an a list indicating which attributes have and have not been used
	// yet, selects out the node with the lowest weighted average impurity to
	// be the root and recurses to generate valid decision trees for the
	// children - returns the entire decision tree with the most valuable node removed
/*	public Node recBuild(MushList list, boolean[] usedAttribute, int calls) 
	{
		// checks to see if should stop recursing
		// if all attributes have been used already, return a node
		// classifying based on the consensus of the list
		// DJS Note: Base Case 1
		boolean allUsed = true;
		int numCalls = calls;
		for (int i = 0; i < usedAttribute.length; i++) {
			allUsed = allUsed && usedAttribute[i];
		}
		if (allUsed)
			return new Node(list.consensus());

		// if current list has all items with the same classification, return
		// a node with no index but the appropriate classification indicated
		// DJS Note: Base Case 2
		if (list.allPoison())
			return new Node("poisonous");
		// DJS Note: Base Case 3
		if (list.allEdible())
			return new Node("edible");

		// calculate the weighted average impurity for a root node with each
		// unused attribute as a possible attribute
		double[] wimpurities = new double[usedAttribute.length];
		int listsize = list.getNum();

		for (int i = 0; i < wimpurities.length; i++) {
			// i is the index of the current attribute considering
			// only proceed if that attribute has not been used yet
			if (!usedAttribute[i]) {
				wimpurities[i] = 0;
				// look up how many values the attribute at that index has
				int numValues = values[i].length;

				for (int j = 0; j < numValues; j++) {
					// number of items in list with value
					double numValue = list.getValueCount(i, values[i][j]);
					// number of items in list with value that are poisonous
					double numPoison = list.getClassCount(i, values[i][j], "poisonous");
					// number of items in list with value that are edible
					double numEdible = list.getClassCount(i, values[i][j], "edible");
					double term = (numValue / listsize) * (1 - (numEdible / numValue) - (numPoison / numValue));
					wimpurities[i] += term;
				}
			}
		}

		// wimpurities stores the weighted average impurity for each attribute
		// over list
		// find the lowest one, make the associated attribute the node to return
		double lowest = Integer.MAX_VALUE;
		int lowAttribute = -1;
		int secLowAttribute = -2;
		double secondLow = Integer.MAX_VALUE;
		for (int i = 0; i < numAttributes; i++) {
			if (!usedAttribute[i] && wimpurities[i] < lowest) {
				secondLow = lowest;
				secLowAttribute = lowAttribute;
				lowest = wimpurities[i];
				lowAttribute = i;
			}
		}
		
		if(numCalls == 0 && lowAttribute != -1)
		{
			usedAttribute[lowAttribute] = true;
			lowest = secondLow;
			lowAttribute = secLowAttribute;
		}
		// if there is no such attribute, simply return a node that classifies
		// based on the consensus of the list
		if (lowAttribute == -1)
			return new Node(list.consensus());

		// an attribute has been selected for the root of the decision tree
		Node newNode = new Node(values[lowAttribute].length, lowAttribute);

		// recurse on each child with the appropriate sublist
		usedAttribute[lowAttribute] = true;
		for (int i = 0; i < values[lowAttribute].length; i++) {
			MushList sublist = list.getSublist(lowAttribute, values[lowAttribute][i]);
			newNode.children[i] = recBuild(sublist, usedAttribute, numCalls++);
		}

		return newNode;
	}*/

	// non-recursive method called to initiate construction of a decision tree
	// initialize all attributes as unused
	public void build(MushList list) {
		boolean[] usedAttribute = new boolean[numAttributes];
		for (int i = 0; i < numAttributes; i++)
			usedAttribute[i] = false;
		root = recBuild(list, usedAttribute);
		//for question 12
		//root = recBuild(list, usedAttribute, 0);
	}

	public void display(String[] labels) {
		root.display(root, 0, labels, values);
	}

	// predicts if a new mushroom is poisonous or edible based on a given tree
	public String predict(Mushroom test, Node r) {
		Node current = r;
		String classify = "";
		// base case
		if (current.children == null) {
			return current.classification;
		} else {
			for (int i = 0; i < current.children.length; i++) {
				// recursive case
				if (test.values[current.index].equalsIgnoreCase(values[current.index][i])) {
					classify = predict(test, current.children[i]);
					return classify;
				}
			}
			return classify;
		}
	}

	// predicts if a mushroom is poisonous or edible based on a given tree, and
	// gives reasoning
	public String verbosePredict(Mushroom test, Node r, String[] labels) {
		Node current = r;
		String classify = "";
		// base case
		if (current.children == null) {
			return current.classification;
		} else {
			for (int i = 0; i < current.children.length; i++) {
				// recursive case
				if (test.values[current.index].equalsIgnoreCase(values[current.index][i])) {
					System.out.println(labels[current.index] + " is " + test.values[current.index].toLowerCase());
					classify = verbosePredict(test, current.children[i], labels);
					return classify;
				}
			}
			return "Something went wrong. Send help.";
		}
	}
}

// Link within a linked list of Mushrooms used as data to process
class Link {
	public Mushroom data;
	public Link next;

	public Link(Mushroom d) {
		data = d;
		next = null;
	}
}

// singly linked list of Mushroom objects, with support methods provided for
// decision tree construction
class MushList {
	private Link first;
	private int numItems;

	public MushList() {
		first = null;
		numItems = 0;
	}

	public int getNum() {
		return numItems;
	}

	// all new Mushrooms inserted at front of list
	public void insert(Mushroom d) {
		Link newLink = new Link(d);
		newLink.next = first;
		first = newLink;
		numItems++;
	}

	// return true if all Mushrooms within this list are classified as poisonous
	public boolean allPoison() {
		boolean all = true;
		Link curr = first;
		while (curr != null) {
			all = all && curr.data.classification.equalsIgnoreCase("poisonous");
			curr = curr.next;
		}
		return all;
	}

	// return true if all Mushrooms within this list are classified as edible
	public boolean allEdible() {
		boolean all = true;
		Link curr = first;
		while (curr != null) {
			all = all && curr.data.classification.equalsIgnoreCase("edible");
			curr = curr.next;
		}
		return all;
	}

	// return "poisonous" or "edible" indicating which classification the
	// majority
	// of the Mushrooms in this last have
	public String consensus() {
		int poison = 0;
		int edible = 0;
		Link curr = first;
		while (curr != null) {
			if (curr.data.classification.equalsIgnoreCase("edible"))
				edible++;
			else
				poison++;
			curr = curr.next;
		}
		if (poison >= edible)
			return "poisonous";
		else
			return "edible";
	}

	// given an attribute, a particular value for that attribute, and either
	// "poisonous" or "edible", return the number of items in this list that
	// have both the given value and the designated classification
	public int getClassCount(int attribute, String value, String clss) {
		int count = 0;
		Link curr = first;
		while (curr != null) {
			if (curr.data.values[attribute].equalsIgnoreCase(value) && curr.data.classification.equalsIgnoreCase(clss))
				count++;
			curr = curr.next;
		}
		return count;
	}

	// given an attribute and a particular value for that attribute, return the
	// number of items in this list that have the given value for that
	// attribute
	public int getValueCount(int attribute, String value) {
		int count = 0;
		Link curr = first;
		while (curr != null) {
			if (curr.data.values[attribute].equalsIgnoreCase(value))
				count++;
			curr = curr.next;
		}
		return count;
	}

	// given an attribute and a particular value for that attribute, return the
	// sublist of this list which has only the Mushroom objects in it with
	// the given value for the attribute
	public MushList getSublist(int attribute, String value) {
		MushList newList = new MushList();
		Link curr = first;
		while (curr != null) {
			if (curr.data.values[attribute].equalsIgnoreCase(value)) {
				newList.insert(curr.data);
			}
			curr = curr.next;
		}
		return newList;
	}

	// getter method for predictions
	public Link getFirst() {
		return first;
	}
}

public class MushroomDT {
	public static void main(String[] args) throws IOException {
		int[] numCorrect = new int[30]; // stores the number of correct
										// predictions
		int[] numTesting = new int[30]; // stores the number of items in the
										// testing list
		int numAttributes = 22; // number of attributes
		String[] labels; // label associated with each attribute
		String[][] values = { { "bell", "conical", "convex", "flat", "knobbed", "sunken" },
				{ "fibrous", "grooves", "scaly", "smooth" },
				{ "brown", "buff", "cinnamon", "gray", "green", "pink", "purple", "red", "white", "yellow" },
				{ "bruises", "no" },
				{ "almond", "anise", "creosote", "fishy", "foul", "musty", "none", "pungent", "spicy" },
				{ "attached", "descending", "free", "notched" }, { "close", "crowded", "distant" },
				{ "broad", "narrow" },
				{ "black", "brown", "buff", "chocolate", "gray", "green", "orange", "pink", "purple", "red", "white",
						"yellow" },
				{ "enlarging", "tapering" }, { "bulbous", "club", "cup", "equal", "rhizomorphs", "rooted", "?" },
				{ "fibrous", "scaly", "silky", "smooth" }, { "fibrous", "scaly", "silky", "smooth" },
				{ "brown", "buff", "cinnamon", "gray", "orange", "pink", "red", "white", "yellow" },
				{ "brown", "buff", "cinnamon", "gray", "orange", "pink", "red", "white", "yellow" },
				{ "partial", "universal" }, { "brown", "orange", "white", "yellow" }, { "none", "one", "two" },
				{ "cobwebby", "evanescent", "flaring", "large", "none", "pendant", "sheathing", "zone" },
				{ "black", "brown", "buff", "chocolate", "green", "orange", "purple", "white", "yellow" },
				{ "abundant", "clustered", "numerous", "scattered", "several", "solitary" },
				{ "grasses", "leaves", "meadows", "paths", "urban", "waste", "woods" } };
		for (int k = 0; k < numCorrect.length; k++) // testing loop
		{
			Scanner fileScan = new Scanner(new File("mushrooms.csv"));
			// file Scanner for question 2
			// Scanner fileScan = new Scanner(new File("mushroomsSmall.csv"));
			String header = fileScan.nextLine();
			// read in the label associated with each attribute from the first
			// line
			// of the csv file
			Scanner labelScan = new Scanner(header);
			labelScan.useDelimiter(",");
			labelScan.next();
			labels = new String[numAttributes];
			for (int i = 0; i < numAttributes; i++) {
				labels[i] = labelScan.next();
			}

			// a linked list for storing the data over which a decision tree
			// will be built
			MushList training = new MushList();

			// a linked list for storing data over which the predictions of the
			// decision tree will
			// be tested
			MushList testing = new MushList();
			Random gen = new Random();
			// read in each line of the csv file, create a Mushroom object
			// storing
			// the data from that line, and then add the new Mushroom to the
			// training data list

			while (fileScan.hasNext()) {
				String rowData = fileScan.nextLine();
				Scanner mushScan = new Scanner(rowData);
				mushScan.useDelimiter(",");

				String classification;
				String[] data = new String[numAttributes];

				classification = mushScan.next();
				for (int i = 0; i < numAttributes; i++) {
					data[i] = mushScan.next();
				}
				Mushroom newMush = new Mushroom(classification, data);
				if (gen.nextInt(99) < 98) {
					training.insert(newMush);
				} else {
					testing.insert(newMush);
				}
				mushScan.close();
			}
			fileScan.close();
			labelScan.close();

			System.out.println("Data read in...");

			// build a decision tree that classifies Mushroom objects as
			// "poisonous" or "edible" given the data in the training list
			DecisionTree tree = new DecisionTree(values);
			tree.build(training);

			System.out.println("Tree built...");
			tree.display(labels);
			// hard coded test data for question 8
			/*
			 * String testClassification = "edible"; String[] testData1 =
			 * {"CONVEX", "SMOOTH", "YELLOW", "BRUISES", "ANISE", "FREE",
			 * "CROWDED", "NARROW", "WHITE", "TAPERING", "BULBOUS", "SMOOTH",
			 * "SMOOTH", "WHITE", "WHITE", "PARTIAL", "WHITE", "ONE", "PENDANT",
			 * "BROWN", "SEVERAL", "WOODS"}; Mushroom test = new
			 * Mushroom(testClassification, testData1);
			 * System.out.println("Known classification: " + testClassification
			 * + " " + "Tree " + "prediction: " + tree.predict(test,
			 * tree.root)); testClassification = "poisonous"; String[] testData2
			 * = {"CONVEX", "SCALY", "GRAY", "NO", "FOUL", "FREE", "CLOSE",
			 * "BROAD", "CHOCOLATE", "ENLARGING", "BULBOUS", "SILKY", "SILKY",
			 * "BROWN", "PINK", "PARTIAL", "WHITE", "ONE", "LARGE", "CHOCOLATE",
			 * "SEVERAL", "WOODS"}; test = new Mushroom(testClassification,
			 * testData2); System.out.println("Known classification: " +
			 * testClassification + " " + "Tree " + "prediction: " +
			 * tree.predict(test, tree.root)); testClassification = "poisonous";
			 * String[] testData3 = {"FLAT", "SCALY", "YELLOW", "NO", "FOUL",
			 * "FREE", "CLOSE", "BROAD", "PINK", "ENLARGING", "BULBOUS",
			 * "SILKY", "SILKY", "PINK", "BROWN", "PARTIAL", "WHITE", "ONE",
			 * "LARGE", "CHOCOLATE", "SEVERAL", "WOODS"}; test = new
			 * Mushroom(testClassification, testData3);
			 * System.out.println("Known classification: " + testClassification
			 * + " " + "Tree " + "prediction: " + tree.predict(test,
			 * tree.root));
			 */

			// hard coded example for question 9
			String testClassification = "poisonous";
			String[] testData = {"FLAT", "SCALY", "YELLOW", "NO", "FOUL", "FREE", "CLOSE", 
								 "BROAD","PINK", "ENLARGING", "BULBOUS", "SILKY", "SILKY", 
								 "PINK", "BROWN", "PARTIAL", "WHITE", "ONE", "LARGE", "CHOCOLATE",
								 "SEVERAL", "WOODS"};
			Mushroom test = new Mushroom(testClassification, testData);
			System.out.println(tree.verbosePredict(test, tree.root, labels));

			// testing for question 11 and 12
			Link current = testing.getFirst();
			numTesting[k] = testing.getNum();
			String prediction = "";
			while (current != null) {
				prediction = tree.predict(current.data, tree.root);
				if (current.data.classification.equalsIgnoreCase(prediction)) {
					numCorrect[k]++;
				}
				current = current.next;
			}
		}
		double percentError = 0.0;
		for (int i = 0; i < numCorrect.length; i++) {
			percentError += (((double) numCorrect[i]) / (numTesting[i])) * 100;
			System.out.println("Percentage Correct for test " + (i + 1) + ": "
					+ (((double) numCorrect[i]) / (numTesting[i])) * 100 + "%");
		}
		System.out.println("Average percent error: " + percentError / numCorrect.length + "%");
	}
}