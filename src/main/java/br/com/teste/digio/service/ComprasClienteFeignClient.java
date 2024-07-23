package br.com.teste.digio.service;


import br.com.teste.digio.model.ClienteDto;
import br.com.teste.digio.model.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "compra", url = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
public interface ComprasClienteFeignClient {

    @RequestMapping(method = RequestMethod.GET)
    List<ClienteDto> getClientes();

}
