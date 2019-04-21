package controllers;

import com.google.gson.Gson;
import models.Site;
import models.SiteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Before;
import play.mvc.Controller;

public class SiteController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new Gson();

    @Before
    void checkToken() {
        String token = request.headers.get("token").value();
        logger.info("token: {}", token);
        if (!token.equals("123456789")) {
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
        logger.info("body: {}", body);

        SiteDTO siteDTO = gson.fromJson(body, SiteDTO.class);
        logger.info("siteDTO: {}", siteDTO);

        response.status = 201;

        renderJSON(siteDTO.toSite().save());
    }

    public void get(Long id) {
        renderJSON(SiteDTO.fromSite(Site.findById(id)));
    }
//    public void text() {
//        params.all().entrySet().forEach(stringEntry -> {
//            System.out.println(stringEntry.getKey()+": ");
//            Arrays.asList(stringEntry.getValue()).forEach(System.out::println);
//        });
//        renderText("");

//    }
}
