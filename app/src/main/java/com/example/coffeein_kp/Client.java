package com.example.coffeein_kp;

public class Client {
    private String Name;
    private String Password;
    private String Mail;
    private String DocId;

    public Client (String Name, String Password, String Mail){
        this.Name = Name;
        this.Password = Password;
        this.Mail = Mail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        DocId = docId;
    }
}
