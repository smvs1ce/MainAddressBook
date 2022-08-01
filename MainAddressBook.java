import java.util.*;

class AddressBook {

    private ArrayList<Record> entries;
    private Scanner scan;

    public AddressBook(){
        entries = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    class Record{
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String emailAddress;

        public Record(String fName,String lName, String phone, String email){
            setFirstName(fName);
            setLastName(lName);
            setPhoneNumber(phone);
            setEmailAddress(email);
        }
        
        public String getFirstName() {
            return this.firstName;
        }
        public void setFirstName(String name){
            firstName = name;
        }
        public String getLastName() {
            return this.lastName;
        }
        public void setLastName(String name){
            lastName = name;
        }
        public String getphoneNumber() {
            return this.phoneNumber;
        }
        public void setPhoneNumber(String number){
            phoneNumber = number;
        }
        public String getEmailAddress() {
            return this.emailAddress;
        }
        public void setEmailAddress(String email){
            emailAddress = email;
        }
        
        @Override
        public String toString() {
            return firstName+" "+lastName+" "+phoneNumber+" "+emailAddress;
        }

    }

    private boolean isNumeric(String str) { 
        try {  
          Integer.parseInt(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    private boolean isEmailUnique(String email){
        for(Record record : entries){
            if(record.getEmailAddress().equals(email))
                return false;
        }
        return true;
    }

    private void displayRecord(int i){
        if(i >= entries.size())
            return;
        
        Record record = entries.get(i);
        System.out.println("****");
        System.out.println("First Name: "+record.getFirstName());
        System.out.println("Last Name: "+record.getLastName());
        System.out.println("Phone Number: "+record.getphoneNumber());
        System.out.println("Email: "+record.getEmailAddress());
        System.out.println("****");
    }

    public void addRecord(){
        System.out.println("First Name: ");
        String fname = scan.next();
        System.out.println("Last Name: ");
        String lname = scan.next();
        System.out.println("Phone Number: ");
        String phone = scan.next();
        System.out.println("Email Address: ");
        String email = scan.next();

        if(isEmailUnique(email)){
            Record record = new Record(fname,lname,phone,email);
            entries.add(record);
            System.out.println("Added new entry!");
        }
        else{
            System.out.println("Email address not unique.Record not added");
        }
    }
    
    public void removeRecord(){
        System.out.println("Enter an entry's email to remove: ");
        String email = scan.next();
        for(int i=0;i<entries.size();i++){
            Record record = entries.get(i);
            if(record.getEmailAddress().equals(email)){
                System.out.println("Deleted the following entry: ");
                displayRecord(i);
                entries.remove(i);
                return;
            }
        }
        System.out.println("Entry not found!");
    }
    
    public void searchRecord() {
        System.out.println("1) First Name: ");
        System.out.println("2) Last Name: ");
        System.out.println("3) Phone Number: ");
        System.out.println("4) Email Address: ");
        System.out.println("Choose a search type: ");
        String type = scan.next();
        if(!isNumeric(type)){
            System.out.println("Invalid search option");
            return;
        }
        int num = Integer.parseInt(type);
        if(num < 1 || num > 4){
            System.out.println("Invalid search option");
            return; 
        }
        System.out.println("Enter your search");
        String text = scan.next();
        List<Integer> list = searchRecord(num, text);
        if(!list.isEmpty()){
            for(int i : list){
                displayRecord(i);
            }
        }
        else{
            System.out.println("No record found.");
        }
        
    }
    
    public void printAddressBook() {
        if(entries.isEmpty()){
            System.out.println("No records currently exist in the address book.");
            return;
        }        
        for(int i=0;i<entries.size();i++){
            displayRecord(i);
        }
    }
    
    public void deleteAddressBook(){
        entries.clear();
        System.out.println("Address book cleared!");
    }
    
    private List<Integer> searchRecord(int type,String text){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<entries.size();i++){
            Record record = entries.get(i);
            switch (type) {
                case 1:
                    if(record.getFirstName().contains(text))
                        list.add(i);
                    break;
                case 2:
                    if(record.getLastName().contains(text))
                        list.add(i);
                    break;
                case 3:
                    if(record.getphoneNumber().contains(text))
                        list.add(i);
                    break;
                case 4:
                    if(record.getEmailAddress().contains(text))
                        list.add(i);
                    break;
                default:
                    break;
            }
        }
        return list;
    }
}

public class MainAddressBook{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        int choice = 0;
        do{
            System.out.println("1) Add an Entry");
            System.out.println("2) Remove an Entry");
            System.out.println("3) Search for a Specific Entry");
            System.out.println("4) Print Address Book");
            System.out.println("5) Delete Book");
            System.out.println("6) Quit");
            System.out.println("\nPlease choose what you'd like to do with the database: ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    addressBook.addRecord();
                    break;
                case 2:
                    addressBook.removeRecord();
                    break;
                case 3:
                    addressBook.searchRecord();
                    break;
                case 4:
                    addressBook.printAddressBook();
                    break;
                case 5:
                    addressBook.deleteAddressBook();
                    break;
                case 6:
                    System.out.println("You have chosen to exit the application");
                    break;
            
                default:
                    System.out.println("Invalid choice.Please try again");
                    break;
            }
        }while(choice != 6);
    }
}