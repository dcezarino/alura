package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {


    @InjectMocks
    private AbrigoService abrigoService;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;

    @Captor
    private ArgumentCaptor<Abrigo> abrigoCaptor;

    @Test
    public void deveriaListarAbrigosComSucesso() {

        given(abrigoRepository.findAll()).willReturn(returnMockListAbrigo());

        List<AbrigoDto> returnListAbrigoDTO = abrigoService.listar();

        Assertions.assertEquals("Abrigo Modelo", returnListAbrigoDTO.get(0).nome());

    }

    @Test
    public void deveriaCadastrarAbrigosComSucesso() {

        CadastroAbrigoDto dto = new CadastroAbrigoDto("Abrigo Modelo", "19991056666", "abrigo@abrigo.com.br");

        given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())).willReturn(false);

        abrigoService.cadastrar(dto);

        then(abrigoRepository).should().save(abrigoCaptor.capture());
        Abrigo abrigoSalvo = abrigoCaptor.getValue();

        Assertions.assertEquals(dto.nome(), abrigoSalvo.getNome());
        Assertions.assertEquals(dto.telefone(), abrigoSalvo.getTelefone());
        Assertions.assertEquals(dto.email(), abrigoSalvo.getEmail());

    }

    @Test
    public void deveriaListarPetsDoAbrigoByIdComSucesso() throws Exception {

        given(abrigoRepository.findById(1l)).willReturn(Optional.of(returnMockAbrigo()));

        Abrigo abrigo = abrigoService.carregarAbrigo("1");

        given(petRepository.findByAbrigo(abrigo)).willReturn(returnMockListPet(abrigo));

        List<PetDto> listPets = abrigoService.listarPetsDoAbrigo("1");

        Assertions.assertEquals(TipoPet.CACHORRO, listPets.get(0).tipo());
        Assertions.assertEquals("Cachorro Teste", listPets.get(0).nome());
        Assertions.assertEquals("Vira Lata", listPets.get(0).raca());
        Assertions.assertEquals(10, listPets.get(0).idade());

    }

    @Test
    public void deveriaListarPetsDoAbrigoByNomeComSucesso() {

        given(abrigoRepository.findByNome(anyString())).willReturn(Optional.of(returnMockAbrigo()));

        Abrigo abrigo = abrigoService.carregarAbrigo("Abrigo Modelo");

        given(petRepository.findByAbrigo(abrigo)).willReturn(returnMockListPet(abrigo));

        List<PetDto> listPets = abrigoService.listarPetsDoAbrigo("Abrigo Modelo");

        Assertions.assertEquals(TipoPet.CACHORRO, listPets.get(0).tipo());
        Assertions.assertEquals("Cachorro Teste", listPets.get(0).nome());
        Assertions.assertEquals("Vira Lata", listPets.get(0).raca());
        Assertions.assertEquals(10, listPets.get(0).idade());

    }

    @Test
    public void deveriaRetornarDadosCadastradosParaOutroAbrigo() {

        CadastroAbrigoDto dto = new CadastroAbrigoDto("Abrigo Modelo", "19991056666", "abrigo@abrigo.com.br");

        given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())).willReturn(true);

        try {
            abrigoService.cadastrar(dto);
            fail("Esperava-se que uma ValidacaoException fosse lançada.");
        } catch (ValidacaoException e) {
            Assertions.assertEquals("Dados já cadastrados para outro abrigo!", e.getMessage());
        }

    }

    private List<Abrigo> returnMockListAbrigo() {

        CadastroAbrigoDto cadastroAbrigoDto = new CadastroAbrigoDto("Abrigo Modelo", "19991056666", "abrigo@abrigo.com.br");

        return List.of(new Abrigo(cadastroAbrigoDto));

    }

    private Abrigo returnMockAbrigo() {

        CadastroAbrigoDto cadastroAbrigoDto = new CadastroAbrigoDto("Abrigo Modelo", "19991056666", "abrigo@abrigo.com.br");

        return new Abrigo(cadastroAbrigoDto);

    }

    private List<Pet> returnMockListPet(Abrigo abrigo) {

        CadastroPetDto cadastroPetDto = new CadastroPetDto(TipoPet.CACHORRO, "Cachorro Teste", "Vira Lata", 10, "Branca", 15f);

        Pet pet = new Pet(cadastroPetDto, abrigo);

        return List.of(pet);

    }


}