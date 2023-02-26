import java.util.regex.*;
import java.util.*;

class Address
{
    String street1,street2,city,state,pincode;

    Address(String street1,String street2,String city,String pincode )
    {
        this.street1=street1;
        this.street2=street2;
        this.city=city;
        this.pincode=pincode;
    }

    void displayAddress()
    {
        System.out.println("stree1 =  "+this.street1 );
        System.out.println("street2 = "+this.street2 );
        System.out.println("pin_code = " +this.pincode );
        System.out.println("city = "+this.city);
    }


}

class EmailNotValidException extends Exception{
    EmailNotValidException(String msg){
        super(msg);
    }
}


class PhoneNumberNotValidException extends Exception{
    PhoneNumberNotValidException(String msg){
        super(msg);
    }
}

class Validate
{
    static boolean validateEmail(String email)
    {
        String regex = "^[a-zA-Z0-9_'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern p = Pattern.compile(regex);

        if (email == null) {
            return false;
        }

        Matcher m = p.matcher(email);

        return m.matches();
    }

    static boolean validatePhoneNo(String pno)
    {
        //allows only indian numbers
        String regex = "^\\+?[9][1]-?[1-9][0-9]{9}$";
        Pattern p = Pattern.compile(regex);

        if (pno == null) {
            return false;
        }

        Matcher m = p.matcher(pno);

        return m.matches();
    }
}

class Person
{
    String name,email,phone;
    Person manager;
    Address address;

    Person(String name,String email,String phone,Address address,Person manager) throws Exception
    {
        if(!Validate.validateEmail(email))
        {
            throw new EmailNotValidException("Please Enter a valid email address for "+ name);
        }
        if(!Validate.validatePhoneNo(phone))
        {
            throw new PhoneNumberNotValidException("Please Enter a valid phone number for "+name);
        }

        this.name=name;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.manager=manager;
    }


    void displayPerson()
    {
        System.out.println("name =  "+this.name );
        System.out.println("email = " +this.email );
        System.out.println("phone = " +this.phone );
        if(this.manager!=null)
            System.out.println("manager = " +this.manager.name);
    }

}

public class Main
{
    public static void main(String[] args) throws Exception{

        Address p1ad=new Address("s1","s223","city1","500084");
        Address p2ad=new Address("s2","s2312","city2","500044");
        Address p3ad=new Address("s3","s23154","city3","500084");
        Address p4ad=new Address("s4","s2222","city4","500074");

        Person p1=null,p2=null,p3=null,p4=null,p5=null;

        try {
            p1=new Person("p1","p1@email.com","+91-9999999999",p1ad,null);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        } catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p2=new Person("p2","p2@email.com","+91-9925999989",p2ad,p1);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p3=new Person("p3","p3@email.com","+91-9999939989",p3ad,p2);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p4=new Person("p4","p4@email.com","+91-9354999989",p4ad,p3);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }

        try {
            p5=new Person("p5","@email.com","+91-8799699989",p3ad,p4);
        } catch(EmailNotValidException e) {
            e.printStackTrace();
        }catch(PhoneNumberNotValidException e){
            e.printStackTrace();
        }


        // if the entered phone number or email is not valid object will not be created 
        // the reference will remain null

        //get details of p4

        p4.displayPerson();
        System.out.println("Address");
        p4.address.displayAddress();

        Person p=p4.manager;
        System.out.println();
        while(p!=null)
        {
            p.displayPerson();
            System.out.println("Address");
            p.address.displayAddress();
            p=p.manager;
            System.out.println();
        }


    }
}
