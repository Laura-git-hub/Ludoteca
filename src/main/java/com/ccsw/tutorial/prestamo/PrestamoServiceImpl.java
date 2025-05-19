package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameRepository;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ClientService clientService;

    @Override
    public List<Prestamo> find(Long idGame, Long idClient, LocalDate date) {

        PrestamoSpecification titlespec = new PrestamoSpecification(new SearchCriteria("game.id", ":", idGame));
        PrestamoSpecification categorySpec = new PrestamoSpecification(new SearchCriteria("client.id", ":", idClient));

        return prestamoRepository.findAll(spec);
    }
}
