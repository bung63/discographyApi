package domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brsve_000 on 2016-11-02.
 */

@Entity
@NamedQueries({

        @NamedQuery(name = "Get Artists", query = "select a from Artist a order by a.id"),
        @NamedQuery(name = "Get Artist", query = "select a from Artist a where a.name = :name")

})
public class Artist  {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="artist")
    private List<Album> albums;

    public Artist() {

    }
    public Artist(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }
    public void addAlbum(Album album) {
        if ( this.albums== null )
            this.albums = new ArrayList<>();

        this.albums.add(album);
    }
    public void setAlbums(List<Album> albums) {
       this.albums = albums;
    }

    @Override
    public String toString(){
        return getName() + " " + getId();
    }
}
