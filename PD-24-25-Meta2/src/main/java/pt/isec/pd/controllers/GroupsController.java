package pt.isec.pd.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.isec.pd.db.Bd;
import pt.isec.pd.models.Grupos;
import pt.isec.pd.rmi.server.RmiService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GroupsController {
    private final RmiService rmiService;

    public GroupsController(RmiService rmiService) {
        this.rmiService = rmiService;
    }

    @GetMapping("/meus-grupos")
    public List<Grupos> listarGrupos(/*@RequestParam String email,*/ Authentication authentication) {

        String usuarioAutenticado = authentication.getName();


        List<Grupos> grupos = new ArrayList<>();
        try {
            Bd.ligaBD("Base_de_dados");
            grupos = Bd.listarGruposDB(usuarioAutenticado);
            rmiService.notifyObservers("O cliente " + authentication.getName() + "listou grupos: ");
            Bd.desligaBD("Base_de_dados");
            System.out.println(grupos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grupos;
    }
}
