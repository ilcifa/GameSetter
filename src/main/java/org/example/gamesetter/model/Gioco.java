package org.example.gamesetter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="giochi")
public class Gioco {
    @Id
    private String nome;
    private String foto;
    private Integer anno;
    private Integer posizione;
    private boolean platino;
    @ManyToOne
    @JoinColumn(name = "utente")
    private Utente utente;
}
