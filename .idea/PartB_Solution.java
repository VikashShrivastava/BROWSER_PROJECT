import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     *
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     *
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     *
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     *
     * @param updatedNumVisits
     *           an int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }

}

public class PartBSolution {

    private static Queue<SiteStats> sites = new LinkedList<SiteStats>();


    // Main method to list top n visited sites
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {
        // WRITE CODE HERE
        sortQueue(sites);//sorts Queue info based on maxVisits
        recentlyVisited(sites,n);//Top n sites according to number of times visited and recency
    }


    private static void recentlyVisited(Queue<SiteStats> sites,int n) {
        for(int i=0;i< sites.size();i++){
            if(i<n){
                System.out.println(sites.peek());
                sites.add(sites.remove());
            }
            else {
                sites.add(sites.remove());
            }
        }
    }


    public static void sortQueue(Queue<SiteStats> sites) {//Sorts the website info in order of maxVisits
        if(sites.isEmpty()){
            System.out.println(sites);//Prints empty queue if no browsing history found
        }
        for (int i = 1; i <= sites.size(); i++) {
            int max_index = maxIndex(sites, sites.size() - i);//max_index returned by maxIndex method
            insertMaxToFront(sites, max_index);//Inserts website with maxVisits to the Front based on max_index value
        }
    }


    public static int maxIndex(Queue<SiteStats> sites, int sort_index) {//returns the index in the queue for the website with maxVisits
        int max_index = -1;
        int max_value = 0;
        int sites_size = sites.size();
        SiteStats current_element;

        for (int i = 0; i < sites_size; i++) {
            current_element = sites.peek();
            sites.remove();
            if (current_element.getNumVisits() >= max_value && i <= sort_index) {
                max_index = i;
                max_value = current_element.getNumVisits();
            }
            sites.add(current_element);
        }

        return max_index;
    }


    public static void insertMaxToFront(Queue<SiteStats> sites, int max_index) {//inserts the website with maxVisits to the Front of the Queue
        int numVisitsTotal = 0;
        for (int i = 0; i < sites.size(); i++) {
            numVisitsTotal = numVisitsTotal + sites.peek().getNumVisits();
        }

        int sites_size = sites.size();
        SiteStats current_element, max_element = null;
        for (int i = 0; i < sites_size; i++) {
            current_element = sites.peek();
            sites.remove();
            if (i != max_index) {
                sites.add(current_element);
            } else {
                max_element = current_element;
            }
        }
        sites.add(max_element);
    }


    // Method to find the website in the queue and increment the visited count by 1, adding new node in case website is not found
    public static void updateCount(String url) {
        // WRITE CODE HERE
        try {
            int sites_size = sites.size();
            while (url != sites.peek().getUrl() && sites_size >= 0) {//Traverses through the queue till url is found
                sites.add(sites.remove());
                sites_size--;
            }
            if (url != sites.peek().getUrl()) { //If url is found,website info added for the first time in the non empty queue
                sites.add(new SiteStats(url, 1));
            } else {
                sites.peek().setNumVisits(sites.peek().getNumVisits() + 1);//If url is found for the next time,NumVisits incremented by 1
            }
        } catch (Exception e) {
            sites.add(new SiteStats(url, 1));//Adds a website in case user tries to add website for the first time in  a empty queue
        }


    }

    public static void main(String[] args) {
        String[] visitedSites = { "www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com", "www.google.co.in", "www.youtube.com",
                "www.facebook.com", "www.upgrad.com", "www.facebook.com", "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com",
                "www.netflix.com", "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com", "www.amazon.com",
                "www.microsoft.com", "www.upgrad.com" };

        for (String url : visitedSites) {
            updateCount(url);
        }
        listTopVisitedSites(sites, 5);

    }

}