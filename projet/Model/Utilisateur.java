
package projet.Model;

import java.util.regex.*;
import java.util.*;

public class Utilisateur {
    protected int CIN ;
    protected String Nom;
    protected String Prenom;
    protected String Role;
    protected String Email;
    protected String mdp;
    
    public Utilisateur(int cin,String nom, String prenom, String role, String email,String password){
        CIN=cin;
        Nom=nom;
        Prenom=prenom;
        setRole(role);
        setEmail(email);
        setMdp(password);
    }
    public Utilisateur(){}
    
    
    /****************************** SETTEURS & GETTEURS ***************************/
    public void setRole(String role){
        if(role.equals("Admin") || role.equals("Resp") || role.equals("Membre"))
            this.Role=role;
        else
            throw new ArithmeticException("Role is incorrect");
    }
    
    public void setEmail(String email){
        String regx = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        //Compile regular expression to get the pattern 
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE); 
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            this.Email=email;
        else
            throw new ArithmeticException("Email is incorrect");
            
    }
    
    public void setMdp(String mdp){
        if(mdp.length()>=3)
            this.mdp=mdp;
        else throw new ArithmeticException("Mot de passe trop faible au moins 4 caractères");
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
    
    

    public int getCIN() {
        return CIN;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getRole() {
        return Role;
    }

    public String getEmail() {
        return Email;
    }

    public String getMdp() {
        return mdp;
    }
    
    

    

    
    
    @Override
    public String toString(){
        String str=this.getClass().getSimpleName()+"[ CIN :"+CIN+"; Noms : "+Prenom +" "+Nom +"; Email :"+Email +"]";
        return str;
    }
}

