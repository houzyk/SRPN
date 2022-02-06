import java.util.ArrayList;

/**
 * Class for generating pseudo-random numbers given an 'r' command.
 */

public class RandomNumGenerator {
  // stores pseudo-random number
  private ArrayList<Integer> randomNumbers = new ArrayList<>();
  // index used to loop over randomNumbers array
  private int randomIndex = 0;

  // * constructor - executes when randomNumber generator is initalised in SRPN constructor
  public RandomNumGenerator () {
    initRandomNumbers(); // fills radom numbers array with 22 random numbers
  }

  // gives back a random number, used in SRPN class whenever "r" is inserted
  public Integer generateRandomNumber() {
    if (this.randomIndex > 21) this.randomIndex = 0; // makes sure that random index does not go beyond the number of randomnumbers in the randomNumbers array
    Integer randomNumber = this.randomNumbers.get(this.randomIndex); // picks a randomnumber
    this.randomIndex++; // increased the index to pick a different number if an 'r' is pressed again
    return randomNumber;
  }

  // adds 22 random numbers to randomNumbers array
  private void initRandomNumbers() {
    this.randomNumbers.add(1804289383); // these numbers are obtained from "legacy" SRPN given
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
