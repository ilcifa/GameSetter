package org.example.gamesetter.service;

import lombok.NoArgsConstructor;
import org.example.gamesetter.repository.GiocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class GiocoService {
    @Autowired
    private GiocoRepository giocoRepository;
}
