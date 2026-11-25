package td.geo.geo_tchad.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table (name = "communes")
@Getter 
@Setter 
public class Commune {
    
    @Id 
    @GeneratedValue (
        strategy = GenerationType.IDENTITY
    )
    @Setter (lombok.AccessLevel.NONE )
    private Long id;

    @Column (name = "code", nullable = false, unique = true, length = 10)   
    private String code;

    @Column (name = "nom", nullable = false, length = 100)
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "departement_id", nullable = false)
    private Departement departement;

    protected Commune(){};

    public Commune(String code, String nom, Departement departement) {
        this.code = code;
        this.nom = nom;
        this.departement = departement;
    }

}
