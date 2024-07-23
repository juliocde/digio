package br.com.teste.digio.service;

import br.com.teste.digio.model.ProdutoDto;
import br.com.teste.digio.response.ComprasClienteResponse;

import java.util.List;

public interface ComprasService {
    List<ComprasClienteResponse> buscarCompras();
    ComprasClienteResponse buscarMaiorCompraAno(List<ComprasClienteResponse> listaCompras);

}
