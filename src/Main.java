import java.util.Random;
/* Добавить 4-го игрока, у которого есть способность лечить членов команды после каждого раунда до тех пор,
пока этот игрок жив и сам медик жив, Медик не учавствует в бою но получает урон от Босса

Задачи на сообразительность(одну из списка):

Добавить 4-го игрока, танка, который имеет увеличенную жизнь но слабый удар. Может принимать часть удара босса на других игроков себе
Добавить 4-го игрока, ловкача, имеет шанс уклонения от ударов босса.
Добавить 4-го игрока, берсерк, блокирует часть удара босса по себе и прибавляет заблокированный урон к своему урону и возвращает боссу
Добавить 4-го игрока, тор, удар по боссу имеет шанс оглушить босса на раунд, вследствие чего босс пропускает раунд и не бьёт*/
public class Main {

    public static int bossHealth = 700;
    public static int[] heroesHealth = {250, 250, 250,250};

    public static int bossAttack = 50;
    public static int[] heroesAttack = {20, 20, 20,0};

    public static String bossDefenceType = "Physical";
    public static String[] heroesAttackType = { "Physical", "Magical", "Kinetic","Doctor"};


    public static void main(String[] args) {
        int roundNumber = 1;
        printStatistics(0);
        while (!isFinished()) {
            changeBossDefence();
            round(roundNumber);
            doctorHeroes ();
            roundNumber++;
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(3);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss defence: " + bossDefenceType);
    }

    public static void round(int number) {
        heroesHit();
        bossHit();
        printStatistics(number);
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] - bossAttack;

            }
        }
    }
    public static void doctorHeroes() {
        if (heroesHealth[3]> 0) {


            for (int i = 0; i <= 2; i++) {
                if (heroesHealth[i] > 0) {
                    heroesHealth[i] = heroesHealth[i] + 50;
                    System.out.println("doctor helped" + "  " + heroesAttackType[i]);
                }
            }
        }
    }
    public static void heroesHit() {
        for (int i = 0; i < heroesAttack.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int randomNumber = r.nextInt(9) + 2;
                    bossHealth = bossHealth - heroesAttack[i] * randomNumber;
                    System.out.println("Critical damage: " + (heroesAttack[i] * randomNumber));
                } else {
                    bossHealth = bossHealth - heroesAttack[i]; // bossHealth-=heroesAttack[i];
                }
            }
        }
    }

    public static void printStatistics(int round) {
        System.out.println("_________________________");
        System.out.println("Round " + round);
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magical health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("_________________________");
    }
}
