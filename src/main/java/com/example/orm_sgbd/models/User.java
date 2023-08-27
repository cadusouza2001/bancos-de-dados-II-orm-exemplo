package com.example.orm_sgbd.models;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;

    @Column(unique = true, nullable = false)
    private String username;

    //Watchlist of many movies to many users (each user can have many movies in his watchlist)
    @ManyToMany
    @JoinTable(
            name = "watchlist",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idMovie")
    )
    private List<Movie> watchlist;

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Movie> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Movie> watchlist) {
        this.watchlist = watchlist;
    }
}
