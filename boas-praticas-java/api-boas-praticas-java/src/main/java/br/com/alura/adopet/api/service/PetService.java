package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    public List<PetDto> buscarPetsDisponiveis() {

        return petRepository
                .findAllByAdotadoFalse()
                .stream()
                .map(PetDto::new)
                .toList();


    }

    public void cadastrarPet(Abrigo abrigo, CadastroPetDto dto) {

        petRepository.save(new Pet(abrigo, dto));

    }
}
