package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @InjectMocks
    ValidacaoPetDisponivel validacaoPetDisponivel;

    @Mock
    private Pet pet;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Mock
    private PetRepository petRepository;

    @Test
    public void testDeveriaPermitirSolicitacaoDeAdocaoPet() {

        given(petRepository.getReferenceById(dto.idPet().longValue())).willReturn(pet);
        given(pet.getAdotado()).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));

    }

    @Test
    public void testNaoDeveriaPermitirSolicitacaoDeAdocaoPet() {

        given(petRepository.getReferenceById(dto.idPet().longValue())).willReturn(pet);
        given(pet.getAdotado()).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(dto));

    }

}