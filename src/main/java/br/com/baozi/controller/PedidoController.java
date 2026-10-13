package br.com.baozi.controller;

import br.com.baozi.model.Pedido;
import br.com.baozi.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository repository;

    public PedidoController(PedidoRepository repository) {
        this.repository = repository;
    }

    // POST /pedidos - criar
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        pedido.setId(null);
        Pedido salvo = repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /pedidos - listar todos
    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }

    // GET /pedidos/{id} - consultar por id
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /pedidos/{id} - atualizar (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pedido.setId(id);
        return ResponseEntity.ok(repository.save(pedido));
    }

    // DELETE /pedidos/{id} - apagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
