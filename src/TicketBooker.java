import java.util.*;

public class TicketBooker {

    static int availableLowerBerths = 1;
    static int availableMiddleBerths = 1;
    static int availableUpperBerths = 1;
    static int availableRacTickets = 1;
    static int availableWaitingList = 1;

    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();
    static List<Integer> bookedticketList = new ArrayList<>();


    static List<Integer> lowerBerthsPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> middleBerthsPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> upperBerthsPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> racPosition = new ArrayList<>(Arrays.asList(1));
    static List<Integer> waitingListPosition = new ArrayList<>(Arrays.asList(1));

    static Map<Integer, Passenger> passengers = new HashMap<>();

    public void bookTicket(Passenger p, int berthInfo, String allotedBerth){
        p.number = berthInfo;
        p.alloted = allotedBerth;

        passengers.put(p.passengerId, p);
        bookedticketList.add(p.passengerId);

        System.out.println("----------------------Booked Successfully");
    }

    public void addToRac(Passenger p, int racInfo, String allotedRac) {
        p.number = racInfo;
        p.alloted = allotedRac;

        passengers.put(p.passengerId,p);
        racList.add(p.passengerId);
        availableRacTickets--;
        racPosition.remove(0);

        System.out.println("--------------------------added to RAC Successfully");
    }

    public void addToWaitingList(Passenger p, int waitingListInfo, String allotedWL) {
        p.number = waitingListInfo;
        p.alloted = allotedWL;

        passengers.put(p.passengerId,p);
        waitingList.add(p.passengerId);
        availableWaitingList--;
        waitingListPosition.remove(0);

        System.out.println("-------------------------- added to Waiting List Successfully");
    }


    public void cancelTicket(int passengerId) {
        Passenger p = passengers.get(passengerId);
        passengers.remove(Integer.valueOf(passengerId));

        bookedticketList.remove(Integer.valueOf(passengerId));

        int positionBooked = p.number;

        System.out.println("--------------------------Cancelled Successfully");

        if(p.alloted.equals("L")){
            availableLowerBerths++;
            lowerBerthsPosition.add(positionBooked);

        } else if(p.alloted.equals("M")){
            availableMiddleBerths++;
            middleBerthsPosition.add(positionBooked);

        }else if(p.alloted.equals("U")) {
            availableUpperBerths++;
            upperBerthsPosition.add(positionBooked);
        }

        //Checking if any RAC reservation is available

        if(racList.size() > 0){

            Passenger passengerFromRAC = passengers.get(racList.poll());
            int positionRac = passengerFromRAC.number;
            racPosition.add(positionRac);
            racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
            availableRacTickets++;

            //Checking if any Waiting List reservation is available

            if(waitingList.size() > 0) {
                Passenger passengerFromWaitingList = passengers.get(waitingList.poll());

                int positionWL = passengerFromWaitingList.number;
                waitingListPosition.add(positionWL);
                waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

                passengerFromWaitingList.number = racPosition.get(0);
                passengerFromWaitingList.alloted = "RAC";
                racPosition.remove(0);
                racList.add(passengerFromWaitingList.passengerId);

                availableWaitingList++;
                availableRacTickets--;
            }
            Main.bookTicket(passengerFromRAC);
        }
    }
    // print all available seats
    public void printAvailable() {
        System.out.println("Available Lower Berths " + availableLowerBerths);
        System.out.println("Available Middle Berths " + availableMiddleBerths);
        System.out.println("Available Upper Berths " + availableUpperBerths);
        System.out.println("Available RACs " + availableRacTickets);
        System.out.println("Available Waiting List " + availableWaitingList);
        System.out.println("---------------------------");
    }

    public void printPassengers() {
        if(passengers.size() == 0){
            System.out.println("No details of Passengers Available");
            return;
        }
        for(Passenger p : passengers.values()){
            System.out.println("Passenger ID: " + p.passengerId);
            System.out.println("Name: " + p.name);
            System.out.println("Age: " + p.age);
            System.out.println("Status: " + p.number + p.alloted);
            System.out.println("--------------------------");
        }
    }
}
