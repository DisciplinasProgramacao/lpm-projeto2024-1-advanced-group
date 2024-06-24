import java.util.concurrent.atomic.AtomicLong;

public class Mesa {

  private static final AtomicLong idGenerator = new AtomicLong(0);
  private Long id;
  private Integer lugares;
  private Boolean isOcupada = false;

  public Mesa() {
    this.id = idGenerator.incrementAndGet();
  }

  public Mesa(Integer lugares) {
    this.id = idGenerator.incrementAndGet();
    this.lugares = lugares;
  }

  public Long getId() {
    return id;
  }

  public Integer getLugares() {
    return lugares;
  }

  public Boolean getIsOcupada() {
    return isOcupada;
  }

  public void setLugares(Integer lugares) {
    this.lugares = lugares;
  }

  public void setIsOcupada(Boolean isOcupada) {
    this.isOcupada = isOcupada;
  }

  public void ocupar() {
    this.isOcupada = true;
  }

  public void desocupar() {
    this.isOcupada = false;
  }

  public Boolean cabe(Integer quantidadePessoas) {
    return lugares >= quantidadePessoas;
  }

  @Override
  public String toString() {
    return "Mesa [id=" + id + ", lugares=" + lugares + ", isOcupada=" + isOcupada + "]";
  }
}
