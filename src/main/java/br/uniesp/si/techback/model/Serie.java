package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Data

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "series")

public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(length = 50)
    private String genero;

    @Column(name = "temporadas")
    private Integer numeroTemporadas;

    @Column(name = "classificacao_indicativa", length = 10)
    private String classificacaoIndicativa;

    @Column(name = "em_andamento")
    private Boolean emAndamento;

    @Column(name = "duracao_minutos")
    private Integer duracaoMinutos;
 public Serie(){

 }
}
