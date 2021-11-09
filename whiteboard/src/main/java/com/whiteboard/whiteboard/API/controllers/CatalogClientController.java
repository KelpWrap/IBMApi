
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import com.whiteboard.whiteboard.CatalogClient;

@Component
@Path("/client")
@Produces("application/json")
public class CatalogClientController {
  @GET
  @Path("/{client-id}") 
  public Response getclient(@PathParam("client-id") String clientId){return null;}
  
  @GET
  public List<CatalogClient> getclients(@QueryParam("meta-data") List<String> clientMetadata) {return null;}
  @PUT
  @Path("/{client-id}")
  public CatalogClient replaceclient(@PathParam("client-id") String clientId, CatalogClient client) {return null;}
  @PATCH
  @Path("/{client-id}")
  public CatalogClient updateclient(@PathParam("client-id") String clientId, CatalogClient client) {return null;}
  @DELETE
  @Path("/{client-id}")
  public Response deleteclient(@PathParam("client-id") String clientId) throws CatalogClientDoesNotExistException {return null;}
}