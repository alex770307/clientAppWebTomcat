package org.clientappwebtomcat.back.entity;

public class Client {

    private Integer idClient;
    private String name;
    private String email;
    private String password;

    public Client(Integer idClient, String name, String email, String password) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Client(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
