package br.com.teste.digio.service;


import br.com.teste.digio.model.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "produto", url = "https://rgr3viiqdl8sikgv.public.blob.vercel-storage.com/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
public interface ProdutoFeignClient {

    @RequestMapping(method = RequestMethod.GET)
    List<ProdutoDto> getProdutos();

}
