package pt.isec.pd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pt.isec.pd.db.Bd;
import pt.isec.pd.models.Despesa;
import pt.isec.pd.rmi.server.RmiService;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {
    private final RmiService rmiService;

    public DespesaController(RmiService rmiService) {
        this.rmiService = rmiService;
    }

    @GetMapping("/eliminar/{grupoNome}/{idDespesa}")
    public ResponseEntity<String> eliminarDespesa(@PathVariable String grupoNome,
                                                  @PathVariable String idDespesa,
                                                  Authentication authentication) {
        Bd.ligaBD("Base_de_dados");

        boolean pertenceAoGrupo = Bd.integraGrupo(grupoNome, authentication.getName());

        if (!pertenceAoGrupo) {
            Bd.desligaBD("Base_de_dados");
            return ResponseEntity.status(403).body("Utilizador não pertence ao grupo");
        }
        Bd.ligaBD("Base_de_dados");
        // Lógica para excluir a despesa
        boolean despesaEliminada = Bd.eliminarDespesa(authentication.getName(), grupoNome,idDespesa);

        if (despesaEliminada) {
            Bd.desligaBD("Base_de_dados");
            rmiService.notifyObservers("O cliente " + authentication.getName() + "eliminou a despesa com ID: "+ idDespesa);
            return ResponseEntity.ok("Despesa eliminada com sucesso");
        } else {
            Bd.desligaBD("Base_de_dados");
            return ResponseEntity.status(500).body("Erro ao eliminar despesa");
        }
    }


    @GetMapping("/minhas-despesas/{grupoNome}")
    public ResponseEntity<?> listarDespesas(@PathVariable String grupoNome,
                                            Authentication authentication) {
        Bd.ligaBD("Base_de_dados");
        boolean pertenceAoGrupo = Bd.integraGrupo(grupoNome, authentication.getName());

        if (!pertenceAoGrupo) {
            Bd.desligaBD("Base_de_dados");
            return ResponseEntity.status(403).body("Utilizador não pertence ao grupo");
        }

        List<Despesa> despesas = Bd.listarDespesas(grupoNome);

        if (despesas.isEmpty()) {
            Bd.desligaBD("Base_de_dados");
            return ResponseEntity.status(404).body("Não há despesas associadas a este grupo.");
        }
        rmiService.notifyObservers("O cliente " + authentication.getName() + "listou despesas: ");
        return ResponseEntity.ok(despesas);
    }

    @PostMapping("/inserir/{grupoNome}")
    public ResponseEntity<String> inserirDespesa(@PathVariable String grupoNome,
                                                 @RequestBody Despesa despesa,
                                                 Authentication authentication) {
        String userEmail = authentication.getName();
        System.out.println("OLASSSS");
        //System.out.println(user.getEmail());
        System.out.println("GRUPO " + grupoNome);
        System.out.println(userEmail);
        System.out.println(despesa);
        // Verifica se o utilizador está no grupo
        Bd.ligaBD("Base_de_dados");
       boolean pertenceAoGrupo = Bd.integraGrupo(grupoNome, userEmail);

        if (!pertenceAoGrupo) {
            Bd.desligaBD("Base_de_dados");
            return ResponseEntity.status(403).body("Utilizador não pertence ao grupo");
        }
        Bd.ligaBD("Base_de_dados");
        boolean despesaInserida = Bd.criaDespesa(grupoNome,despesa, userEmail);

        Bd.desligaBD("Base_de_dados");
        if (despesaInserida) {
            Bd.desligaBD("Base_de_dados");
            rmiService.notifyObservers("O cliente " + authentication.getName() + "criou uma a despesa");
            return ResponseEntity.ok("Despesa inserida com sucesso");
        } else {
            Bd.desligaBD("Base_de_dados");
            return ResponseEntity.status(500).body("Erro ao inserir despesa");
        }
    }

}
