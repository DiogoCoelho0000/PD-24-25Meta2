package pt.isec.pd.controllers;


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
    public List<Grupos> listarGrupos(@RequestParam String email, Authentication authentication) {
        // A partir da autenticação, obtemos o nome do usuário autenticado
        String usuarioAutenticado = authentication.getName();

        // Consulta ao banco de dados para pegar os grupos que o usuário pertence
        List<Grupos> grupos = new ArrayList<>();
        try {
            grupos = Bd.listarGruposDB(usuarioAutenticado);  // Assume que você tenha um método que retorna os grupos
            System.out.println(grupos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grupos;
    }
}
