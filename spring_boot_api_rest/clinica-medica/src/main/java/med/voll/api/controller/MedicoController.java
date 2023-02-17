package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    /*
    Injeção de Dependências: indicar com a anotação "@Autowired" para
    que o Spring instancie esse atributo "medicoRepository", já que esse atributo
    é uma interface repository que deve ser reconhecida e recarregada,  então cria
    o objeto e passa para o controller automaticamente.
     */
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {

        medicoRepository.save(new Medico(dados));



    }


}
