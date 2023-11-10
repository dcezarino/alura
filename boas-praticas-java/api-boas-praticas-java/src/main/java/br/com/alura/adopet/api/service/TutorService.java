package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizaTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutoDto;
import br.com.alura.adopet.api.exception.ValidationException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public void cadastrarTutor(CadastroTutoDto dto){

        boolean jaCadastrado = tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

        if (jaCadastrado) {
            throw new ValidationException("Dados já cadastrados para outro tutor!");
        }

        tutorRepository.save(new Tutor(dto));

    }

    public void atualizarTutor(AtualizaTutorDto dto) {

        Tutor tutor = tutorRepository.getReferenceById(dto.id());
        tutor.atualizaTutor(dto);

    }

}
