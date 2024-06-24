import java.util.concurrent.atomic.AtomicLong;

public class Cliente {

  private static final AtomicLong idGenerator = new AtomicLong(0);
  private Long id;
  private String nome;
  private String telefone;

  public Cliente() {
    this.id = idGenerator.incrementAndGet();
  }

  public Cliente(String nome, String telefone) {
    this.id = idGenerator.incrementAndGet();
    setNome(nome);
    setTelefone(telefone);
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setNome(String nome) {
    if (nome != null && nome.length() > 0) {
      this.nome = nome;
    } else {
      throw new IllegalArgumentException("Nome não pode ser nulo");
    }
  }

  public void setTelefone(String telefone) {
    if (telefone != null && telefone.length() > 0) {
      this.telefone = telefone;
    } else {
      throw new IllegalArgumentException("Telefone não pode ser nulo");
    }
  }

  @Override
  public String toString() {
    return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
  }
}
