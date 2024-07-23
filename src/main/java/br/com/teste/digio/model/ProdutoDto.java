package br.com.teste.digio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    private int codigo;
    private String tipo_vinho;
    private Float preco;
    private String safra;
    private int ano_compra;

}
