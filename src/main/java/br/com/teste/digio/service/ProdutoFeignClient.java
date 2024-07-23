package br.com.teste.digio.service;


import br.com.teste.digio.model.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value = "produto", url = "${api.produtos}")
public interface ProdutoFeignClient {

    @RequestMapping(method = RequestMethod.GET)
    List<ProdutoDto> getProdutos();

}
