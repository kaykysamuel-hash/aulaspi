package prova.atividade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 1. MODELO
@Entity
class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

	public Tarefa() {
	}

	public Tarefa(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

// 2. REPOSITÓRIO
@Repository
interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}

// 3. CONTROLLER
@Controller
class TarefaController {

	private final TarefaRepository repository;

	public TarefaController(TarefaRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("tarefas", repository.findAll());
		model.addAttribute("tarefa", new Tarefa());
		return "index";
	}

	@PostMapping("/salvar")
	public String salvar(Tarefa tarefa) {
		repository.save(tarefa);
		return "redirect:/";
	}
}