import java.util.*;

class bankaccount
{
    static void register()
    {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("\033[H\033[2J");
            System.out.print("Enter your name : ");
            atm.name = s.nextLine();
            System.out.print("Enter username : ");
            String user = s.nextLine();
            System.out.print("Enter password : ");
            String pass = s.nextLine();
            System.out.print("Enter Account no. : ");
            atm.accnumber = s.nextLine();
            System.out.println("REGISTERED SUCCESSFULLY!");
            System.out.println("------------------------");
            while(true)
            {
                display(atm.name);
                int choice = s.nextInt();
                if(choice==1)
                {
                    login(user,pass);
                    break;
                }
                else
                {
                    if(choice==2)
                    {
                        System.exit(0);
                    }
                    else
                    {
                        System.out.println("Bad value! Enter again!");
                    }
                }
            }
        }
    }
    static void display(String name)
    {
        System.out.println("hello "+name+"! Select option : ");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Enter your choice : ");
    }
    static void login(String user, String pass)
    {
        try (Scanner s = new Scanner(System.in)) {
            while(true)
            {
                System.out.print("Enter username : ");
                String username = s.nextLine();
                System.out.print("Enter password : ");
                String password = s.nextLine();
                if(username.equals(user) && password.equals(pass))
                {
                    atm.prompt();
                    break;
                }
                else
                {
                    System.out.println("Incorrect username or password! Enter again!");
                }
            }
        }
    }
}

class transaction
{
    static void withdraw()
    {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("---------------------");
            System.out.print("Enter amount to withdraw : ");
            int wcash = s.nextInt();
            if(wcash<=atm.balance)
            {
                atm.balance = atm.balance - wcash;
                atm.history.add(Integer.toString(wcash));
                atm.history.add("Withdraw");
                System.out.println("Amount Rs "+wcash+"/- withdraw successfully!");
            }
            else
            {
                System.out.println("Insufficient balance for cash withdrawal!");
            }
            atm.prompt();
        }
    }
    static void deposit()
    {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("---------------------");
            System.out.print("Enter amount to deposit : ");
            int dcash = s.nextInt();
            atm.updatebalance(dcash);
            atm.history.add(Integer.toString(dcash));
            atm.history.add("Deposit");
            System.out.println("Amount Rs."+dcash+"/- deposited successfully!");
            atm.prompt();
        }
    }
    static void transfer()
    {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("---------------------");
            System.out.print("Enter name of the reciever : ");
            String nam = s.nextLine();
            System.out.print("Enter acc no. of the reciever : ");
            String rec = s.nextLine();
            System.out.print("Enter amount to transfer : ");
            int tcash = s.nextInt();
            if(tcash<=atm.balance)
            {
                atm.balance = atm.balance-tcash;
                atm.history.add(Integer.toString(tcash));
                atm.history.add("Transfered to "+nam);
                System.out.println("Amount Rs."+tcash+"/- transfered successfully to Acc : "+rec);
            }
            else
            {
                System.out.println("Insufficient balance for cash withdrawal!");
            }
            atm.prompt();
        }
    }
}

class check
{
    static void checkbalance()
    {
        System.out.println("---------------------");
        System.out.print("Available balance on a/c no. "+atm.accnumber+" is : ");
        atm.showbalance();
        atm.prompt();
    }
}

class his
{
    static void transactionhistory()
    {
        System.out.println("---------------------");
        System.out.println("Transaction History :");
        int k=0;
        for(int i=0;i<(atm.history.size()/2);i++)
        {
            for(int j=0;j<2;j++)
            {
                System.out.print(atm.history.get(k)+" ");
                k++;
            }
            System.out.println("");
        }
        atm.prompt();
    }
}

public class atm {
    public static String name ;
    public static int balance=0;
    public static String accnumber;
    public static ArrayList<String> history = new ArrayList<String>();

    static void updatebalance(int dcash)
    {
        balance = balance + dcash;
    }
    static void showbalance()
    {
        System.out.println(balance);
    }

    public static void homepage()
    {
        System.out.println("\033[H\033[2J");
        try (Scanner s = new Scanner(System.in)) 
        {
            System.out.println("WELCOME TO ATM SYSTEM");
            System.out.println("---------------------");
            System.out.println("Select option : ");
            System.out.println("1. Register");
            System.out.println("2. Exit");
            System.out.print("Enter your choice : ");
            int choice = s.nextInt();
            if(choice==1)
            {
                bankaccount.register();
            }
            else
            {
                if(choice==2)
                {
                    System.exit(0);
                }
                else
                {
                    System.out.println("Bad value! Enter again!");
                    homepage();
                }
            }
        }
    }
    static void prompt()
    {
        try (Scanner s = new Scanner(System.in)) {
            System.out.println("WELCOME "+atm.name+"! TO ATM SYSTEM");
            System.out.println("---------------------");
            System.out.println("Select option : ");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Check balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice : ");
            int choice = s.nextInt();
            while(true){
            switch(choice)
            {
                case 1:
                transaction.withdraw();
                break;
                case 2:
                transaction.deposit();
                break;
                case 3:
                transaction.transfer();
                break;
                case 4:
                check.checkbalance();
                break;
                case 5:
                his.transactionhistory();
                break;
                case 6:
                System.exit(0);
                break;
                default:
                System.out.println("Please try again");
            }
            }
        }
    }
    public static void main(String args[])
    {
        homepage();
    }
}
