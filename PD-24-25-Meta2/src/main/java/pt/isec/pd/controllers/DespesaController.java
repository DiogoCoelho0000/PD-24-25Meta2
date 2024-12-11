package pt.isec.pd.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pt.isec.pd.db.Bd;
import pt.isec.pd.models.Despesa;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {


    @GetMapping("/eliminar/{grupoNome}/{idDespesa}")
    public ResponseEntity<String> eliminarDespesa(@PathVariable String grupoNome,
                                                  @PathVariable String idDespesa,
                                                  Authentication authentication) {
        // Lógica para verificar se o usuário está no grupo
        boolean pertenceAoGrupo = Bd.integraGrupo(grupoNome, authentication.getName());

        if (!pertenceAoGrupo) {
            return ResponseEntity.status(403).body("Utilizador não pertence ao grupo");
        }

        // Lógica para excluir a despesa
        boolean despesaEliminada = Bd.eliminarDespesa(authentication.getName(), grupoNome,idDespesa);

        if (despesaEliminada) {
            return ResponseEntity.ok("Despesa eliminada com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao eliminar despesa");
        }
    }


    @GetMapping("/minhas-despesas/{grupoNome}")
    public ResponseEntity<?> listarDespesas(@PathVariable String grupoNome,
                                            Authentication authentication) {

        boolean pertenceAoGrupo = Bd.integraGrupo(grupoNome, authentication.getName());

        if (!pertenceAoGrupo) {
            return ResponseEntity.status(403).body("Utilizador não pertence ao grupo");
        }

        List<Despesa> despesas = Bd.listarDespesas(grupoNome);

        if (despesas.isEmpty()) {
            return ResponseEntity.status(404).body("Não há despesas associadas a este grupo.");
        }

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
       boolean pertenceAoGrupo = Bd.integraGrupo(grupoNome, userEmail);

        if (!pertenceAoGrupo) {
            return ResponseEntity.status(403).body("Utilizador não pertence ao grupo");
        }
        boolean despesaInserida = Bd.criaDespesa(grupoNome,despesa, userEmail);

        if (despesaInserida) {
            return ResponseEntity.ok("Despesa inserida com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao inserir despesa");
        }
    }

}
