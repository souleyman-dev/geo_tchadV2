package td.geo.geo_tchad.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@Table (name = "provinces")
public class Province {
    
    @Id 
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Setter(lombok.AccessLevel.NONE)
    private Long id;

    @Column (name = "code", nullable = false, unique = true, length = 10)
    private String code;

    @Column (name = "nom", nullable = false, unique = true, length = 100)
    private String nom;

    @Column (name = "chef_lieu", nullable = false, length = 100)
    private String chefLieu;

    @OneToMany (mappedBy = "province", fetch = FetchType.LAZY)
    private List<Departement> departements = new ArrayList<>();

    protected Province(){}

    public Province(String code, String nom, String chefLieu) {
        this.code = code;
        this.nom = nom;
        this.chefLieu = chefLieu;
    }


}
