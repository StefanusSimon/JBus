package StefanusSimonJBusRS;


/**
* Modul 3
* Stefanus Simon Rilando - 2206830422
*/
public class Review extends Serializable
{
    public String date;
    public String desc;
    
    public Review(int id, String date, String desc){
       super(id);
       this.date = date;
       this.desc = desc;
    }
    
    public String toString(){
        String println = "Review Info: " + " | Date: " + date + " | Desc: " + desc;
        return println;
    }
}
