package cn.edu.nju.p.cache;

/**
 * token manager interface
 */
public interface TokenManager {

    String createToken(String phoneNumber);

    boolean checkToken(String token);
}
