package org.example.modelo.muitospramuitos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double nota;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "atores_filmes",
               joinColumns = @JoinColumn(name = "filme_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "ator_id", referencedColumnName = "id"))
    private List<Ator> atores;

    public Filme(){};
    public Filme(String nome, Double nota){
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public List<Ator> getAtores() {
        if (atores == null){
            atores = new ArrayList<>();
        }
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public void adicionaAtor(Ator ator){
        if(ator != null && !this.getAtores().contains(ator)) {
            this.getAtores().add(ator);
            if(!ator.getFilmes().contains(this)) {
                ator.getFilmes().add(this);
            }
        }
    }
}
