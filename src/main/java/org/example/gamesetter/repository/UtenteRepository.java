package org.example.gamesetter.repository;

import org.example.gamesetter.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
    Utente findByUsernameAndPassword(String username, String password);
    Utente findByUsername(String username);
    Utente findByEmail(String email);
}
