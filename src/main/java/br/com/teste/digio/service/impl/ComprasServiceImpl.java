package br.com.teste.digio.service.impl;

import br.com.teste.digio.model.ClienteDto;
import br.com.teste.digio.model.CompraDto;
import br.com.teste.digio.model.ProdutoDto;
import br.com.teste.digio.response.ComprasClienteResponse;
import br.com.teste.digio.response.ErrorResponse;
import br.com.teste.digio.response.ProdutoResponse;
import br.com.teste.digio.service.ComprasClienteFeignClient;
import br.com.teste.digio.service.ComprasService;
import br.com.teste.digio.service.ProdutoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class ComprasServiceImpl implements ComprasService {

    @Autowired
    private ProdutoFeignClient produtoFeignClient;

    @Autowired
    private ComprasClienteFeignClient comprasClienteFeignClient;

    @Override
    public List<ComprasClienteResponse>
    buscarCompras() {

        List<ClienteDto> clientes = comprasClienteFeignClient.getClientes();
        List<ProdutoDto> produtos = produtoFeignClient.getProdutos();


        List<ComprasClienteResponse> listaComprasPorCliente = new ArrayList<ComprasClienteResponse>();

        for (ClienteDto cliente : clientes) {

            ComprasClienteResponse comprasClienteResponse = new ComprasClienteResponse();
            comprasClienteResponse.setCpfCliente(cliente.getCpf());
            comprasClienteResponse.setNomeCliente(cliente.getNome());
            comprasClienteResponse.setQuantidadeCompras(cliente.getCompras().size());

            List<ProdutoResponse> produtosComprasResponse = new ArrayList<>();

            //popula o produto de cada compra
            for (CompraDto compra : cliente.getCompras()) {
                ProdutoDto produtoDto = produtos.stream().filter(p -> String.valueOf(p.getCodigo()).equals(compra.getCodigo())).findFirst().orElse(null);
                produtosComprasResponse.add(
                        new ProdutoResponse(
                                produtoDto,
                                compra.getQuantidade() * produtoDto.getPreco(),
                                compra.getQuantidade()));
            }

            Float valorTotal = 0.f;
            for (ProdutoResponse valor : produtosComprasResponse) {
                valorTotal += valor.getValorTotalCompra();
            }

            comprasClienteResponse.setValorTotalGasto(valorTotal);
            comprasClienteResponse.setProdutosCompras(produtosComprasResponse);

            listaComprasPorCliente.add(comprasClienteResponse);
        }

        //ordenar por valor de cada compra
        for(ComprasClienteResponse compras : listaComprasPorCliente){
            compras.getProdutosCompras().sort(Comparator.comparing(ProdutoResponse::getValorTotalCompra));
        }

        //ordenar por valor total gasto de cada cliente
        listaComprasPorCliente.sort(Comparator.comparing(ComprasClienteResponse::getValorTotalGasto));

        return listaComprasPorCliente;
    }

    @Override
    public ComprasClienteResponse buscarMaiorCompraAno(List<ComprasClienteResponse> listaCompras) {
        List<ComprasClienteResponse> listaMaioresComprasPorCliente = new ArrayList<ComprasClienteResponse>();
        for (ComprasClienteResponse c: listaCompras){
            ProdutoResponse maiorValorCompra = Collections.max(c.getProdutosCompras(), Comparator.comparing(ProdutoResponse::getValorTotalCompra));
            ComprasClienteResponse comprasClienteResponse = new ComprasClienteResponse();
            comprasClienteResponse.setNomeCliente(c.getNomeCliente());
            comprasClienteResponse.setCpfCliente(c.getCpfCliente());
            List<ProdutoResponse> produtosMaiorValorCompra = new ArrayList<ProdutoResponse>();
            produtosMaiorValorCompra.add(maiorValorCompra);
            comprasClienteResponse.setProdutosCompras(produtosMaiorValorCompra);
            listaMaioresComprasPorCliente.add(comprasClienteResponse);
        }

        ComprasClienteResponse resultado = new ComprasClienteResponse();

        for(ComprasClienteResponse result : listaMaioresComprasPorCliente){
            ProdutoResponse maiorValorCompra = Collections.max(result.getProdutosCompras(), Comparator.comparing(ProdutoResponse::getValorTotalCompra));

            resultado.setNomeCliente(result.getNomeCliente());
            resultado.setCpfCliente(result.getCpfCliente());
            resultado.setQuantidadeCompras(result.getQuantidadeCompras());
            resultado.setValorTotalGasto(result.getValorTotalGasto());
            List<ProdutoResponse> produtosMaiorValorCompra = new ArrayList<ProdutoResponse>();
            produtosMaiorValorCompra.add(maiorValorCompra);
            resultado.setProdutosCompras(produtosMaiorValorCompra);
        }
        return resultado;
    }

}
