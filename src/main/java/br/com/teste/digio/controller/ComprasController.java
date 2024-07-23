package br.com.teste.digio.controller;

import br.com.teste.digio.response.ComprasClienteResponse;
import br.com.teste.digio.response.ErrorResponse;
import br.com.teste.digio.response.ProdutoResponse;
import br.com.teste.digio.service.ComprasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class ComprasController {

	@Autowired
	private ComprasService comprasService;

	@Operation(summary = "Retornar uma lista das compras ordenadas de forma crescente por valor, deve conter o nome dos clientes, cpf dos clientes, dado dos produtos, quantidade das compras e valores totais de cada compra.")
	@GetMapping("/compras")
	public ResponseEntity<?> getCompras() {

		List<ComprasClienteResponse> listaCompras = comprasService.buscarCompras();

		if(listaCompras.isEmpty()){
			return ResponseEntity.badRequest().body(new ErrorResponse("Dados não encontrados"));
		}

		return ResponseEntity.ok().body(listaCompras);
	}
	@Operation(summary = "Retornar a maior compra do ano informando os dados da compra disponibilizados, deve ter o nome do cliente, cpf do cliente, dado do produto, quantidade da compra e seu valor total.")
	@GetMapping("/maior-compra/{ano}")
	public ResponseEntity<?> getMaiorCompraAno(@PathVariable int ano) {

		List<ComprasClienteResponse> listaCompras = comprasService.buscarCompras();

		for (ComprasClienteResponse compra: listaCompras){
			compra.getProdutosCompras().removeIf(anoCompra -> anoCompra.getProdutoDto().getAno_compra() != ano);
		}

		listaCompras.removeIf(anoCompra -> anoCompra.getProdutosCompras().isEmpty());

		if(listaCompras.isEmpty()){
			return ResponseEntity.badRequest().body(new ErrorResponse("Não existem dados para este ano."));
		}

		ComprasClienteResponse resultado = comprasService.buscarMaiorCompraAno(listaCompras);

		return ResponseEntity.ok().body(resultado);
	}

	@Operation(summary = "Retornar o Top 3 clientes mais fieis, clientes que possuem mais compras recorrentes com maiores valores.")
	@GetMapping("/clientes-fieis")
	public ResponseEntity<?> getClientesFieis() {

		List<ComprasClienteResponse> listaCompras = comprasService.buscarCompras();

		List<ComprasClienteResponse> clientesFieis = listaCompras.subList(listaCompras.size()-3, listaCompras.size());

		return ResponseEntity.ok().body(clientesFieis);
	}

	@Operation(summary = "Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.")
	@GetMapping("/recomendacao/cliente/tipo")
	public ResponseEntity<?> getRecomendacao() {

		List<ComprasClienteResponse> listaCompras = comprasService.buscarCompras();

		for (ComprasClienteResponse c: listaCompras){
			ProdutoResponse maiorQuantidade = Collections.max(c.getProdutosCompras(), Comparator.comparing(ProdutoResponse::getQuantidade));
			c.getProdutosCompras().clear();
			c.getProdutosCompras().add(maiorQuantidade);
		}

		return ResponseEntity.ok().body(listaCompras);
	}

}
