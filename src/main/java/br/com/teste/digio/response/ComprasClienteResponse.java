package br.com.teste.digio.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComprasClienteResponse {

    private String nomeCliente;
    private String cpfCliente;
    private Integer quantidadeCompras;
    private Float valorTotalGasto;
    private List<ProdutoResponse> produtosCompras;

}
