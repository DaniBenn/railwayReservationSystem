import java.util.Scanner;

public class Main {
    public static void bookTicket(Passenger p) {
        TicketBooker booker = new TicketBooker();
        if(TicketBooker.availableWaitingList == 0){
            System.out.println("No Ticket Available");
            return;
        }

        if((p.berthPreference.equals("L") && TicketBooker.availableLowerBerths > 0 )||
                (p.berthPreference.equals("M") && TicketBooker.availableMiddleBerths > 0) ||
                (p.berthPreference.equals("U") && TicketBooker.availableUpperBerths > 0)){

            // because the preferred berth is available it books the preferred berth.

            System.out.println("Preferred Berth Available");
            if(p.berthPreference.equals("L")){
                System.out.println("Lower Berth Given");

                booker.bookTicket(p, (TicketBooker.lowerBerthsPosition.get(0)), "L");

                TicketBooker.lowerBerthsPosition.remove(0);
                TicketBooker.availableLowerBerths--;
            }
            else if(p.berthPreference.equals("M"))
            {
                System.out.println("Middle Berth Given");

                booker.bookTicket(p,(TicketBooker.middleBerthsPosition.get(0)),"M");

                TicketBooker.middleBerthsPosition.remove(0);
                TicketBooker.availableMiddleBerths--;

            }
            else if(p.berthPreference.equals("U"))
            {
                System.out.println("Upper Berth Given");

                booker.bookTicket(p,(TicketBooker.upperBerthsPosition.get(0)),"U");

                TicketBooker.upperBerthsPosition.remove(0);
                TicketBooker.availableUpperBerths--;
            }
        }

        // if preferred berth not available then this else if books the available berth

        else if(TicketBooker.availableLowerBerths > 0){
            System.out.println("Lower Berth Given");

            booker.bookTicket(p, (TicketBooker.lowerBerthsPosition.get(0)), "L");

            TicketBooker.lowerBerthsPosition.remove(0);
            TicketBooker.availableLowerBerths--;

        }

        else if(TicketBooker.availableMiddleBerths > 0)
        {
            System.out.println("Middle Berth Given");

            booker.bookTicket(p,(TicketBooker.middleBerthsPosition.get(0)),"M");

            TicketBooker.middleBerthsPosition.remove(0);
            TicketBooker.availableMiddleBerths--;

        }

        else if(TicketBooker.availableUpperBerths > 0)
        {
            System.out.println("Upper Berth Given");

            booker.bookTicket(p,(TicketBooker.upperBerthsPosition.get(0)),"U");

            TicketBooker.upperBerthsPosition.remove(0);
            TicketBooker.availableUpperBerths--;

        }

        //if no berth available move to RAC
        else if((TicketBooker.availableRacTickets > 0)){
            System.out.println("RAC Available");
            booker.addToRac(p, (TicketBooker.racPosition.get(0)) , "RAC");
        }

        //if no RAC is available move to waiting list
        else if (TicketBooker.availableWaitingList > 0){
            System.out.println("Added to Waiting List");
            booker.addToWaitingList(p, (TicketBooker.waitingListPosition.get(0)), "WL");
        }
    }

    public static void cancelTicket(int id){
        TicketBooker booker = new TicketBooker();
        if(!booker.passengers.containsKey(id)){
            System.out.println("Passenger Detail Unknown");
        } else
            booker.cancelTicket(id);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;

        while(loop){
            System.out.println(" 1. Book Ticket \n 2. Cancel ticket \n 3. available Tickets \n 4. Booked Tickets \n 5.Exit");
            int choice = s.nextInt();
            switch (choice){

                case 1:{
                    System.out.println("Enter Passenger name, age and berth preference(L, M or U)");
                    String name = s.next();
                    int age = s.nextInt();
                    String berthPreference = s.next();

                    Passenger p = new Passenger(name, age, berthPreference);
                    bookTicket(p);
                }
                break;

                //cancelling ticket
                case 2: {
                    System.out.println("Enter Passenger Id to cancel");
                    int id = s.nextInt();
                    cancelTicket(id);
                }
                break;
                //Printing Available Tickets
                case 3: {
                    TicketBooker booker = new TicketBooker();
                    booker.printAvailable();
                }
                break;

                case 4: {
                    TicketBooker booker = new TicketBooker();
                    booker.printPassengers();
                }
                break;

                case 5: {
                    loop = false;
                } break;
                default:;
                break;
            }
        }
    }
}