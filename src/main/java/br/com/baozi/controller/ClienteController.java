package br.com.baozi.controller;

import br.com.baozi.model.Cliente;
import br.com.baozi.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    // POST /clientes - criar
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        cliente.setId(null);
        Cliente salvo = repository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /clientes - listar todos
    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    // GET /clientes/{id} - consultar por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /clientes/{id} - atualizar (opcional)
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        return ResponseEntity.ok(repository.save(cliente));
    }

    // DELETE /clientes/{id} - apagar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
