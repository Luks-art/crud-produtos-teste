package br.com.anm.produtos.crud_produtos.servico;

import br.com.anm.produtos.crud_produtos.modelo.ProdutoModelo;
import br.com.anm.produtos.crud_produtos.modelo.RespostaModelo;
import br.com.anm.produtos.crud_produtos.repositorio.ProdutoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServicoTest {

    @Mock private ProdutoRepositorio pr;
    @Mock private RespostaModelo rm;
    @InjectMocks private ProdutoServico ps;

    @Test
    void deveAlterarProdutoComSucesso() {
        ProdutoModelo produto = new ProdutoModelo();
        produto.setCodigo(1L);
        produto.setNome("Mouse");
        produto.setMarca("Logitech");
        when(pr.save(any(ProdutoModelo.class))).thenReturn(produto);
        ResponseEntity<?> resposta = ps.cadastrarAlterar(produto, "alterar");
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(pr, times(1)).save(produto);
    }

    @Test
    void deveRemoverProdutoComSucesso() {
        ProdutoModelo produto = new ProdutoModelo();
        produto.setCodigo(10L);
        when(pr.findById(10L)).thenReturn(Optional.of(produto));
        doNothing().when(pr).delete(produto);
        ResponseEntity<?> resposta = ps.remover(10L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(pr, times(1)).delete(produto);
    }

    @Test
    void deveRetornarErroAoRemoverProdutoInexistente() {
        when(pr.findById(99L)).thenReturn(Optional.empty());
        ResponseEntity<?> resposta = ps.remover(99L);
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        verify(pr, never()).delete(any(ProdutoModelo.class));
    }
}
