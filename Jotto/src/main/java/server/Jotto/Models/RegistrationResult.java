package server.Jotto.Models;

public class RegistrationResult{
    private String status;
    private String username;

    public String getstatus(){return status;}
    public String getusename(){return username;}
    public void setStatus(String username){this.status = status;}
    public void setUsername(String password){this.username = username;}
}