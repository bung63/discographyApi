package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by brsve_000 on 2016-11-03.
 */
@Entity
public class Album {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String title;

    private String release;

    @OneToMany(fetch=FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy="album")
    private List<Track> tracks;

    @ManyToOne(fetch= FetchType.LAZY,optional=false)

    @JoinColumn(name = "artist_id")
    @JsonIgnore
    private Artist artist;


    public Album() {

    }
    public Album(Long id,String title, String release, List<Track> tracks) {
        this.id = id;
        this.title = title;
        this.release = release;
        this.tracks = tracks;

    }
    public Album(Long id,String title, String release, List<Track> tracks, Artist artist) {
        this.id = id;
        this.title = title;
        this.release = release;
        this.tracks = tracks;
        this.artist = artist;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        if ( this.tracks == null )
            this.tracks = new ArrayList<>();

        this.tracks.add(track);
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString(){
        return getTitle() + " " + getId();
    }
}
