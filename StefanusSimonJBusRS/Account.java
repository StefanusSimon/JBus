package StefanusSimonJBusRS;


/**
* Modul 3
 * Stefanus Simon Rilando - 2206830422
 */
public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;
    
    public Account(int id, String name, String email, String password){
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString(){
        String println = "Account info: " + " | Id: " + id + " | Email: " + email + " | Name: " + name + " | Password: " + password;
        return println;
    }
}
