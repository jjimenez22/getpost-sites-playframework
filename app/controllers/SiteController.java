package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import models.Site;
import models.SiteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

import javax.validation.ConstraintViolationException;
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
            wrongToken();
        }
    }

    private void wrongToken() {
        response.status = 401;
        renderText("Bad Token");
    }

    public void save() {
        String json = params.get("body");
        try {
            SiteDTO siteDTO = Optional.of(gson.fromJson(json, SiteDTO.class)).get();
            logger.info("Creating website: {}", siteDTO);

            response.status = 201;

            SiteDTO created = SiteDTO.fromSite(siteDTO.toSite().save());
            logger.info("Created website: {}", created);
            renderJSON(created);
        } catch (JsonSyntaxException | ConstraintViolationException | NullPointerException e) {
            malformedBody(json);
        }
    }

    private void malformedBody(String json) {
        logger.info("Malformed body: {}", json);
        response.status = 400;
        renderText("Malformed body");
    }

    public void get(Long id) {
        logger.info("Retrieving website with id: {}", id);
        Optional<Site> found = Optional.ofNullable(Site.findById(id));
        if (found.isPresent()) {
            SiteDTO site = SiteDTO.fromSite(found.get());
            logger.info("Retrieved website: {}", site);
            renderJSON(site);
        } else {
            String notFound = String.format("Not found website with id: %s", id);
            logger.info(notFound);
            response.status = 404;
            renderText(notFound);
        }
    }
}
