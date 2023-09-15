package StefanusSimonJBusRS;


/**
 * Modul 2
 * Stefanus Simon Rilando - 2206830422
 */
public class Rating
{
    private long count;
    private long total;
    
    public Rating(){
        this.count = 0;
        this.total = 0;
    } 
    
    public void insert(int rating){
        this.total += rating;
        this.count ++;  
    }
    
    public long getTotal(){
        return this.total;
    }
    
    public long getCount(){
        return this.count;
    }
    
    public double getAverage(){
        if(count == 0){
         System.out.println("Tidak bisa dibagi 0"); 
         return 0;
        } else {
            double average = this.total / this.count;
            return average;
    }
}
}

