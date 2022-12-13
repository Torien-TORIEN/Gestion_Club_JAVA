
package projet.Model;

import java.util.Scanner;

public class Salle {
    private int numSal;
    private String local;
    private String descriptionSal;
    
    public Salle(int num,String local,String des) throws MyException{
        numSal=num;
        this.descriptionSal=des;
        setLocal(local);
    }
    
    public Salle(){};
    
    /*************************** SETTERS & GETTERS *****************************/
    public void setLocal(String loc) throws MyException{
        if(loc.equals("Annexe") || loc.equals("Principal"))
            local=loc;
        else
            throw new MyException("Local is incorrect");
    }

    public void setNumSal(int numSal) {
        this.numSal = numSal;
    }

    public void setDescriptionSal(String descriptionSal) {
        this.descriptionSal = descriptionSal;
    }

    public int getNumSal() {
        return numSal;
    }

    public String getLocal() {
        return local;
    }

    public String getDescriptionSal() {
        return descriptionSal;
    }
    
    
    
    /*************************** AUTRES *****************************/
    public void saisir(Scanner sc) throws MyException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("saisir le numero de la salle :");
        numSal=sc.nextInt();
        System.out.println("Donner le local(Annexe ou Principal) :");
        String loc=sc.next();
        setLocal(loc);
        System.out.println("Donner sa description :");
        descriptionSal=scanner.nextLine();
        
              
    }
    
    @Override
    public String toString(){
        String str=this.getClass().getSimpleName()+"[ Numero :"+numSal+"; Local : "+local+"; Description :"+descriptionSal +"]";
        return str;
    }

}
