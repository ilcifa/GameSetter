package org.example.gamesetter.service;

import lombok.NoArgsConstructor;
import org.example.gamesetter.model.Utente;
import org.example.gamesetter.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente getUtenteByUsernameAndPassword(String username, String password) {
        return utenteRepository.findByUsernameAndPassword(username, password);
    }

    public void addUtente(Utente utente) {
        utenteRepository.save(utente);
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }
}
