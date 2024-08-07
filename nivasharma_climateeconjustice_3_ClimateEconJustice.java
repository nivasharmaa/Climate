package climate;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered 
 * linked list structure that contains USA communitie's Climate and Economic information.
 * 
 * @author Navya Sharma
 */

public class ClimateEconJustice {

    private StateNode firstState;
    
    /*
    * Constructor
    * 
    * **** DO NOT EDIT *****
    */
    public ClimateEconJustice() {
        firstState = null;
    }

    /*
    * Get method to retrieve instance variable firstState
    * 
    * @return firstState
    * 
    * **** DO NOT EDIT *****
    */ 
    public StateNode getFirstState () {
        // DO NOT EDIT THIS CODE
        return firstState;
    }

    /**
     * Creates 3-layered linked structure consisting of state, county, 
     * and community objects by reading in CSV file provided.
     * 
     * @param inputFile, the file read from the Driver to be used for
     * @return void
     * 
     * **** DO NOT EDIT *****
     */
    public void createLinkedStructure ( String inputFile ) {
        
        // DO NOT EDIT THIS CODE
        StdIn.setFile(inputFile);
        StdIn.readLine();
        
        // Reads the file one line at a time
        while ( StdIn.hasNextLine() ) {
            // Reads a single line from input file
            String line = StdIn.readLine();
            // IMPLEMENT these methods
            addToStateLevel(line);
            addToCountyLevel(line);
            addToCommunityLevel(line);
        }
    }

    /*
    * Adds a state to the first level of the linked structure.
    * Do nothing if the state is already present in the structure.
    * 
    * @param inputLine a line from the input file
    */
    public void addToStateLevel ( String inputLine ) {

        // WRITE YOUR CODE HERE
            String[] data = inputLine.split(",");
            String stateName = data[2]; 
            StateNode currentState = firstState;
            
            while (currentState != null) {
                if (currentState.getName().equals(stateName)) {
                    return;
                }


                currentState = currentState.getNext();
            }
            StateNode newState = new StateNode(stateName, null, null);
            

                if (firstState == null) {
                    firstState = newState;
                } else {
                    currentState = firstState;
                    while (currentState.getNext() != null) {
                        currentState = currentState.getNext();
                    }
                    currentState.setNext(newState);
                }
    }

    

    /*
    * Adds a county to a state's list of counties.
    * 
    * Access the state's list of counties' using the down pointer from the State class.
    * Do nothing if the county is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCountyLevel ( String inputLine ) {

        // WRITE YOUR CODE HERE
        String[] data = inputLine.split(",");
        String stateName = data[2];
        String countyName = data[1];

       
       StateNode currentState = firstState;
       while (currentState != null) {
        if (currentState.getName().equals(stateName)) {
            break;
           }

           currentState = currentState.getNext();
       }

      
       CountyNode currentCounty = currentState.getDown();
      
       boolean countyExists = false;
      
        while (currentCounty != null) {
           if (currentCounty.getName().equals(countyName)) {
               countyExists = true;
               break;
           }
           currentCounty = currentCounty.getNext();
       }

      
       if (!countyExists) {
           CountyNode newCounty = new CountyNode(countyName, null, null);


           if (currentState.getDown() == null) {
               currentState.setDown(newCounty);
           } else {
               currentCounty = currentState.getDown();
               while (currentCounty.getNext() != null) {
                   currentCounty = currentCounty.getNext();
               }
               currentCounty.setNext(newCounty);
            }
        }

    }

    /*
    * Adds a community to a county's list of communities.
    * 
    * Access the county through its state
    *      - search for the state first, 
    *      - then search for the county.
    * Use the state name and the county name from the inputLine to search.
    * 
    * Access the state's list of counties using the down pointer from the StateNode class.
    * Access the county's list of communities using the down pointer from the CountyNode class.
    * Do nothing if the community is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCommunityLevel ( String inputLine ) {

        // WRITE YOUR CODE HERE
        
        String[] split = inputLine.split(",");
        String comm = split[0];
        String state = split[2];
        String county = split[1];


                Double percentAfricanAmerican = Double.parseDouble(split[3]);
                Double percentNative = Double.parseDouble(split[4]);
                Double percentAsian = Double.parseDouble(split[5]);
                Double percentWhite = Double.parseDouble(split[8]);
                Double percentHispanic = Double.parseDouble(split[9]);
                String disadvantaged1 = split[19];
                Double PMlevel = Double.parseDouble(split[49]);
                Double chanceofFlood = Double.parseDouble(split[37]);
                Double povertyLine = Double.parseDouble(split[121]);


                    boolean state1 = false;
                    boolean county1 = false;
                    boolean com1 = false;

                            StateNode currentStateNode = firstState;
                            CountyNode currentCountyNode = null; // Initialize to null to avoid potential NullPointerException
                            CommunityNode curCommunityNode = null; // Initialize to null to avoid potential NullPointerException
                            Data newComData = new Data();
                                newComData.setPrcntAfricanAmerican(percentAfricanAmerican);
                                newComData.setPrcntAsian(percentAsian);
                                newComData.setPrcntHispanic(percentHispanic);
                                newComData.setPrcntNative(percentNative);
                                newComData.setPrcntWhite(percentWhite);
                                newComData.setAdvantageStatus(disadvantaged1);
                                newComData.setPercentPovertyLine(povertyLine);
                                newComData.setChanceOfFlood(chanceofFlood);
                                newComData.setPMlevel(PMlevel);

    
        // Search for the state
        while (currentStateNode != null) {
            if (currentStateNode.getName().equals(state)) {
                state1 = true;
                currentCountyNode = currentStateNode.getDown(); // Move to the counties of this state
                break;
            }
            currentStateNode = currentStateNode.getNext();
        }
    
        // Check if the state exists
        if (state1==false) {
            
            return;
        }
    
       
        while (currentCountyNode != null) {
            if (currentCountyNode.getName().equals(county)) {
                county1 = true;
                curCommunityNode = currentCountyNode.getDown(); // Move to the communities of this county
                break;
            }
            currentCountyNode = currentCountyNode.getNext();
        }
    
        
        if (county1==false) {
           
            return;
        }
    
        
        while (curCommunityNode != null) {
            if (curCommunityNode.getName().equals(comm)) {
                com1 = true;
                break;
            }
            curCommunityNode = curCommunityNode.getNext();
        }
    
        if (com1==false) {
            
            CommunityNode newCommunityNode = new CommunityNode(comm,null,null);
            newCommunityNode.setName(comm);
            newCommunityNode.setInfo(newComData);
            

            // Find the last community node and add the new node after it
            CommunityNode lastCommunityNode = currentCountyNode.getDown();
            if (lastCommunityNode == null) {
                currentCountyNode.setDown(newCommunityNode); // No communities exist yet, so set this as the first one
            } else {
                
                while (lastCommunityNode.getNext() != null) {
                    lastCommunityNode = lastCommunityNode.getNext();
                }
                lastCommunityNode.setNext(newCommunityNode); // Link the new node to the end of the list
            }
        } else {
           
            curCommunityNode.setInfo(newComData);

        }

        }

    

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int disadvantagedCommunities ( double userPrcntage, String race ) {

        // WRITE YOUR CODE HERE
        int total = 0;
        StateNode firstState1 = firstState;
        if(firstState1==null){
            return -1;
        }
        String B = "African American";
        String N = "Native American";
        String A = "Asian American";
        String W = "White American";
        String H = "Hispanic American";
        String T = "True";
        double p = 0.0;
        double newUser = userPrcntage;

        while(firstState1!=null){ 
            CountyNode firstCounty = firstState1.getDown();
            while(firstCounty!=null){
                CommunityNode firstCommunity1 = firstCounty.getDown();
                while(firstCommunity1!=null){
                    Data comD = firstCommunity1.getInfo();
                    String dis = firstCommunity1.getInfo().getAdvantageStatus();
                    if(dis.equals(T)){
                        
                        if(race.equalsIgnoreCase(B)){
                            p = comD.getPrcntAfricanAmerican()*100;
                            if(p>=newUser){
                                total++;
                            }
                        }
                        if(race.equalsIgnoreCase(N)){
                            p = comD.getPrcntNative()*100;
                            if(p>=newUser){
                                total++;
                            }
                        }
                        if(race.equalsIgnoreCase(A)){
                            p = comD.getPrcntAsian()*100;
                            if(p>=newUser){
                                total++;
                            }
                        }
                        if(race.equalsIgnoreCase(W)){
                            p = comD.getPrcntWhite()*100;
                            if(p>=newUser){
                                total++;
                            }
                        }
                        if(race.equalsIgnoreCase(H)){
                            p = comD.getPrcntHispanic()*100;
                            if(p>=newUser){
                                total++;
                            }
                        }
                        
                    }
                    firstCommunity1=firstCommunity1.getNext();
                }
                firstCounty=firstCounty.getNext();
            }
            firstState1=firstState1.getNext();
 
        }
        return total;
    }
        
    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as non disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int nonDisadvantagedCommunities ( double userPrcntage, String race ) {

        //WRITE YOUR CODE HERE
        int total = 0;
        StateNode currentState = firstState;
    
        while (currentState != null) {
            CountyNode currentCounty = currentState.getDown();
    
            while (currentCounty != null) {
                CommunityNode currentCommunity = currentCounty.getDown();
    
                while (currentCommunity != null) {
                    Data communityData = currentCommunity.getInfo();
                    String disadvantagedStatus = communityData.getAdvantageStatus();
    
                     if (disadvantagedStatus.equals("False")) {
                        double racialPercentage = 0.0;
    
                       
                        
                        switch (race.toLowerCase()) {
                            case "african american":
                                racialPercentage = communityData.getPrcntAfricanAmerican() * 100;
                                break;
                            case "native american":
                                racialPercentage = communityData.getPrcntNative() * 100;
                                break;
                            case "asian american":
                                racialPercentage = communityData.getPrcntAsian() * 100;
                                break;
                            case "white american":
                                racialPercentage = communityData.getPrcntWhite() * 100;
                                break;
                            case "hispanic american":
                                racialPercentage = communityData.getPrcntHispanic() * 100;
                                break;
                        }
    
                        if (racialPercentage >= userPrcntage) {
                            total++;
                        }
                    }
    
                    currentCommunity = currentCommunity.getNext();
                }
    
                currentCounty = currentCounty.getNext();
            }
    
            currentState = currentState.getNext();
        }
    
      
            
            return total;
    }
    

    
    /** 
     * Returns a list of states that have a PM (particulate matter) level
     * equal to or higher than value inputted by user.
     * 
     * @param PMlevel the level of particulate matter
     * @return the States which have or exceed that level
     */ 
    public ArrayList<StateNode> statesPMLevels ( double PMlevel ) {

        // WRITE YOUR METHOD HERE
        
            ArrayList<StateNode> statesWithHighPMLevels = new ArrayList<>();
    
               
                if (firstState != null) {
                    StateNode currentState = firstState;
            
                    // Traverse through the linked list structure
                    while (currentState != null) {
                        CountyNode currentCounty = currentState.getDown();
            
                        while (currentCounty != null) {
                            CommunityNode currentCommunity = currentCounty.getDown();
            
                            while (currentCommunity != null) {

                                // Check if the PM level of the current community exceeds or equals the provided threshold
                                if (currentCommunity.getInfo().getPMlevel() >= PMlevel) {
                                    // Add the state to the list if it's not already included

                                    if (!statesWithHighPMLevels.contains(currentState)) {
                                        statesWithHighPMLevels.add(currentState);
                                        
                                    }
                                    
                                    break;
                                }
            
                                currentCommunity = currentCommunity.getNext();
                            }
            
                            currentCounty = currentCounty.getNext();
                        }
            
                        currentState = currentState.getNext();
                    }
                }
            
                    return statesWithHighPMLevels;
            
                
                }

    /**
     * Given a percentage inputted by user, returns the number of communities 
     * that have a chance equal to or higher than said percentage of
     * experiencing a flood in the next 30 years.
     * 
     * @param userPercntage the percentage of interest/comparison
     * @return the amount of communities at risk of flooding
     */
    public int chanceOfFlood ( double userPercntage ) {
       //WRITE YOUR CODE HERE
        int communitiesAtRisk = 0;

        // Ensure that the linked list structure is not empty
        if (firstState != null) {
            StateNode currentState = firstState;

            // Traverse through the linked list structure
            while (currentState != null) {
                CountyNode currentCounty = currentState.getDown();

                while (currentCounty != null) {
                    CommunityNode currentCommunity = currentCounty.getDown();

                    while (currentCommunity != null) {
                        // Check if the chance of flood of the current community exceeds or equals the provided percentage
                        if (currentCommunity.getInfo().getChanceOfFlood() >= userPercntage) {
                            communitiesAtRisk++;
                        }

                        currentCommunity = currentCommunity.getNext();
                    }

                    currentCounty = currentCounty.getNext();
                }

                currentState = currentState.getNext();
            }
        }

        return communitiesAtRisk;


        
    }

    /** 
     * Given a state inputted by user, returns the communities with 
     * the 10 lowest incomes within said state.
     * 
     *  @param stateName the State to be analyzed
     *  @return the top 10 lowest income communities in the State, with no particular order
    */
    public ArrayList<CommunityNode> lowestIncomeCommunities ( String stateName ) {
        ArrayList<CommunityNode> lowestIncomeCommunitiesList = new ArrayList<>();

        // Traverse the linked list structure to find the specified state
        StateNode statePointer = firstState;
        while (statePointer != null && !statePointer.getName().equals(stateName)) {
            statePointer = statePointer.getNext();
        }
    
        // Check if the state exists in the linked list structure
        if (statePointer != null) {
            // Traverse the linked list connected to the specified state
            CountyNode countyPointer = statePointer.getDown();
            while (countyPointer != null) {
                CommunityNode communityPointer = countyPointer.getDown();
                while (communityPointer != null) {
                    // Add the first 10 communities arbitrarily
                    if (lowestIncomeCommunitiesList.size() < 10) {
                        lowestIncomeCommunitiesList.add(communityPointer);
                    } else {
                        // Find the community with the lowest poverty line among the 10 lowest-income communities
                        double lowestPovertyLine = Double.MAX_VALUE;
                        int lowestIndex = -1;
                        for (int i = 0; i < lowestIncomeCommunitiesList.size(); i++) {
                            double povertyLine = lowestIncomeCommunitiesList.get(i).getInfo().getPercentPovertyLine();
                            if (povertyLine < lowestPovertyLine) {
                                lowestPovertyLine = povertyLine;
                                lowestIndex = i;
                            }
                        }
                        // Replace the community with the lowest poverty line if the current community has a lower poverty line
                        if (communityPointer.getInfo().getPercentPovertyLine() > lowestPovertyLine && lowestIndex != -1) {
                            lowestIncomeCommunitiesList.set(lowestIndex, communityPointer);
                        }
                    }
                    communityPointer = communityPointer.getNext();
                }
                countyPointer = countyPointer.getNext();
            }
        }
    
        return lowestIncomeCommunitiesList;

    }
}
    
