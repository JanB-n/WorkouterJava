package project.workouter.model;

/**
 * Reprezentuje token zwracany użytkownikowi
 */
public class TokenResponse {
    private String token;

    private String refresh;

    public TokenResponse(String token, String refresh) {
        this.token = token;
        this.refresh = refresh;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }
}