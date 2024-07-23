package br.com.teste.digio.response;

import br.com.teste.digio.model.ProdutoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {

    private ProdutoDto produtoDto;
    private Float valorTotalCompra;
    private int quantidade;
}
