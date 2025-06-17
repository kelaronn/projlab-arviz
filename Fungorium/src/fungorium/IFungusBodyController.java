package fungorium;

import java.util.Random;

public interface IFungusBodyController {
    boolean ShootSpores(boolean isRandom, Random rand);

    boolean Die();

    boolean ProduceSpore();
}
