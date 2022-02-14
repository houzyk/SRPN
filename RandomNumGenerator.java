import java.util.ArrayList;

public class RandomNumGenerator {
  private ArrayList<Integer> randomNumbers = new ArrayList<>();
  private int randomIndex = 0;

  public RandomNumGenerator() {
    initRandomNumbers();
  }

  public Integer generateRandomNumber() {
    if (this.randomIndex > 21)
      this.randomIndex = 0;
    Integer randomNumber = this.randomNumbers.get(this.randomIndex);
    this.randomIndex++;
    return randomNumber;
  }

  private void initRandomNumbers() {
    this.randomNumbers.add(1804289383);
    this.randomNumbers.add(846930886);
    this.randomNumbers.add(1681692777);
    this.randomNumbers.add(1714636915);
    this.randomNumbers.add(1957747793);
    this.randomNumbers.add(424238335);
    this.randomNumbers.add(719885386);
    this.randomNumbers.add(1649760492);
    this.randomNumbers.add(596516649);
    this.randomNumbers.add(1189641421);
    this.randomNumbers.add(1025202362);
    this.randomNumbers.add(1350490027);
    this.randomNumbers.add(783368690);
    this.randomNumbers.add(1102520059);
    this.randomNumbers.add(2044897763);
    this.randomNumbers.add(1967513926);
    this.randomNumbers.add(1365180540);
    this.randomNumbers.add(1540383426);
    this.randomNumbers.add(304089172);
    this.randomNumbers.add(1303455736);
    this.randomNumbers.add(35005211);
    this.randomNumbers.add(521595368);
  }
}
