import java.util.Stack;
import java.util.Scanner;

public class PartA_Solution {

    private static Stack<String> history = new Stack<String>();

    public static Boolean isBrowsingHistoryEmpty() {
        // Add CODE BELOW
        if(history.isEmpty()){   //checks if the stack is empty or not
            return true;
        }else {
            return false;
        }
    }

    public static String mostRecentlyVisitedSite() {
        // Add CODE BELOW
        String mostRecent = history.peek();    //collects recently visited url
        return mostRecent;
    }

    public static void addSiteToHistory(String url) {
        // Add CODE BELOW
        history.push(url); //add url to browsing history
    }

    public static void goBackInTime(int n) {
        // Add CODE BELOW
        if(history.isEmpty()){
            //Do nothing
        }else {
            for(int i=0;i<n;i++){
                history.pop();    //removes the n most recent websites from the history
            }
        }

    }

    public static void printBrowsingHistory() {
        // Add CODE BELOW
        if(history.isEmpty()){
            System.out.println("Browsing history is empty");
        }else{
            System.out.println(history);   //print out all the websites present in history
        }
    }

    public static void main(String[] args) {
        System.out.println(isBrowsingHistoryEmpty()); // Checking if Browsing History is Empty
        addSiteToHistory("www.google.co.in"); // Navigating to Google
        addSiteToHistory("www.facebook.com"); // Navigating to Facebook
        addSiteToHistory("www.upgrad.com"); // Navigating to UpGrad
        System.out.println(isBrowsingHistoryEmpty()); // Checking if Browsing History is Empty
        System.out.println(mostRecentlyVisitedSite()); // Fetching most recently visited site (UpGrad)
        addSiteToHistory("www.youtube.com"); // Navigating to Youtube
        goBackInTime(2); // Going back by 2 sites
        addSiteToHistory("www.yahoo.com"); // Navigating to UpGrad platform site
        System.out.println(mostRecentlyVisitedSite()); // Fetching most recently visited site (UpGrad Learn Platform)
        printBrowsingHistory(); // Printing browsing history

    }

}

/**
 * Your code should have the following Output:
 * <p>
 * true
 * false
 * www.upgrad.com
 * www.yahoo.com
 * [www.google.co.in, www.facebook.com, www.yahoo.com]
 */
