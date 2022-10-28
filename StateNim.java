//we represent the state simply as an integer that denotes the number of coins left in the pile
//player 1 is the human, player 0 is the computer

public class StateNim extends State {
  int pile;

  public StateNim() {
    this.pile = 13;
  }

  public StateNim(StateNim state) {
    this.pile = state.pile;
    player = state.player;
  }

  public void changeState(StateNim state, int removalAmount) {
    this.pile = this.pile - removalAmount;
    player = (player + 1) % 2;
  }

  public String toString() {
    return Integer.toString(this.pile);
  }
}