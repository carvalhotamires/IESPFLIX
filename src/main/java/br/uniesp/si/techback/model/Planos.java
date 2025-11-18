package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planos")
@Getter
@Setter
public class Planos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 100)
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @Column(nullable = false)
    private Integer duracaoDias;
    
    @Column(nullable = false)
    private Integer limiteDispositivos;

    @Builder.Default
    @Column(name = "tem_anuncios" ,nullable = false)
    private Boolean temAnuncios = false;
    
    @Column(nullable = false, length = 50)
    private String resolucao = "Full HD (1080p)";
    
    @Column(name = "dispositivos_download")
    private Integer dispositivosDownload;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Builder.Default
    @Column(name = "ativo",nullable = false)
    private Boolean ativo = true;

    @Builder.Default
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
