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

        Artist a;

        if (artist.getId() == null ) {
            a = new Artist();
            a.setName(artist.getName());
            System.out.println(artist);
            entityManager.persist(a);
            List<Album> aAlbums = artist.getAlbums().stream().map(b->
                new Album(b.getId(),b.getTitle(),b.getRelease(),b.getTracks().stream().map(c->
                    new Track(c.getId(),c.getTrack(),b)).collect(Collectors.toList()), a)).collect(Collectors.toList());

            a.setAlbums(aAlbums);
            //      entityManager.persist(a);
            entityManager.merge(a);

        } else {
            a = entityManager.find(Artist.class, artist.getId());
            a.setName(artist.getName());
            List<Album> aAlbums = artist.getAlbums().stream().map(b->
                    new Album(b.getId(),b.getTitle(),b.getRelease(),b.getTracks().stream().map(c->
                            new Track(c.getId(),c.getTrack(),b)).collect(Collectors.toList()), a)).collect(Collectors.toList());

            a.setAlbums(aAlbums);

            entityManager.merge(a);
        }

    }



}
