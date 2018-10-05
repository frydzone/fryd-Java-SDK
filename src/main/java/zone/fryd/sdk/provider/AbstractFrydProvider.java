package zone.fryd.sdk.provider;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.github.scribejava.core.oauth2.clientauthentication.RequestBodyAuthenticationScheme;

/**
 * @author Kristoffer Pöpperling, fryd
 */
public abstract class AbstractFrydProvider extends DefaultApi20 {

    @Override
    public ClientAuthentication getClientAuthentication() {
        return RequestBodyAuthenticationScheme.instance();
    }

    public abstract String getAPIBaseURL();

}
