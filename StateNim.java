//we represent the state simply as an integer that denotes the number of coins left in the pile
//player 1 is the human, player 0 is the computer

public class StateNim extends State {
  public int pile;

  //initiates a new game with 13 coins and the human going first
  public StateNim() {
    this.pile = 13;
    player = 1;
  }

  //creates a new state with a specific pile size
  public StateNim(StateNim state) {
    this.pile = state.pile;
    player = state.player;
  }

  public int getPileSize() {
    return this.pile;
  }

  //takes in the old state and the amount of coins we would like to remove, and returns the new state with the player already altered
  //does not verify whether the removal amount is valid
  public void changeState(StateNim state, int removalAmount) {
    this.pile = this.pile - removalAmount;
    player = (player + 1) % 2;
  }

  public String toString() {
    return Integer.toString(this.pile);
  }
}