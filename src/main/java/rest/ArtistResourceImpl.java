package rest;

import domain.Album;
import domain.Artist;
import domain.Track;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brsve_000 on 2016-11-03.
 */
@Stateless
public class ArtistResourceImpl implements ArtistResource{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Artist> getArtists() {
        List<Artist> artist = entityManager.createNamedQuery("Get Artists").getResultList();

        if ( artist.size()==0) {
            artist = new ArrayList<>();
        }
        return artist;
    }

    @Override
    public Artist editArtist(Long artistId) {
        Artist a = entityManager.find(Artist.class,artistId);

        if (a == null){
            a = new Artist();
            a.setName("Not Found");
        }
        return a;
    }

    @Override
    public Artist editArtist(String artistName) {
        final List<Artist> list = entityManager.createNamedQuery("Get Artist").setParameter("name",artistName).getResultList();
        Artist a;
        if ( list.size() == 1 ) {
            a = list.get(0);
        } else {
            a = new Artist();
            a.setName("Not Found");
        }
        return a;
    }

    @Override
    public void removeArtist(Long artistId) {
        Artist a = entityManager.find(Artist.class,artistId);
        if ( a!= null)
            entityManager.remove(a);
    }
    @Override
    public void removeArtist(String artistName) {
        System.out.println(artistName);
        List<Artist> list = entityManager.createNamedQuery("Get Artist").setParameter("name",artistName).getResultList();
        if ( list.size() == 1 ) {
            Artist a = list.get(0);
            entityManager.remove(a);
        }
    }
    @Override
    public void updateArtist(Artist artist){
        artist.getAlbums().stream().forEach(System.out::println);
        Artist a;

        if (artist.getId() == null ) {

            a = new Artist();
            a.setName(artist.getName());

            for (Album al:artist.getAlbums()) {
                al.setArtist(a);
                for (Track track:al.getTracks()) {
                    track.setAlbum(al);
                }
                a.addAlbum(al);
            }

            entityManager.persist(a);

        } else {
            a = entityManager.find(Artist.class, artist.getId());
            a.setName(artist.getName());
            for (Album al:artist.getAlbums()) {
                al.setArtist(a);
                for (Track track:al.getTracks()) {
                    track.setAlbum(al);
                }
                a.addAlbum(al);
            }


        }

    }



}
