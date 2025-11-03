package br.com.anm.produtos.crud_produtos.controle;

import br.com.anm.produtos.crud_produtos.modelo.ProdutoModelo;
import br.com.anm.produtos.crud_produtos.servico.ProdutoServico;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoControle.class)
class ProdutoControleTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ProdutoServico ps;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void deveAlterarProdutoComSucesso() throws Exception {
        ProdutoModelo produto = new ProdutoModelo();
        produto.setCodigo(1L);
        produto.setNome("Notebook Atualizado");
        produto.setMarca("Dell");
        when(ps.cadastrarAlterar(any(ProdutoModelo.class), eq("alterar")))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body(produto));
        mockMvc.perform(put("/alterar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Notebook Atualizado"));
    }

    @Test
    void deveRemoverProdutoComSucesso() throws Exception {
        when(ps.remover(1L)).thenReturn(ResponseEntity.ok().build());
        mockMvc.perform(delete("/remover/{codigo}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarNotFoundAoRemoverProdutoInexistente() throws Exception {
        when(ps.remover(99L)).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        mockMvc.perform(delete("/remover/{codigo}", 99L))
                .andExpect(status().isNotFound());
    }
}
