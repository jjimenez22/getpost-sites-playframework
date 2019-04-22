package controllers;

import com.google.gson.Gson;
import models.Site;
import models.SiteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Optional;

public class SiteController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new Gson();

    @Before
    void checkToken() {
        logger.info("Received request: {}", request);
        String token = "";
        Optional<Http.Header> optional = Optional.ofNullable(request.headers.get("token"));
        if (optional.isPresent()) {
            token = optional.get().value();
        }
        if (!"123456789".equals(token)) {
//            throw new UnauthorizedException();
            wrongToken(null);
            renderText("Bad Token");
        }
    }

    //    @Catch(UnauthorizedException.class)
    public void wrongToken(Throwable e) {
        response.status = 401;
    }

    public void save() {
        String body = params.get("body");
        SiteDTO siteDTO = gson.fromJson(body, SiteDTO.class);
        logger.info("Creating website: {}", siteDTO);

        response.status = 201;

        SiteDTO created = SiteDTO.fromSite(siteDTO.toSite().save());
        logger.info("Created website: {}", created);
        renderJSON(created);
    }

    public void get(Long id) {
        logger.info("Retrieving website with id: {}", id);
        SiteDTO site = SiteDTO.fromSite(Site.findById(id));
        logger.info("Retrieved website: {}", site);
        renderJSON(site);
    }
}
