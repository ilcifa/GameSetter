package org.example.gamesetter.repository;

import org.example.gamesetter.model.Gioco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiocoRepository extends JpaRepository<Gioco, String>  {

}
