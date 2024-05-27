import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @PostMapping("/mesas")
    public ResponseEntity<Restaurante> adicionarMesa(@RequestParam int idMesa, @RequestParam int capacidade) {
        Restaurante restaurante = restauranteRepository.getOne(1L);
        if (restaurante != null) {
            restaurante.adicionarMesa(idMesa, capacidade);
            restauranteRepository.save(restaurante);
            return ResponseEntity.ok().body(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/requisicoes")
    public ResponseEntity<Restaurante> adicionarRequisicao(@RequestBody RequisicaoDTO requisicaoDTO) {
        Restaurante restaurante = restauranteRepository.getOne(1L);
        if (restaurante != null) {
            restaurante.adicionarRequisicao(requisicaoDTO.getCliente(), requisicaoDTO.getNumeroDePessoas());
            restauranteRepository.save(restaurante);
            return ResponseEntity.ok().body(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/alocar")
    public ResponseEntity<Restaurante> alocarClientes() {
        Restaurante restaurante = restauranteRepository.getOne(1L);
        if (restaurante != null) {
            restaurante.alocarCliente();
            restauranteRepository.save(restaurante);
            return ResponseEntity.ok().body(restaurante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/requisicoes")
    public ResponseEntity<String> listarRequisicoes() {
        Restaurante restaurante = restauranteRepository.getOne(1L);
        if (restaurante != null) {
            return ResponseEntity.ok().body(restaurante.printListaRequisicoes());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mesas")
    public ResponseEntity<String> listarMesas() {
        Restaurante restaurante = restauranteRepository.getOne(1L);
        if (restaurante != null) {
            return ResponseEntity.ok().body(restaurante.printListaMesas());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
