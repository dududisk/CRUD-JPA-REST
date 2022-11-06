package com.application.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.produto;
import com.application.model.repository.Produto;
import com.application.model.repository.ProdutoRepository;

import antlr.collections.List;

@RestController
@RequestMappin("api/produto")

public class ProdutoRestController {

    @Autowired
    private ProdutoRepository repProduto;

    @GetMapping("/listar")
    public List<Produto> listar() {
        List<Produto> produtos = repProduto.findAll();
        return produtos;
    }

    @GetMapping("/ver/{id}")
    public Produto ver(@PathVariable("id") int id) {

        Optional<produto> produto = repProduto.findById(id);

        if (produto.isEmpty())
            throw new IllegalArgumentExption("Id Não Encontrado: " + id);

        return produto.get();
    }

    @PostMapping("/adicionar")
    public Produto adicionar(@Valid @RequestBody Produto produto) {
        produto = repProduto.save(produto);
        return produto;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {

        Produto produto = repProduto.findById()
                .orElseThrow(() -> new IllegalArgumentExption("Id Não Encontrado:" + id));

        repProduto.delete(produto);
        return "Produto [" + id + "] removido com Sucesso";

    }

    @PutMapping("/editar/{"id"}")
    public Produto editar(@PathVariable("id") int id, @Valid @RequestBody Produto requestProduto)

        Optional<produto> produto = repProduto.findById(id);
        if(produto.isEmpty())
            throw new IllegalArgumentException("Id Não encontrado:" + id);
        requestProduto.setId(id);

        repProduto.save(requestProduto);

        return requestProduto;

}

    public ProdutoRepository getRepProduto() {
        return repProduto;
    }
