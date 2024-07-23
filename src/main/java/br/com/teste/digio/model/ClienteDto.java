package br.com.teste.digio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private String nome;
    private String cpf;
    private List<CompraDto> compras;

    public List<CompraDto> getCompras() {
        return compras;
    }
}
