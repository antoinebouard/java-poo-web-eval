package com.java.eval.web;

/*
import com.ipiecoles.java.java230.model.Employe;
import com.ipiecoles.java.java230.model.Manager;
import com.ipiecoles.java.java230.model.Technicien;
import com.ipiecoles.java.java230.repository.CommercialRepository;
import com.ipiecoles.java.java230.repository.EmployeRepository;
import com.ipiecoles.java.java230.repository.ManagerRepository;
import com.ipiecoles.java.java230.repository.TechnicienRepository;
 */
import com.java.eval.web.model.Album;
import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public void run(String ... strings) throws Exception {
        String url = "jdbc:mysql://localhost:33066/audio?serverTimezone=UTC";
        String user= "root"; String pwd = "";
        java.sql.Connection connexion = null;
        try {
            connexion = java.sql.DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion réussie !");
            // LES REQUETES
            System.out.println(albumRepository.count());
            System.out.println(albumRepository.findById(2));

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }

        if(connexion != null) {
            try {
                connexion.close();
            }
            catch (java.sql.SQLException e) {

            }
        }
    }
}
