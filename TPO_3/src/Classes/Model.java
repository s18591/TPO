package Classes;

import java.math.BigInteger;

public class Model {
    private BigInteger bigInteger;

    public Model(BigInteger bigInteger){
        this.bigInteger = bigInteger;
    }
    public BigInteger getResult(){
        return bigInteger;
    }
}
