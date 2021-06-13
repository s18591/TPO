package Classes;

import java.math.BigInteger;

public class Logic {
    public static Model add(BigInteger bigInteger,BigInteger bigInteger2){
        if(bigInteger == null || bigInteger2 == null){
            return null;
        }
        return new Model(bigInteger.add(bigInteger2));
    }
}
