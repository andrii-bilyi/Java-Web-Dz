package step.learning.services.kdf;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.services.hash.HashService;


@Singleton
public class HashKdfService implements KdfService{
    private final HashService hashService;
    @Inject
    public HashKdfService(HashService hashService) {
        this.hashService = hashService;
    }

    @Override
    public String derivedKey(String password, String salt) {
        //за формалізмом 5.1 PBKDF1
        String t1 = hashService.digest(password + salt);
        String t2 = hashService.digest(t1);
        return t2.substring(0, 32);
    }
}
