/******************* Importing Essential Libraries ***********************/

import java.util.*;
import java.io.*;

class EmployDetail
{
    String name;
    String father_name;
    String email;
    String position;
    String employ_id;
    String employ_salary;
    String employ_contact;
    public void getInfo()
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Employee name        : ");
        name=sc.nextLine();
        System.out.print("Enter Father's name        : ");
        father_name=sc.nextLine();
        System.out.print("Enter Employee ID          : ");
        employ_id=sc.nextLine();
        System.out.print("Enter Employee Email       : ");
        email=sc.nextLine();
        System.out.print("Enter Employee position    : ");
        position=sc.nextLine();
        System.out.print("Enter Employee contact no. : ");
        employ_contact=sc.nextLine();
        System.out.print("Enter Employee salary      : ");
        employ_salary=sc.nextLine();
    }
}
class Employee_Add{
    public void createFile()
    {
        EmployDetail emp=new EmployDetail();
        emp.getInfo();
        try{
            File f1=new File("file"+emp.employ_id+".txt");
            if(f1.createNewFile()){
                FileWriter myWriter = new FileWriter("file"+emp.employ_id+".txt");
                myWriter.write("Employee ID:"+emp.employ_id+"\n"+"Employee Name     :"+emp.name+"\n"+
                "Father's Name     :"+emp.father_name+"\n"+"Employee Contact  :"+emp.employ_contact+"\n"+
                "Email Information :"+emp.email+"\n"+"Employee position :"+emp.position+"\n"+
                "Employee Salary   :"+emp.employ_salary);
                myWriter.close();
                System.out.println("employee added");
            }
            else {
                System.out.println("employee already exist");
            }
        }
        catch(Exception e){System.out.println(e);}
    }
}
class Employee_Remove{
    public void removeFile(String ID){

    File file = new File("file"+ID+".txt");
      if(file.exists())
       {if(file.delete());
         { System.out.println("employee removed successfully");} }
      else
       {
            System.out.println("employee does not exist");
       }
}
}
class Employee_Update{
public void updateFile(String s,String o,String n) throws IOException
{
 File file = new File("file"+s+".txt");
        Scanner sc = new Scanner(file);
  String fileContext="";
    while (sc.hasNextLine())
     { fileContext =fileContext+"\n"+sc.nextLine(); }
        FileWriter myWriter = new FileWriter("file"+s+".txt");
        fileContext = fileContext.replaceAll(o,n);
        myWriter.write(fileContext);
myWriter.close();

}
}

class Employee_Show{
public void viewFile(String s) throws Exception
{
    File file = new File("file"+s+".txt");
    Scanner sc = new Scanner(file);

    while (sc.hasNextLine())
     { System.out.println(sc.nextLine()); }
   }
}

class EmployManagementSystem
{
public static void main(String arv[])
{
    Scanner sc=new Scanner(System.in);
    Employee_Show epv =new Employee_Show();

    int i=0;
    System.out.println("Press 1 : To add an Employee Details");
    System.out.println("Press 2 : To See an Employee Details ");
    System.out.println("Press 3 : To Remove an Employee");
    System.out.println("Press 4 : To Update Employee Details");
    while(i<5)
    {
      sc.nextLine();
      System.out.println("Please Enter choice :");
      i=Integer.parseInt(sc.nextLine());
      switch(i)
      {
        case 1:
        {
        Employee_Add ep =new Employee_Add();
        ep.createFile();
        //System.out.print("\033[H\033[2J");
        break;
        }
        case 2:
        {
          System.out.println("Enter Employee ID :");
          String s=sc.nextLine();
          try
          {
            epv.viewFile(s);}
            catch(Exception e){System.out.println(e);}
            break;
          }
    case 3:
    {System.out.println("Enter Employee ID :");
    String s=sc.nextLine();
    Employee_Remove epr =new Employee_Remove();
    epr.removeFile(s);
    break;
    }
    case 4:
    {
        System.out.println("Enter Employee ID :");
        String I=sc.nextLine();
        try{
        epv.viewFile(I);}
        catch(Exception e){System.out.println(e);}
        Employee_Update epu = new Employee_Update();
        System.out.println("enter the detail you want to change same as above:");
	      System.out.println("\n For Example:\n");
        System.out.println("If you want to update an Employee name hritik to jagmeet then type hritik and press enter \n then type jagmeet and press enter. The name hritik will update to jagmeet.");
        String s=sc.nextLine();
        System.out.println("enter new Detail:");
        String n=sc.nextLine();
        try{epu.updateFile(I,s,n);}
        catch(IOException e){System.out.println(e);}
        }
        }
        }
    }
}
