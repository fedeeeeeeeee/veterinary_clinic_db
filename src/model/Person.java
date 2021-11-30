package model;

/**
 * This interface represents a person. 
 */
public interface Person {
    
    public String getNome();
    
    public String getCognome();
    
    public String getCF();
    
    public String getSesso();
    
    public String getDataNascita();
    
    public String getCitta();
    
    public String getIndirizzoVia();
    
    public int getIndirizzoNumero();
    
    public String getCap();

    public String getIndirizzoCitta();
    
    public String getTelefono();
    
    public String getEmail();
    
    public void setNome(String nome);
    
    public void setCognome(String cognome);
    
    public void setCF(String CF);
    
    public void setSesso(String sesso);
    
    public void setIndirizzoVia(String via);
    
    public void setIndirizzoNumero(int numero);
    
    public void setCap(int cap);


    public void setIndirizzoCitta(String citta);
    
    public void setDataNascita(String dataNascita);
   
    public void setCitta(String citta);

    public void setTelefono(String telefono);
    
    public void setEmail(String email);

}
