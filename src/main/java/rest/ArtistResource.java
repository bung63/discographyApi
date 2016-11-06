package rest;

import domain.Artist;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by brsve_000 on 2016-11-03.
 */
@Path("/artist")
public interface ArtistResource {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    List<Artist> getArtists();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    Artist editArtist(@PathParam("id") Long artistId);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/name/{name}")
    Artist editArtist(@PathParam("name") String artistName);

    @DELETE
    @Path("/{id}")
    void removeArtist(@PathParam("id") Long artistId);
    @DELETE
    @Path("/name/{name}")
    void removeArtist(@PathParam("name") String artistName);

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    void updateArtist(Artist artist);


}
