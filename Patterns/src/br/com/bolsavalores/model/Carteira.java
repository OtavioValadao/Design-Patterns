package br.com.bolsavalores.model;

import br.com.bolsavalores.factorymethod.Acao;

import java.util.ArrayList;
import java.util.List;

public class Carteira {
    private String nome;
    private List<AcaoPosicao> posicoes;
    private double saldoDisponivel;

    public Carteira(String nome, double saldoDisponivel) {
        this.nome = nome;
        this.saldoDisponivel = saldoDisponivel;
        this.posicoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AcaoPosicao> getPosicoes() {
        return posicoes;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public void adicionarOuAtualizarPosicao(Acao acao, int quantidade, double preco) {
        AcaoPosicao existente = null;
        for (AcaoPosicao posicao : posicoes) {
            if (posicao.getAcao().getCodigo().equals(acao.getCodigo())) {
                existente = posicao;
            }
        }
        if (existente == null) {
            posicoes.add(new AcaoPosicao(acao, quantidade, preco));
        } else {
            int novaQuantidade = existente.getQuantidade() + quantidade;
            double novoTotal = existente.getPrecoMedio() * existente.getQuantidade() + preco * quantidade;
            double novoPrecoMedio = novoTotal / novaQuantidade;
            existente.setQuantidade(novaQuantidade);
            existente.setPrecoMedio(novoPrecoMedio);
        }
    }

    public void reduzirPosicao(Acao acao, int quantidade) {
        AcaoPosicao alvo = null;
        for (AcaoPosicao posicao : posicoes) {
            if (posicao.getAcao().getCodigo().equals(acao.getCodigo())) {
                alvo = posicao;
            }
        }
        if (alvo != null) {
            int novaQuantidade = alvo.getQuantidade() - quantidade;
            if (novaQuantidade <= 0) {
                posicoes.remove(alvo);
            } else {
                alvo.setQuantidade(novaQuantidade);
            }
        }
    }
}


