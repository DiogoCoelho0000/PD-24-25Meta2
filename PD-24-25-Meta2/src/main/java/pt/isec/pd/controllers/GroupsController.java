package pt.isec.pd.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isec.pd.db.Bd;
import pt.isec.pd.models.Grupos;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GroupsController {

    @GetMapping("/meus-grupos")
    public List<Grupos> listarGrupos(Authentication authentication) {
        // A partir da autenticação, obtemos o nome do usuário autenticado
        String usuarioAutenticado = authentication.getName();

        List<Grupos> grupos = Bd.listarGruposDB(usuarioAutenticado);  // Assume que você tem um método que retorna os grupos

        if (grupos.isEmpty()) {
            return (List<Grupos>) ResponseEntity.status(404).body("Não há grupos associados a este usuário.");
        }

        return ResponseEntity.ok(grupos).getBody();
    }

}
